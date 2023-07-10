package com.hanaonestock.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanaonestock.member.model.dto.Member;
import com.hanaonestock.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {this.memberService = memberService;}

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index"); //jsp(html)로 갈때는 setViewName // class로 갈때는 setView
        return mav;
    }

    @RequestMapping("/join")
    public ModelAndView join() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("join_1"); //jsp(html)로 갈때는 setViewName // class로 갈때는 setView
        return mav;
    }

    @ResponseBody
    @GetMapping(value="/api/main")
    public ResponseEntity<String>  index(Model model) {
        List<Member> memberList = memberService.getAllMember();
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(memberList);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Error processing JSON", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        model.addAttribute("memberList",memberList);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/insertMember")
    public ResponseEntity<String> insertMember(@ModelAttribute Member member) {
        // 회원 등록
        boolean isSuccess = memberService.insertMember(member);
        if (isSuccess) {
            RedirectView redirectView = new RedirectView("index");
            redirectView.setStatusCode(HttpStatus.SEE_OTHER);
            return ResponseEntity.ok("회원 등록 성공");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 등록 실패");
        }
    }

    @ResponseBody
    @RequestMapping(value="/idCheck")
    public ResponseEntity<Map<String, Boolean>> idCheck(@RequestParam("id") String id) {
        // id 중복 체크
        boolean isExists = memberService.selectOneMember(id) > 0;
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", isExists);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/updateMember/{id}")
    public ResponseEntity<String> updateMember(@PathVariable("id") String id, @RequestBody Member updatedMember) {
        // id에 해당하는 회원을 찾아서 업데이트
        boolean isSuccess = memberService.updateMember(id, updatedMember);

        if (isSuccess) {
            return ResponseEntity.ok("회원 수정 성공");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 수정 실패");
        }
    }
    @PostMapping("/deleteMember/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable("id") String id) {
        // id에 해당하는 회원을 삭제
        boolean isSuccess = memberService.deleteMember(id);
        if (isSuccess) {
            return ResponseEntity.ok("회원 삭제 성공");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 삭제 실패");
        }
    }

    @PostMapping("/loginMember")
    public ResponseEntity<String> loginMember(@RequestBody HashMap<String, String> loginData) {
        boolean isSuccess = memberService.loginMember(loginData) > 0;
        if (isSuccess) {
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그인 실패");
        }
    }
}
