package com.thoughtworks.felix.training.picture.interfaces.rest;


import com.thoughtworks.felix.training.picture.Routes;
import com.thoughtworks.felix.training.picture.domain.Image;
import com.thoughtworks.felix.training.picture.domain.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/images")
public class ImagesApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImagesApi.class);

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "/tmp/";

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping
    public ResponseEntity createImage(@RequestParam("file") MultipartFile uploadFile) {
        if (uploadFile.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        saveImage(uploadFile);
        final Image saved = imageRepository.save(new Image(uploadFile.getOriginalFilename()));

        return ResponseEntity.created(Routes.imageUri(saved)).build();
    }

    private void saveImage(@RequestParam("file") MultipartFile uploadFile) {
        try {
            byte[] bytes = uploadFile.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + uploadFile.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
