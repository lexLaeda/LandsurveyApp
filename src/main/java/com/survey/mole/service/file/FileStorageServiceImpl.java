package com.survey.mole.service.file;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Service
public class FileStorageServiceImpl implements FileStorageService {

    private static final String ROOT = "app/image/";

    private static final String EMPLOYEE = "employee/";

    private static final String DEPARTMENT = "department/";

    private static final String DEFAULT = "default/";

    private static final String SUF = ".png";

    private static final String DEFAULT_IMAGE = ROOT + DEFAULT + "default" + SUF;

    Logger LOG = LoggerFactory.getLogger(FileStorageServiceImpl.class);

    private Set<String> directories = new HashSet<>();

    @PostConstruct
    public void initStorage(){
        createDirectory(ROOT + DEFAULT);
    }

    private void createDirectory(String path){
        File file = new File(path);

        boolean success = file.mkdirs();

        if (success || file.exists()){
            LOG.info("\"path\" directory initiated");
        } else {
            LOG.error("Fail to initiate \"path\" directory");
        }
    }

    @Override
    public InputStreamResource loadFile(String filePath) {
        try {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(filePath));
            return resource;
        } catch (FileNotFoundException e) {
            LOG.error("Can't load data from hard drive " + filePath);
            return null;
        }
    }

    @Override
    public InputStreamResource loadImage(String directory, Long id) {
        String filePath = ROOT + directory + "/" + id + SUF;
        File file = new File(filePath);
        if (!directories.contains(directory) && file.exists()){
            return loadFile(filePath);
        } else {
            InputStreamResource inputStreamResource = loadFile(DEFAULT_IMAGE);
            return inputStreamResource;
        }
    }

    @Override
    public Boolean saveImage(String directory, Long id, MultipartFile file) {

        if (!directories.contains(directory)){
            createDirectory(ROOT + directory + "/");
            directories.add(directory);
        }

        String filePath = ROOT + directory + "/" + id + SUF;
        File fileIn = new File(filePath);

        return saveFile(fileIn,file);
    }

    @Override
    public Boolean deleteFile(String directory, Long id) {
        File file = new File(ROOT + directory + "/" + id + SUF);
        if (file.exists()){
            try {
                FileUtils.forceDelete(file);
                LOG.info("File in directory /" + directory + "/ with " + id + " deleted");
                return file.exists();
            } catch (IOException e) {
                LOG.error("Can't delete file with id " + id + " in directory " + directory);
                return false;
            }
        }
        return false;
    }

    private Boolean saveFile(File fileIn, MultipartFile file) {
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),fileIn);
            LOG.info("File " + fileIn.getName() + " saved.");
            return true;
        } catch (IOException e) {
            LOG.error(e.getMessage());
            return false;
        }
    }
}
