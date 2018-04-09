package com.thoughtworks.felix.training.picture.interfaces.rest;


import com.thoughtworks.felix.training.picture.Routes;
import com.thoughtworks.felix.training.picture.domain.Image;
import com.thoughtworks.felix.training.picture.domain.ImagePathRepository;
import com.thoughtworks.felix.training.picture.domain.ImageRepository;
import com.thoughtworks.felix.training.picture.interfaces.rest.dto.BatchResource;
import com.thoughtworks.felix.training.picture.interfaces.rest.dto.ErrorDTO;
import com.thoughtworks.felix.training.picture.interfaces.rest.dto.ImageDTO;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/images")
public class ImagesApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImagesApi.class);

    private static String IMAGE_FOLDER = "/tmp/";

    @Autowired
    private ImagePathRepository imagePathRepository;

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping
    public ResponseEntity createImage(@RequestParam("file") MultipartFile uploadFile) {
        if (uploadFile.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        imageRepository.saveImage(uploadFile);
        final Image saved = imagePathRepository.save(new Image(uploadFile.getOriginalFilename()));
        return ResponseEntity.created(Routes.imageUri(saved)).build();
    }

    @GetMapping
    public ResponseEntity<BatchResource<ImageDTO>> listImages() {
        final List<Image> all = imagePathRepository.findAll();
        final List<ImageDTO> dtos = all.stream().map(x -> new ImageDTO(x.getName(), "images/" + x.getId())).collect(toList());
        return ResponseEntity.ok(BatchResource.builder().withData(dtos).withUrl("/images").build());
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> showImage(@PathVariable Integer id) throws IOException {
        final Optional<Image> optionalImage = imagePathRepository.findById(id.longValue());
        if (!optionalImage.isPresent()) {
            throw new NotFoundException(new ErrorDTO("404", "Image id not exist"));
        }
        FileInputStream stream = new FileInputStream(IMAGE_FOLDER + optionalImage.get().getName());
        final byte[] image = IOUtils.toByteArray(stream);
        return ResponseEntity.ok(image);
    }

}
