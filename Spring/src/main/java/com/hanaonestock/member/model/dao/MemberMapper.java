package com.hanaonestock.member.model.dao;

import com.hanaonestock.member.model.dto.InvestInfo;
import com.hanaonestock.member.model.dto.Member;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    List<Member> getAllMember();
    void insertMember(Member member);
    int selectOneMember(String id);
    boolean updateMember(String id);
    int deleteMember(String id);
    Member loginMember(HashMap<String, String> loginData);
    int selectNameAndEmail(HashMap<String, String> kakaoLogin);
    Member selectNameOfMember(String id);
    void insertInvestInfo(Member member);
    Optional<InvestInfo> findInvestInfoById(String id);
    int updateInvestInfoCashById(Map<Integer, String> idAndNewCash);
}
