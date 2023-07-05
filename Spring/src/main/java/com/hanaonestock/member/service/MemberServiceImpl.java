package com.hanaonestock.member.service;

import com.hanaonestock.member.model.dao.MemberMapper;
import com.hanaonestock.member.model.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
