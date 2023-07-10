package com.hanaonestock.member.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Options;
import org.springframework.security.core.userdetails.User;

@Data
@Getter
@Setter
public class Member {
    public Member() {
    }
    public Member(String id, String pw) {
        this.id=id;
        this.pw=pw;
    }
    private int m_id;
    private String id;
    private String name; // 사용자 이름
    private String email; // 사용자의 이메일
    private String pw;
    private String tel;
}
