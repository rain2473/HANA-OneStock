package com.hanaonestock.member.controller;

import com.hanaonestock.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/oauth")
public class KakaoOAuth2Controller {
    private final MemberService memberService;
    @Autowired
    public KakaoOAuth2Controller(MemberService memberService) {this.memberService = memberService;}

    @GetMapping("/loginInfo")
    //현재 사용자의 인증 정보를 나타내며, 주로 사용자가 인증되었는지 확인하거나 사용자의 권한을 확인하는 데 사용
    public ModelAndView getJson(Authentication authentication, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        //현재 인증된 사용자의 주요 정보를 반환
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        HashMap<String, String> kakaoLogin = new HashMap<>();
        HttpSession session = request.getSession();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        String provider = oauthToken.getAuthorizedClientRegistrationId();

        String name = attributes.get("name").toString();
        String email = attributes.get("email").toString();

        kakaoLogin.put("name",name);
        kakaoLogin.put("email",email);

        if(memberService.selectNameAndEmailOfMember(kakaoLogin)==0){
            mav.addObject("message", "가입이 필요합니다.");
            mav.addObject("name", name);
            mav.addObject("email", email);
            mav.addObject("provider",provider);
            mav.setViewName("join");
        }else {
            mav.addObject("msg", "이미 가입된 회원입니다.");
            mav.addObject("loc", "/");
            mav.setViewName("/common/message");
        }

        session.setAttribute("name", name);
        return mav;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Authentication authentication) {

        if (authentication != null && authentication.isAuthenticated()) {
            // OAuth2 인증 토큰인 경우
            if (authentication instanceof OAuth2AuthenticationToken) {
                OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
                String provider = oauthToken.getAuthorizedClientRegistrationId();
                if ("kakao".equals(provider)) {
                    // 세션 무효화
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        session.invalidate();
                    }
                    // 카카오 계정 로그아웃 URL로 리다이렉트
                    return "redirect:https://kauth.kakao.com/oauth/logout?client_id=3e04da871b237fb6169d1ec2b50af7fb&logout_redirect_uri=http%3A%2F%2Flocalhost%3A8080%2F";

                }
            }
            // 일반적인 로그아웃 처리
        }
        return "redirect:/";
    }

}
