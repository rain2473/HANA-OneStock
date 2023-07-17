package com.hanaonestock.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanaonestock.member.model.dto.InvestInfo;
import com.hanaonestock.member.model.dto.Member;
import com.hanaonestock.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
    @RequestMapping("/mypage")
    public ModelAndView mypage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("mypage");
        return mav;
    }
    @RequestMapping("/mypage2")
    public ModelAndView mypage2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        ModelAndView mav = new ModelAndView();
        Member memberInfo = memberService.selectNameOfMember(id);
        InvestInfo investInfo = memberService.selectInvestInfo(id);

        mav.addObject("member",memberInfo);
        mav.addObject("invest",investInfo);
        mav.setViewName("mypage2");
        return mav;
    }

    @RequestMapping("/join")
    public ModelAndView join() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("join");
        return mav;
    }
    @RequestMapping("/recommend")
    public ModelAndView recommend() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        return mav;
    }
    @RequestMapping("/index_login")
    public ModelAndView index_login(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        Member m = memberService.selectNameOfMember(id);
        mav.addObject("provider",m.getProvider());
        mav.addObject("id",id);
        mav.setViewName("index_login");
        return mav;
    }

    @RequestMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("dashboard");
        return mav;
    }


    @ResponseBody
    @GetMapping(value = "/api/main")
    public ResponseEntity<String> index(Model model) {
        List<Member> memberList = memberService.getAllMember();
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(memberList);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Error processing JSON", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        model.addAttribute("memberList", memberList);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/idCheck")
    public ResponseEntity<
            Map<String, Boolean>> idCheck(@RequestParam("id") String id) {
        // id 중복 체크
        boolean isExists = memberService.selectOneMember(id) > 0;
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", isExists);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/updateMember")
    public ResponseEntity<String> updateMember(@RequestBody Member member) {

        try {
            Member updateM = memberService.selectNameOfMember(member.getId());
            updateM.setPassword(member.getPassword());
            updateM.setPhoneNumber(member.getPhoneNumber());
            updateM.setGoal(member.getGoal());

            memberService.updateMember(updateM);
            memberService.updateInvest(updateM);
            return ResponseEntity.ok("회원 수정 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 수정 실패");
        }
    }

    @PostMapping("/loginMember")
    public ResponseEntity<String> loginMember(@RequestBody HashMap<String, String> loginData, HttpServletRequest request) {
        Member loginMember = memberService.loginMember(loginData);
        HttpSession session = request.getSession();
        if (loginMember!=null) {
            session.setAttribute("name",loginMember.getName());
            session.setAttribute("id",loginMember.getId());
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그인 실패");
        }
    }

    @PostMapping(value = "/insertMember")
    @ResponseBody
    public String insertMember(@RequestBody Member member) {
        try {
            memberService.insertMember(member);
            memberService.insertInvestInfo(member);
            return "회원 등록 성공";
        } catch (Exception e) {
            return "회원 등록 실패";
        }
    }

    @RequestMapping(value="/logoutMember")
    public ModelAndView deleteMember(HttpSession session) {
        String id = (String) session.getAttribute("id");
        ModelAndView mav = new ModelAndView();
        session.invalidate();
//        int isSuccess = memberService.deleteMember(id);
//        if(isSuccess!=-1) {
//            session.invalidate();
//            mav.addObject("msg", "로그아웃 성공");
//        }else {
//            mav.addObject("msg", "로그아웃 실패");
//
//        }
        mav.addObject("msg", "로그아웃 성공");
        mav.addObject("loc","/");
        mav.setViewName("/common/message");
        return mav;
    }
}
