package com.example.fiurinee.domain.image.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.example.fiurinee.domain.image.controller.api.ImageApi;
import com.example.fiurinee.domain.image.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.Map;

@RestController
public class ImageController implements ImageApi {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public ResponseEntity<Map<String, URL>> getImage(@PathVariable Long id) {
        Map<String, URL> imgUrls = imageService.getImageUrls(id);
        return ResponseEntity.ok(imgUrls);
    }

    @Override
    public ResponseEntity<Void> updateImage(@PathVariable Long id, @RequestBody Map<String, Integer> imageRequest){
        int flowerCode = imageRequest.get("flowerCode");
        int backgroundCode = imageRequest.get("backgroundCode");
        imageService.updateProfileImage(id, flowerCode, backgroundCode);
        return ResponseEntity.noContent().build();
    }
}
