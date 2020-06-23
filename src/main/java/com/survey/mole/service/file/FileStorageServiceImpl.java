package com.survey.mole.service.file;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;


@Service
public class FileStorageServiceImpl implements FileStorageService {


    @Override
    public Boolean saveFile(String filePath, MultipartFile file) {
        File fileIn = new File(filePath);
        return saveFile(fileIn,file);
    }

    @Override
    public InputStreamResource loadFile(String filePath) {
        try {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(filePath));
            return resource;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void regStorage(String... paths) {
        Arrays.stream(paths).forEach(path->{
            File file = new File(path);
            file.mkdirs();
        });
    }

    private Boolean saveFile(File fileIn, MultipartFile file) {
        try (InputStream source = file.getInputStream();
             OutputStream dest = new FileOutputStream(fileIn.getAbsolutePath())) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = source.read(buffer)) > 0) {
                dest.write(buffer, 0, length);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
