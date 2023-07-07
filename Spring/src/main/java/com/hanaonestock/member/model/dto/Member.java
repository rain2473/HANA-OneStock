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
    private int id;
    private String name; // 사용자 이름
    private String email; // 사용자의 이메일
    private String pw;
    public Member() {

    }
    public Member updateMember(String name, String email) {
        this.name = name;
        this.email = email;

        return this;
    }
}
