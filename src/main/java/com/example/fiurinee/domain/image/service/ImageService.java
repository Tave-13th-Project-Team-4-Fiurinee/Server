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
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 회원 ID입니다."));
        int flowerCode = member.getProfileImage();
        String backgroundColor = member.getBackgroundColor();

        String flowerImagePath = "images/flower_" + flowerCode + ".png";

        if (!doesObjectExist(flowerImagePath)) {
            throw new IllegalArgumentException("이미지 파일이 S3에 존재하지 않습니다.");
        }

        URL flowerImageUrl = s3Client.getUrl(bucketName, flowerImagePath);

        ImageResponseDTO imageResponseDTO = new ImageResponseDTO();
        imageResponseDTO.setFlowerImageUrl(flowerImageUrl);
        imageResponseDTO.setBackgroundColor(backgroundColor != null ? backgroundColor : "#FFFFFF"); // 기본값은 흰색

        return imageResponseDTO;
    }

    public void updateProfileImage(Long memberId, int flowerCode, String backgroundColor) {
        String flowerImagePath = "images/flower_" + flowerCode + ".png";

        if (!doesObjectExist(flowerImagePath)) {
            throw new IllegalArgumentException("이미지 파일이 S3에 존재하지 않습니다.");
        }

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 회원 ID입니다."));
        member.updateProfileImage(flowerCode);
        member.updateBackgroundColor(backgroundColor != null ? backgroundColor : "#FFFFFF"); // 기본값은 흰색
        memberRepository.save(member);
    }

    private boolean doesObjectExist(String path) {
        return s3Client.doesObjectExist(bucketName, path);
    }

}
