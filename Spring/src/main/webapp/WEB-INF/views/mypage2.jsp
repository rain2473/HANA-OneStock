<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../../resources/style/common.css">
        <link rel="stylesheet" href="../../resources/style/join.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    </head>
    <body>
    <%@ include file="/WEB-INF/views/include/header.jsp" %>

    <div class="member">
        <form id="updateForm">
            <h1>회원정보수정</h1>
            <br>
            <div class="field">
                <b>아이디</b>
                <span><input type="text" name="id" value="${id}" readonly="readonly" style="background-color: lightgray; color: black;"></span>
                <span class="idChk" id="idChk" style="color: green;"></span>
            </div>
            <div class="field">
                <b>비밀번호</b>
                <input class="userpw" type="password" name="password" value="${member.password}">
                <span class="pwChk" style="color: green;"></span>
            </div>
            <div class="field">
                <b>이름</b>
                <input type="text" name="name" value="${name}" readonly="readonly" style="background-color: lightgray; color: black;">
            </div>

            <div class="field email">
                <b>이메일</b>
                <div>
                    <input type="email" name="email" value="${member.email}" readonly="readonly" style="background-color: lightgray; color: black;">
                </div>
            </div>
            <div class="field">
                <b>전화번호</b>
                <input type="tel" placeholder="전화번호 입력" name="phone" value="${member.phoneNumber}">
            </div>
            <div class="field">
                <b>목표수익률</b>
                <input type="text" placeholder="목표수익률 입력" name="goal" value="${invest.goal}">
            </div>
            <input type="submit" value="회원정보수정"></button>
        </form>
    </div>
    <footer>
        <br>
        <hr>
        <p>
            <span>고객센터 1800-0000</span><br/>
            <span>평일 AM 09:00 ~ PM 18:00 </span><br/>
            <span>서울특별시 영등포구 의사당대로 82(여의도동) | 사업자등록번호 116-81-05992 </span><br/>
            <span>Copyright 2023. HANA-OneStock. All Rights Reserved.</span>
        </p>
        <br>
    </footer>
    <script>
        // 회원 가입
        $('#updateForm').submit(function (event) {
            event.preventDefault(); // 기본 동작 방지 (페이지 새로고침)
            // 입력된 정보 가져오기
            const id = $('#updateForm input[name="id"]').val();
            const password = $('#updateForm input[name="password"]').val();
            const phoneNumber = $('#updateForm input[name="phone"]').val();
            const goal = $('[name="goal"]').val();

            const data = {
                id: id,
                password: password,
                phoneNumber: phoneNumber,
                goal:goal
            };

            // Ajax 요청 보내기
            $.ajax({
                url: '/updateMember',
                type: 'POST',
                data: JSON.stringify(data),
                contentType: 'application/json',
                success : function(response){
                    if (response === "회원 수정 성공") {
                        alert("회원 수정 성공");
                        var link = document.createElement("a");
                        link.href = "/mypage";
                        link.click();
                    } else {
                        console.error("회원 수정 실패");
                    }
                }
            });
        });


    </script>
    </body>
    </html>