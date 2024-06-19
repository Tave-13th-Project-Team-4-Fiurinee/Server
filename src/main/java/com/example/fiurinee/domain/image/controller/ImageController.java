package com.example.fiurinee.domain.image.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.example.fiurinee.domain.image.controller.api.ImageApi;
import com.example.fiurinee.domain.image.dto.ImageRequestDTO;
import com.example.fiurinee.domain.image.dto.ImageResponseDTO;
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
    public ResponseEntity<ImageResponseDTO> getImage(@PathVariable Long id) {
        ImageResponseDTO imgUrls = imageService.getImageUrls(id);
        return ResponseEntity.ok(imgUrls);
    }

    @Override
    public ResponseEntity<Void> updateImage(@PathVariable Long id, @RequestBody ImageRequestDTO imageRequest){
        int flowerCode = imageRequest.getFlowerCode();
        int backgroundCode = imageRequest.getBackgroundCode();
        imageService.updateProfileImage(id, flowerCode, backgroundCode);
        return ResponseEntity.noContent().build();
    }
}
