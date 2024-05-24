package com.example.fiurinee;

import com.amazonaws.services.s3.AmazonS3;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.String;

import java.net.URL;

@RestController
public class S3Controller {

    private final AmazonS3 s3Client;
    private final String bucketName;

    public S3Controller(AmazonS3 s3Client, @Value("${cloud.aws.s3.bucket}") String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    @GetMapping("/image")
    public ResponseEntity<URL> getImage(){
        URL img = s3Client.getUrl(bucketName, "fiurinnn");

        return ResponseEntity.ok(img);
    }
}
