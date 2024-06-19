package com.example.fiurinee.global.s3;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Component
public class ImageUploadInitializer {
    @Autowired
    private S3UploadService s3UploadService;

    @PostConstruct
    public void init() {
        List<File> files = Arrays.asList(
                new File("src/main/resources/static/images/flower_10.png"),
                new File("src/main/resources/static/images/flower_20.png"),
                new File("src/main/resources/static/images/flower_30.png"),
                new File("src/main/resources/static/images/flower_40.png"),
                new File("src/main/resources/static/images/flower_50.png"),
                new File("src/main/resources/static/images/background_1.png"),
                new File("src/main/resources/static/images/background_2.png"),
                new File("src/main/resources/static/images/background_3.png"),
                new File("src/main/resources/static/images/background_4.png")
        );

        s3UploadService.uploadImages(files);
    }
}
