package com.example.demo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ImageController {

    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getImage() {
        try {
            // -> file placed at src/main/resources/images/sample.png
            ClassPathResource imgFile = new ClassPathResource("static/image1.PNG");

            if (!imgFile.exists()) {
                // helpful debug: return 404 if resource not found
                return ResponseEntity.notFound().build();
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            InputStreamResource resource = new InputStreamResource(imgFile.getInputStream());
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (IOException e) {
            // log (or print) stacktrace during debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

