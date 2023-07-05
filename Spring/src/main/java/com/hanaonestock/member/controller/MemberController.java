package com.hanaonestock.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanaonestock.member.model.dto.Member;
import com.hanaonestock.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {this.memberService = memberService;}
    @ResponseBody
    @GetMapping(value="/main")
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
}
