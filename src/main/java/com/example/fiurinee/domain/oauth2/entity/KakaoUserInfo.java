package com.example.fiurinee.domain.oauth2.entity;

import java.util.Map;

public class KakaoUserInfo {
    public static String socialId;
    public static Map<String, Object> account;
    public static Map<String, Object> profile;
    public static String email;

    public KakaoUserInfo(Map<String, Object> attributes) {
        socialId = String.valueOf(attributes.get("id"));
        account = (Map<String, Object>) attributes.get("kakao_account");
        profile = (Map<String, Object>) account.get("profile");
        email = (String) account.get("email");
    }

    public String getSocialId() {
        return socialId;
    }

    public String getName() {
        return String.valueOf(profile.get("nickname"));
    }

    public String getEmail(){
        return email;
    }
}
