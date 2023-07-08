package com.hanaonestock.member.service;

import com.hanaonestock.member.model.dto.Member;

import java.util.List;

public interface MemberService {
    public List<Member> getAllMember();
    boolean insertMember(Member member);
    int selectOneMember(int id);
    boolean updateMember(int id, Member updatedMember);
    boolean deleteMember(int id);
}
