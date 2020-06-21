package com.survey.mole.controller;

import com.survey.mole.service.file.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class AvatarController {

    private static final String ROOT = "app/image/";

    private static final String EMPLOYEE = "employee/";

    private static final String DEPARTMENT = "department/";

    private static final String DEFAULT = "default/";

    private static final String SUF = ".png";

    private static final String DEFAULT_IMAGE = ROOT + DEFAULT + "default" + SUF;

    private FileStorageService fileStorageService;

    @Autowired
    public AvatarController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostConstruct
    public void initStorage() {
        this.fileStorageService.regStorage(ROOT + EMPLOYEE, ROOT + DEPARTMENT, ROOT + DEFAULT);
    }

    @PostMapping(value = "/image/", consumes = "multipart/form-data")
    public Boolean saveEmployeeImage(@RequestParam("file") MultipartFile file, @RequestParam("dir") String dir, @RequestParam("id") String id) {
        Boolean isSaved = fileStorageService.saveFile(ROOT + dir + "/" + id + SUF, file);
        return isSaved;
    }


    @GetMapping("/image/")
    public InputStreamResource downloadImage(@RequestParam("id") Long id, @RequestParam("dir") String dir) {
        if (id == null) {
            return fileStorageService.loadFile(DEFAULT_IMAGE);
        }

        InputStreamResource resource = fileStorageService.loadFile(ROOT + dir + "/" + id + SUF);

        if (resource == null) {
            return fileStorageService.loadFile(DEFAULT_IMAGE);
        }

        return resource;
    }
}
