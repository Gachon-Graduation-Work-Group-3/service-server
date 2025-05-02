package howmuchcar.infra.serviceImpl

import com.oracle.bmc.ConfigFileReader
import com.oracle.bmc.ConfigFileReader.ConfigFile
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider
import com.oracle.bmc.objectstorage.ObjectStorage
import com.oracle.bmc.objectstorage.ObjectStorageClient
import com.oracle.bmc.objectstorage.model.CreatePreauthenticatedRequestDetails
import com.oracle.bmc.objectstorage.requests.CreatePreauthenticatedRequestRequest
import com.oracle.bmc.objectstorage.requests.PutObjectRequest
import howmuchcar.infra.properties.ObjectStorageProperties
import howmuchcar.infra.service.ObjectStorageService
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.nio.file.Paths
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Service
@RequiredArgsConstructor
class ObjectStorageServiceImpl(
    private val properties: ObjectStorageProperties
):ObjectStorageService {

    @Value("\${spring.oci.config.path}")
    lateinit var projectConfigPath: String

    override fun uploadFile(image: MultipartFile):String {
        val client = getObjectStorageClient()
        val objectName = UUID.randomUUID().toString() + "-" + image.originalFilename
        val request = PutObjectRequest.builder()
            .bucketName(properties.bucket)
            .namespaceName(properties.namespace)
            .objectName(objectName)
            .putObjectBody(ByteArrayInputStream(image.bytes))
            .contentType(image.contentType)
            .build()

        val response = client.putObject(request)

        return createPreAuthenticationUrl(client, objectName)
    }

    private fun getObjectStorageClient():ObjectStorage {
        val config: ConfigFile = ConfigFileReader.parse(projectConfigPath, "DEFAULT")
        val provider = ConfigFileAuthenticationDetailsProvider(config)

        return ObjectStorageClient.builder()
            .region(properties.region)
            .build(provider)
    }

    private fun createPreAuthenticationUrl(client : ObjectStorage, objectName: String):String {
        val details = CreatePreauthenticatedRequestDetails.builder()
            .name("public-access-$objectName")
            .accessType(CreatePreauthenticatedRequestDetails.AccessType.ObjectRead)
            .objectName(objectName)
            .timeExpires(Date.from(Instant.now().plus(365, ChronoUnit.DAYS))) // 1년 후 만료
            .build()

        val request = CreatePreauthenticatedRequestRequest.builder()
            .namespaceName(properties.namespace)
            .bucketName(properties.bucket)
            .createPreauthenticatedRequestDetails(details)
            .build();

        val response = client.createPreauthenticatedRequest(request)
        client.close()

        return "https://objectstorage.ap-chuncheon-1.oraclecloud.com" + response.preauthenticatedRequest.accessUri
    }
}