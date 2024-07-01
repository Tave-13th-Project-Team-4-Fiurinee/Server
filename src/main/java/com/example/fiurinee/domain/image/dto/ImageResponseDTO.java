package com.example.fiurinee.domain.image.dto;

import java.net.URL;

public class ImageResponseDTO {
    private URL flowerImageUrl;
    private String backgroundColor;

    // Getters and setters
    public URL getFlowerImageUrl() {
        return flowerImageUrl;
    }

    public void setFlowerImageUrl(URL flowerImageUrl) {
        this.flowerImageUrl = flowerImageUrl;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
