package com.example.fiurinee.domain.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.example.fiurinee.domain.image.dto.ImageResponseDTO;
import com.example.fiurinee.domain.member.entity.Member;
import com.example.fiurinee.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@Service
public class ImageService {
    private final MemberRepository memberRepository;
    private final AmazonS3 s3Client;
    private final String bucketName;

    public ImageService(MemberRepository memberRepository, AmazonS3 s3Client, @Value("${AWS_BUCKET}") String bucketName) {
        this.memberRepository = memberRepository;
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    public ImageResponseDTO getImageUrls(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));
        int imageCode = member.getProfileImage();

        int flowerCode = (imageCode / 10) * 10;
        int backgroundCode = imageCode % 10;

        String flowerImagePath = "images/flower_" + flowerCode + ".png";
        String backgroundImagePath = "images/background_" + backgroundCode + ".png";

        if (!doesObjectExist(flowerImagePath) || !doesObjectExist(backgroundImagePath)) {
            throw new IllegalArgumentException("One or more image files do not exist in S3");
        }

        URL flowerImageUrl = s3Client.getUrl(bucketName, flowerImagePath);
        URL backgroundImageUrl = s3Client.getUrl(bucketName, backgroundImagePath);

        ImageResponseDTO imageResponseDTO = new ImageResponseDTO();
        imageResponseDTO.setFlowerImageUrl(flowerImageUrl);
        imageResponseDTO.setBackgroundImageUrl(backgroundImageUrl);


        return imageResponseDTO;
    }

    public void updateProfileImage(Long memberId, int flowerCode, int backgroundCode) {
        String flowerImagePath = "images/flower_" + (flowerCode * 10) + ".png";
        String backgroundImagePath = "images/background_" + backgroundCode + ".png";

        if (!doesObjectExist(flowerImagePath) || !doesObjectExist(backgroundImagePath)) {
            throw new IllegalArgumentException("One or more image files do not exist in S3");
        }

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));
        int profileImageCode = flowerCode * 10 + backgroundCode;
        member.updateProfileImage(profileImageCode);
        memberRepository.save(member);
    }

    private boolean doesObjectExist(String path) {
        return s3Client.doesObjectExist(bucketName, path);
    }

}
