package com.hanaonestock.member.service;

import com.hanaonestock.member.model.dto.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberService {
    public List<Member> getAllMember();

    int selectOneMember(String id);

    boolean updateMember(String id, Member updatedMember);

    boolean deleteMember(String id);

    int loginMember(HashMap<String, String> loginData);

    int selectNameAndEmail(HashMap<String, String> kakaoLogin);

    void insertMember(Member member);
}

