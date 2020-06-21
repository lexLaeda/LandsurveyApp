package com.survey.mole.service.file;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

    Boolean saveFile(String filePath, MultipartFile file);

    InputStreamResource loadFile(String filePath);

    void regStorage(String ... paths);
}
