package howmuchcar.infra.service

import org.springframework.web.multipart.MultipartFile

interface ObjectStorageService {
    fun uploadFile(image: MultipartFile):String
}