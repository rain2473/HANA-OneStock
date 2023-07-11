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
    private int m_id;
    private String id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String provider;
    public Member() {
    }
}
