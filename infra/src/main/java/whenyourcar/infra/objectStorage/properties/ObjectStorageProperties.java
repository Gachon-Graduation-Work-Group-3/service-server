package whenyourcar.infra.objectStorage.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.oci.object-storage")
public class ObjectStorageProperties {
    private String region;
    private String namespace;
    private String bucket;
}