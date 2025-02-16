package whenyourcar.application.dto.chat.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import whenyourcar.application.dto.car.common.CarSaleInfoDto;

import java.util.Date;

public class ChatCommonResponse {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class ChatRoomResponseDto{
        private Long roomId;
        private Long user2Id;
        private String name;
        private String picture;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ChatRoomInfoResponseDto{
        private Long roomId;
        private Long user2Id;
        private String name;
        private String picture;
        private CarSaleInfoDto carSaleInfoDto;
        //예측가격은 ml서버에 요청
    }

}
