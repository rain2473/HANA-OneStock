package com.hanaonestock.member.model.dao;

import com.hanaonestock.member.model.dto.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
@Mapper
public interface MemberMapper {
    List<Member> getAllMember();
    void insertMember(Member member);
    int selectOneMember(String id);
    boolean updateMember(String id);
    int     deleteMember(String id);
    int loginMember(HashMap<String, String> loginData);
    int selectNameAndEmail(HashMap<String, String> kakaoLogin);
    Member selectNameOfMember(String id);
}
