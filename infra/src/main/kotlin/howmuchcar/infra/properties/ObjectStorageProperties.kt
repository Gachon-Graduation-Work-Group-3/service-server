package howmuchcar.infra.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@Component
@ConfigurationProperties(prefix = "spring.oci.object-storage")
data class ObjectStorageProperties(
    var region: String? = null,
    var namespace: String? = null,
    var bucket: String? = null
)