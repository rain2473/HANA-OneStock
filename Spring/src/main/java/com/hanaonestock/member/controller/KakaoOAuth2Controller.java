package com.hanaonestock.member.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class KakaoOAuth2Controller {
    @GetMapping("/loginInfo")
    //현재 사용자의 인증 정보를 나타내며, 주로 사용자가 인증되었는지 확인하거나 사용자의 권한을 확인하는 데 사용
    public String getJson(Authentication authentication) {
        //현재 인증된 사용자의 주요 정보를 반환
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        return attributes.toString();
    }
}
