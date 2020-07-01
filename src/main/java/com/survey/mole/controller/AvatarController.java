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

    private FileStorageService fileStorageService;

    @Autowired
    public AvatarController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }



    @PostMapping(value = "/image/{root}/", consumes = "multipart/form-data")
    public Boolean saveImage(@PathVariable("root") String root, @RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        Boolean isSaved = fileStorageService.saveImage(root,id, file);
        return isSaved;
    }


    @GetMapping("/image/{root}/")
    public InputStreamResource downloadImage(@PathVariable("root") String root,@RequestParam("id") Long id) {
        return fileStorageService.loadImage(root,id);
    }

    @DeleteMapping("/image/{root}/{id}")
    public Boolean deleteFile(@PathVariable("root") String root, @PathVariable("id") Long id){
        return fileStorageService.deleteFile(root,id);
    }

}
