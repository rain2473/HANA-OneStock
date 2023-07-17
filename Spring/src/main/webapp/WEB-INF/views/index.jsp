<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="container">
    <nav>
        <a href="/">
            <img src="../../resources/img/logo.png" height="40">
        </a>
        <div>
            <ul>
                <li class="nav-list">
                    <a href="#" class="nav-menu" onclick="openModal()">추천종목</a>
                </li>
                <%-- provider 데이터가 "kakao"일 경우 카카오 로그인 버튼을 표시 --%>
                <%
                    String id = (String) session.getAttribute("id");
                    if (id != null) {
                %>
                <c:if test="${empty provider}">
                    <li class="nav-list">
                        <a href="/logoutMember" class="nav-menu">로그아웃</a>
                    </li>
                </c:if>
                <c:if test="${provider eq 'kakao'}">
                    <li class="nav-list">
                        <a href="/oauth/logout" class="nav-menu">로그아웃</a>
                    </li>
                </c:if>
                <c:if test="${provider eq 'general'}">
                    <li class="nav-list">
                        <a href="/logoutMember" class="nav-menu">로그아웃</a>
                    </li>
                </c:if>

                <%-- provider 데이터가 "kakao"가 아닐 경우 일반 로그아웃 버튼을 표시 --%>
                <c:if test="${provider eq 'null'}">
                    <li class="nav-list">
                        <a href="/logoutMember" class="nav-menu">로그아웃</a>
                    </li>
                </c:if>

                <%} else{%>
                <li class="nav-list">
                    <a class="nav-menu" onclick="openModal()">로그인</a>
                </li>
                <%}%>
            </ul>
        </div>
    </nav>
    <div class="content">
        <div class="main">
            <div class="animate__animated animate__slideInLeft slow 4s">
                <h1><span class="highlight">HANA - One Stock<br></span>SMART TRADING</h1>
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
            <div id="myModal" class="modal">
                <div class="modal-content">
                    <div class="modal-header">
                        <br>
                        <h2>${name} 환영합니다.</h2>
                        <span class="close" onclick="closeModal()">&times;</span>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-body">
                                <h4>목표 수익률을 입력하고,<br>
                                    추천 주식을 확인하세요 !</h4>
                                <input type="text" id="goal" name="goal"><br><br>
                                <%--                    main.jsp로 이동--%>
                                <input type="button" class="button" value="추천 주식 확인하기" onclick="goToMain()">
                            </div>
                        </form>
                    </div>
                </div>
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
    <%@ include file="include/footer.jsp" %>
</div>
</body>
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
                    link.href = "/index_login";
                    link.click();
                } else {
                    console.error("로그인 실패");
                }
            }
        });
    }

</script>
</html>