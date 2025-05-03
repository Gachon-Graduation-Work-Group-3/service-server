package howmuchcar.application.port.out.infra

import org.springframework.web.multipart.MultipartFile


interface BucketPort {
    fun uploadFile(image: MultipartFile):String
}