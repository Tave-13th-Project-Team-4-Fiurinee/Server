package com.example.fiurinee.domain.image.dto;

import java.net.URL;

public class ImageResponseDTO {
    private URL flowerImageUrl;
    private URL backgroundImageUrl;

    // Getters and setters
    public URL getFlowerImageUrl() {
        return flowerImageUrl;
    }

    public void setFlowerImageUrl(URL flowerImageUrl) {
        this.flowerImageUrl = flowerImageUrl;
    }

    public URL getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public void setBackgroundImageUrl(URL backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }
}
