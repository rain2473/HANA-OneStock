package com.hanaonestock.member.service;

import com.hanaonestock.member.model.dao.MemberMapper;
import com.hanaonestock.member.model.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService{
    private MemberMapper memberMapper;
    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper){
        this.memberMapper = memberMapper;
    }
    @Override
    public List<Member> getAllMember(){
        return memberMapper.getAllMember();
    }

    @Override
    public boolean insertMember(Member member){
        try {
            memberMapper.insertMember(member);
            return true;
        } catch (Exception e) {
            // insert 작업 실패
            return false;
        }
    }
    @Override
    public int selectOneMember(String id) {
        try {
            return memberMapper.selectOneMember(id);
        } catch (Exception e) {
            return 0;
        }
    }
    @Override
    public boolean updateMember(String id, Member updatedMember) {
        try {
            memberMapper.updateMember(id);
            return true;
        } catch (Exception e) {
            // insert 작업 실패
            return false;
        }
    }
    @Override
    public boolean deleteMember(String id) {
        try {
            memberMapper.deleteMember(id);
            return true;
        } catch (Exception e) {
            // insert 작업 실패
            return false;
        }
    }

    @Override
    public int loginMember(HashMap<String, String> loginData) {
        try {
            return memberMapper.loginMember(loginData);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int selectNameAndEmail(HashMap<String, String> kakaoLogin) {
        try {
            return memberMapper.selectNameAndEmail(kakaoLogin);
        } catch (Exception e) {
            return 0;
        }
    }
}
