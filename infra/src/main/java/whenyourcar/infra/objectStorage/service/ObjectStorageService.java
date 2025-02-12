package whenyourcar.infra.objectStorage.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ObjectStorageService {
    public String uploadFile(MultipartFile image) throws Exception;
}
