package com.hanaonestock.member.service;

import javax.servlet.http.HttpSession;

public interface EmailService {
    String sendSimpleMessage(String to)throws Exception;
}