package howmuchcar.infra.openai

import com.google.gson.Gson
import com.google.gson.JsonObject
import howmuchcar.infra.dto.ChatMessage
import howmuchcar.infra.dto.ChatRequest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TagGenerator {
    private val client = OkHttpClient()

    @Value("\${openai.key}")
    private lateinit var apiKey: String

    @Value("\${openai.model}")
    private lateinit var model: String

    fun generateTags(description: String): List<String> {
        val gson = Gson()
        val data = JsonObject().apply {
            addProperty("설명글", description.trimIndent())
        }
        val dataString = gson.toJson(data)
        val prompt = createPrompt(dataString, "설명글")
        val requestBody = ChatRequest(
            model = model,
            messages = listOf(
                ChatMessage(
                    role = "system",
                    content = "당신은 데이터 분석 전문가입니다. 텍스트를 분석하고 지정된 태그에 따라 특징적인 데이터만 분류합니다."
                ),
                ChatMessage(
                    role = "user",
                    content = prompt
                )
            )
        )

        val jsonBody = gson.toJson(requestBody)
        val mediaType = "application/json".toMediaType()
        val body = jsonBody.toRequestBody(mediaType)

        val request = Request.Builder()
            .url("https://api.openai.com/v1/chat/completions")
            .addHeader("Authorization", "Bearer $apiKey")
            .addHeader("Content-Type", "application/json")
            .post(body)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                println("Error: ${response.code} - ${response.message}")  // 응답 코드 및 메시지 출력
                return emptyList()  // 실패 시 빈 리스트 반환
            }

            val responseBody = response.body?.string() ?: return emptyList()
            println("Response body: $responseBody")  // 응답 본문 출력 (디버그용)

            val regex = Regex("""\[(.*?)\]""")
            return regex.find(responseBody)?.groupValues?.get(1)
                ?.split(",")
                ?.map { it.trim().replace("\"", "").replace("\\", "") } // 따옴표 및 백슬래시 제거
                ?: emptyList()
        }
    }

    private fun createTagDefinitions(): String {
        return """
        - "무사고": 사고나 침수 등이 난 적이 없는 매물입니다. 텍스트에서 사고가 없었다는 점을 강조할 확률이 높습니다. 데이터에는 사고 유무, 정비, 사고 관련 수치가 포함되어 있습니다.
        - "옵션(상)": 차량에 다양한 고급 옵션이 매우 풍부하게 탑재된 매물입니다. 데이터 상에서 '유'로 표기된 옵션 항목이 10개 이상일 경우 해당됩니다. 텍스트에서도 "풀옵션", "최상위 트림", "프리미엄", "BOSE", "어라운드뷰", "자동주차", "통풍시트" 등 고급 옵션 강조 단어가 자주 등장합니다.
        - "옵션(중)": 옵션이 적절히 포함된 평균 수준의 차량입니다. 데이터에서 '유'로 표시된 옵션이 5개 이상 9개 이하인 경우 해당됩니다. 텍스트에서도 "스마트키", "열선시트", "후방카메라", "네비게이션" 등 일반적인 옵션이 언급될 확률이 높습니다.
        - "옵션(하)": 옵션이 거의 없거나 최소한만 포함된 차량입니다. 데이터상 '유'로 표시된 옵션이 0~4개인 경우 해당됩니다. 텍스트에서 옵션 관련 언급이 거의 없거나 강조되지 않을 확률이 높습니다.
        - "1인사용": 차량의 소유주가 변경된 적이 없는 매물입니다. 텍스트에서 1인소유, 1인사용 등을 강조할 확률이 높습니다. 데이터의 '소유자변경' 컬럼을 참고하면 좋습니다.
        - "출퇴근용": 주로 출퇴근 목적으로 사용된 것으로 보이는 차량입니다. 연간 주행거리가 15,000km~25,000km 범위이며, 주행 패턴이 규칙적이거나, 텍스트에 "출퇴근", "통근", "직장", "회사" 등의 단어가 포함되어 있을 확률이 높습니다.
        - "나들이용": 주로 여가 활동이나 주말 사용 목적으로 운행된 차량입니다. 주행거리가 연간 10,000km 이하이고, 텍스트에 "주말", "나들이", "여행", "캠핑", "레저", "가족" 등의 단어가 포함되어 있을 확률이 높습니다.
        - "연식대비적은마일리지": 차량 연식에 비해 주행거리가 현저히 적은 차량입니다. 자세한 기준은 다음과 같습니다:
            * 5년 미만 차량: 연평균 주행거리 10,000km 이하
            * 5-10년 차량: 연평균 주행거리 8,000km 이하
            * 10년 이상 차량: 연평균 주행거리 5,000km 이하
        - "전문정비": 정기적으로 전문 정비소나 제조사 서비스센터에서 관리되었거나 딜러가 상태가 좋다고 보증한 차량입니다. 텍스트에 "제조사", "서비스센터", "정비소", "직영점", "정비기록", "정식 딜러", "딜러 보증" 등의 단어가 포함되어 있을 확률이 높습니다.
        - "급매물": 시장 평균 가격보다 현저히 낮은 가격에 판매되거나, 빠른 판매를 원하는 판매자의 차량입니다. 텍스트에 "급매", "급처", "급히", "빨리", "즉시", "네고가능", "가격조정", "대폭할인" 등의 급한 상황(이사, 이직, 급전 등)을 암시하는 단어가 포함되어 있을 확률이 높습니다.
        """.trimIndent()
    }

    private fun createPrompt(data: String, textColumn: String): String {
        return """
            # 중고차 데이터 태그 분류 작업

            ## 작업 설명
            아래에 주어진 하나의 중고차 데이터를 분석하고, 주요 텍스트 컬럼 '${textColumn}'을 기반으로 해당 차량에 부여할 수 있는 태그들을 선정해주세요.

            ## 태그 정의
            다음 태그 정의에 따라 데이터를 분류해주세요:
            ${createTagDefinitions()}

            ## 분류 규칙
            1. 특별한 특징이 없는 경우, 태그를 부여하지 마세요.
            2. 데이터의 태그는 없을 수도 있고 1개 있을 수도 있고 여러 개 있을 수도 있습니다.
            3. 주 분석 대상은 ${textColumn} 컬럼이며, 다른 컬럼의 정보도 함께 고려하세요.
            4. 단, 컬럼 값이 null인 경우는 정보 수집 실패로 간주하고 분석에 포함하지 마세요.
            5. 태그는 데이터 전체 맥락에서 명확하고 뚜렷한 특징이 보일 때만 부여해야 합니다.
            6. 태그 선정은 느슨하지 않게, 엄격한 기준으로 판단해주세요.

            ## 입력 데이터
            ${data}

            ## 출력 형식
            해당 데이터에 적용 가능한 태그를 아래와 같은 JSON 배열 형식으로 응답해주세요:
            {"tags": ["태그1", "태그2"]}
            
            특징이 없어서 태그를 부여하지 않을 경우, 빈 배열을 반환해주세요:
            {"tags": []}
        """.trimIndent()
    }
}

