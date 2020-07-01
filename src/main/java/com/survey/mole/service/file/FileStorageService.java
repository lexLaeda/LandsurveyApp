package com.survey.mole.service.file;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileStorageService {

    InputStreamResource loadFile(String filePath) throws FileNotFoundException;

    InputStreamResource loadImage(String root, Long id);
    Boolean saveImage(String root, Long id, MultipartFile file);

    Boolean deleteFile(String root, Long id);
}
