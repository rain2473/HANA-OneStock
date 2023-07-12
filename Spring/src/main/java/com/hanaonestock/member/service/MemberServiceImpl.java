package com.hanaonestock.member.service;

import com.hanaonestock.member.model.dao.MemberMapper;
import com.hanaonestock.member.model.dto.InvestInfo;
import com.hanaonestock.member.model.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public void insertMember(Member member) {
        memberMapper.insertMember(member);
    }

    @Override
    public Member selectNameOfMember(String id) {
        return memberMapper.selectNameOfMember(id);
    }

    @Override
    public void insertInvestInfo(Member member) {
        memberMapper.insertInvestInfo(member);
    }

    @Override
    public int findUserCash(String id) {
        Optional<InvestInfo> optionalInvestInfo = memberMapper.findInvestInfoById(id);
        int UserCash = 0;
        if (optionalInvestInfo.isPresent()) {
            InvestInfo investInfo = optionalInvestInfo.get();
            UserCash = investInfo.getCash();
        }
        return UserCash;
    }

    @Override
    public int updateInvestInfoCashById(String id, int cash) {
        Map<Integer, String> map = new HashMap<>();
        map.put(cash, id);
        try{
            return memberMapper.updateInvestInfoCashById(map);
        } catch (Exception e) {
            return 0;
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
    public int deleteMember(String id) {
        return memberMapper.deleteMember(id);
    }

    @Override
    public Member loginMember(HashMap<String, String> loginData){return memberMapper.loginMember(loginData);}

    @Override
    public int selectNameAndEmailOfMember(HashMap<String, String> kakaoLogin) {
        try {
            return memberMapper.selectNameAndEmail(kakaoLogin);
        } catch (Exception e) {
            return 0;
        }
    }
}


