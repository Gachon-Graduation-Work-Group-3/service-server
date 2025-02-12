package whenyourcar.infra.objectStorage.serviceImpl;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.ConfigFileReader.*;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.model.CreatePreauthenticatedRequestDetails;
import com.oracle.bmc.objectstorage.model.CreatePreauthenticatedRequestDetails.*;
import com.oracle.bmc.objectstorage.model.PreauthenticatedRequest;
import com.oracle.bmc.objectstorage.requests.CreatePreauthenticatedRequestRequest;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import com.oracle.bmc.objectstorage.responses.CreatePreauthenticatedRequestResponse;
import com.oracle.bmc.objectstorage.responses.PutObjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import whenyourcar.infra.objectStorage.properties.ObjectStorageProperties;
import whenyourcar.infra.objectStorage.service.ObjectStorageService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ObjectStorageServiceImpl implements ObjectStorageService {
    private final ObjectStorageProperties properties;
    private final String PROJECT_CONFIG_PATH = Paths.get(System.getProperty("user.dir"), ".oci", "config").toString();

    private ObjectStorage getObjectStorageClient() throws IOException {
        ConfigFile config = ConfigFileReader.parse(PROJECT_CONFIG_PATH, "DEFAULT");
        AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(config);

        return ObjectStorageClient.builder()
                .region(properties.getRegion())
                .build(provider);
    }

    public String uploadFile(MultipartFile image) throws Exception {
        ObjectStorage objectStorageClient = getObjectStorageClient();
        String objectName = UUID.randomUUID() + "-" + image.getOriginalFilename();
        PutObjectRequest request = PutObjectRequest.builder()
            .bucketName(properties.getBucket())
            .namespaceName(properties.getNamespace())
            .objectName(objectName)
            .putObjectBody(new ByteArrayInputStream(image.getBytes()))
            .contentType(image.getContentType())
            .build();

        PutObjectResponse response = objectStorageClient.putObject(request);
        System.out.println(response);

        return createPreAuthenticatedUrl(objectStorageClient, objectName);
    }

    private String createPreAuthenticatedUrl(ObjectStorage objectStorageClient, String objectName) throws Exception {
        CreatePreauthenticatedRequestDetails details = CreatePreauthenticatedRequestDetails.builder()
                .name("public-access-" + objectName)
                .accessType(AccessType.ObjectRead)
                .objectName(objectName)
                .timeExpires(Date.from(Instant.now().plus(365, ChronoUnit.DAYS)))  // 1년 후 만료

                .build();

        CreatePreauthenticatedRequestRequest request = CreatePreauthenticatedRequestRequest.builder()
                .namespaceName(properties.getNamespace())
                .bucketName(properties.getBucket())
                .createPreauthenticatedRequestDetails(details)
                .build();

        CreatePreauthenticatedRequestResponse response = objectStorageClient.createPreauthenticatedRequest(request);
        objectStorageClient.close();

        return "https://objectstorage.ap-chuncheon-1.oraclecloud.com"+response.getPreauthenticatedRequest().getAccessUri();
    }
}