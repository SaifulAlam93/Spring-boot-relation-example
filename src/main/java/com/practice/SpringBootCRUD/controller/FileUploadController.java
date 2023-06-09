package com.practice.SpringBootCRUD.controller;


import com.practice.SpringBootCRUD.dto.FileUploadDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Slf4j
@SuppressWarnings("unused")
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadController {

    @PostMapping(value = "simple-form-upload-mvc", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> handleFileUploadForm(@RequestPart("file") MultipartFile file) {

        log.info("handling request parts: {}", file);

        try {
//            File f = new ClassPathResource("").getFile();

            Path uploadPath = Paths.get("E://img/");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            final Path path = Paths.get("E://img" + File.separator + "static" + File.separator + "image");

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Path filePath = path.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename())
                    .toUriString();

            FileUploadDto fileUploadDto = new FileUploadDto();
            fileUploadDto.setFilename(file.getOriginalFilename());
            fileUploadDto.setFileUri(fileUri);

            return ok().body(fileUploadDto);
        } catch (IOException e) {
//            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
