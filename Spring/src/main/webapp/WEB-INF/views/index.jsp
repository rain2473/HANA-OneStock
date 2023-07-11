<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="../../resources/style/common.css">
    <link rel="stylesheet" href="../../resources/style/index.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<header>
    <nav>
        <a href="/">
        <img src="../../resources/img/logo.png" height="40">
        </a>
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

<div class="content">
    <div class="main">
        <div class="animate__animated animate__slideInLeft slow 4s">
            <h1><span class="highlight">HANA - One Stock</span> SMART TRADING</h1>
        </div>
        <h3>당연하지 않았던 투자환경, 이제는 당연하게</h3>
        <div onclick="openModal()">
            <a href="#" class="btn">시작하기</a>
        </div>
    </div>
</div>

<div id="myModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <img src="../../resources/img/logo2.png" width="205">
        </div>

        <div class="modal-body">
            <div class="form-body">
                <h2>로그인</h2>
                <form id="loginForm" method="post">
                    <div class="form-group">
                        <label for="username">아이디</label>
                        <input type="text" id="username" name="id">
                    </div>
                    <div class="form-group">
                        <label for="password">비밀번호</label>
                        <input type="password" id="password" name="pw">
                    </div>
                    <input type="button" class="button" value="로그인" onclick="loginFormFunc(); return false;">
                    <a href="join" class="button">회원가입</a>
                </form>
                <br>
                <a href="/oauth2/authorization/kakao" class="kakao_btn"><img src="../../resources/img/kakao.png"
                                                                             width="20"> 카카오로 시작하기</a>

            </div>
            <div class="text">
                <p>소셜 계정으로 로그인 또는 가입 시 <br>개인정보 처리 방침 및 서비스 이용약관에 동의한 것으로 간주합니다.</p>
            </div>
        </div>
        <span class="close" onclick="closeModal()">&times;</span>
    </div>
</div>

<footer>
    <hr>
    <p>
        <span>고객센터 1800-0000</span><br/>
        <span>평일 AM 09:00 ~ PM 18:00 </span><br/>
        <span>서울특별시 영등포구 의사당대로 82(여의도동) | 사업자등록번호 116-81-05992 </span><br/>
        <span>Copyright 2023. HANA-OneStock. All Rights Reserved.</span>
    </p>
    <br>
</footer>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
    // 모달 열기 함수
    function openModal() {
        document.getElementById("myModal").style.display = "block";
    }

    // 모달 닫기 함수
    function closeModal() {
        document.getElementById("myModal").style.display = "none";
    }

    // 로그인
    function loginFormFunc() {
        var formData = $("#loginForm").serialize();
        var id = $("#username").val();
        var password = $("#password").val();

        $.ajax({
            type: "POST",
            url: "/loginMember",
            data: JSON.stringify({
                id: id,
                password: password
            }),
            contentType: 'application/json',
            error: function (xhr, status, error) {
                alert(error + "error");
            },
            success: function (response) {
                if (response === "로그인 성공") {
                    // index_login.jsp로 이동
                    alert("로그인 성공");
                    var link = document.createElement("a");
                    link.href = "/index_login?id="+id;
                    link.click();
                } else {
                    console.error("로그인 실패");
                }
            }
        });
    }


</script>
</body>
</html>