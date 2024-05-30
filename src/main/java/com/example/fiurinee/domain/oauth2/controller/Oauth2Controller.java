package com.example.fiurinee.domain.oauth2.controller;

import com.example.fiurinee.domain.oauth2.service.Oauth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class Oauth2Controller {
    private final Oauth2Service oauth2Service;

    @Autowired
    public Oauth2Controller(Oauth2Service oauth2Service) {
        this.oauth2Service = oauth2Service;
    }

    @GetMapping("/oauth2/authorization/google")
    public String googleAuthorization() {
        String authorizationRequest = "https://accounts.google.com/o/oauth2/auth" +
                "?client_id=${spring.google.client_id}" +
                "&redirect_uri=login/oauth2/code/google" +
                "&response_type=code" +
                "&scope=email%20profile";
        return authorizationRequest;
    }

    @GetMapping("login/oauth2/code/google")
    public String googleCallback(@RequestParam("code") String code) {
        // 이 부분에서 받은 code를 이용해 토큰을 요청합니다.
        return "redirect:/api/v1/oauth2/google?code=" + code;
    }

    @GetMapping("/api/v1/oauth2/google")
    public String getGoogleInfo(@RequestParam(value = "code") String authCode) {
        return oauth2Service.getGoogleInfo(authCode);
    }

}
