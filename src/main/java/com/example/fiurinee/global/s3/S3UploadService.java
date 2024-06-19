package com.example.fiurinee.global.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.util.List;

@Service
public class S3UploadService {
    private final AmazonS3 s3Client;
    private final String bucketName;

    public S3UploadService(AmazonS3 s3Client, @Value("${AWS_BUCKET}") String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    public void uploadImages(List<File> files) {
        for (File file : files) {
            String key = "images/" + file.getName();
            s3Client.putObject(new PutObjectRequest(bucketName, key, file));
        }
    }

    public URL getImageUrl(String key) {
        return s3Client.getUrl(bucketName, key);
    }
}
