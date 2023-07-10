<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../resources/style/main.css">
    <link rel="stylesheet" href="../../resources/style/join2.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<header>
    <nav>
        <img src="../../resources/img/logo.png" height="40">
        <div class="justify-content-end">
            <ul class="justify-content-end">
                <li class="nav-list">
                    <a class="nav-menu">서비스소개</a>
                <li class="nav-list">
                    <a class="nav-menu" onclick="openModal()">로그인</a>
            </ul>
        </div>
    </nav>
</header>

<div class="member">
    <form id="signupForm">
    <h1>회원가입</h1>
    <br>
    <div class="field">
        <b>아이디</b>
        <span><input type="text" name="id"></span>
    </div>
    <div class="field">
        <b>비밀번호</b>
        <input class="userpw" type="password" name="password" placeholder="4자리 이상의 영문 또는 숫자로 작성해주세요.">
    </div>
    <div class="field">
        <b>비밀번호 재확인</b>
        <input class="userpw-confirm" type="password" name="password_confirm">
    </div>
    <div class="field">
        <b>이름</b>
        <input type="text" name="name">
    </div>

    <div class="field email">
        <b>본인 확인 이메일</b>
        <div>
            <input type="email" name="email">
            <input type="button" value="인증메일 받기">
            <input type="number" placeholder="인증번호를 입력하세요">
        </div>
    </div>
    <div class="field">
        <b>전화번호</b>
        <input type="tel" placeholder="전화번호 입력" name="phone">
    </div>
        <button class="button" type="submit">가입하기</button>
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
    $('#signupForm').submit(function(event) {
        event.preventDefault(); // 기본 동작 방지 (페이지 새로고침)

        // 입력된 정보 가져오기
        const id = $('#signupForm input[name="id"]').val();
        const password = $('#signupForm input[name="password"]').val();
        const name = $('#signupForm input[name="name"]').val();
        const email = $('#signupForm input[name="email"]').val();
        const phoneNumber = $('#signupForm input[name="phone"]').val();

        // 서버로 전달할 데이터 생성
        const data = {
            id: id,
            password: password,
            name: name,
            email: email,
            phoneNumber: phoneNumber
        };

        console.log(data);

        // Ajax 요청 보내기
        $.ajax({
            url: '/insertMember',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(response) {
                // 성공적으로 회원가입이 완료되었을 때의 처리
                console.log('회원가입 성공');
                console.log(response); // 응답 데이터 출력
            },
            error: function() {
                // 회원가입 실패 시의 처리
                console.error('회원가입 실패');
            }
        });
    });
</script>
</body>
</html>