package com.hanaonestock.member.service;

import com.hanaonestock.member.model.dto.Member;

import java.util.HashMap;
import java.util.List;

public interface MemberService {
    public List<Member> getAllMember();

    int selectOneMember(String id);

    boolean updateMember(String id, Member updatedMember);
    int deleteMember(String id);
    int loginMember(HashMap<String, String> loginData);
    int selectNameAndEmailOfMember(HashMap<String, String> kakaoLogin);
    void insertMember(Member member);
    Member selectNameOfMember(String id);
    void insertInvestInfo(Member member);
}

