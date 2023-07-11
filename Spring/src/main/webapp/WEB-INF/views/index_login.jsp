<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="../../resources/style/common.css">
    <link rel="stylesheet" href="../../resources/style/index_login.css">
</head>
<body>
<header>
    <nav>
        <a href="index_login">
            <img src="../../resources/img/logo.png" height="40">
        </a>
        <div>
            <ul>
                <li class="nav-list">
                    <a href="" class="nav-menu">서비스소개</a>
                <li class="nav-list">
                    <a href="" class="nav-menu">마이페이지</a>
                 <%-- provider 데이터가 "kakao"일 경우 카카오 로그인 버튼을 표시 --%>
                 <c:if test="${provider eq 'kakao'}">
                     <li class="nav-list">
                         <a href="/oauth/logout" class="nav-menu">로그아웃</a>
                     </li>
                 </c:if>

                 <%-- provider 데이터가 "kakao"가 아닐 경우 일반 로그아웃 버튼을 표시 --%>
                 <c:if test="${empty provider}">
                     <li class="nav-list">
                         <a href="/logoutMember" class="nav-menu">로그아웃</a>
                     </li>
                 </c:if>
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
<script>
    // 모달 열기 함수
    function openModal() {
        document.getElementById("myModal").style.display = "block";
    }

    // 모달 닫기 함수
    function closeModal() {
        document.getElementById("myModal").style.display = "none";
    }

    function goToMain() {
        alert("페이지 이동");
        var link = document.createElement("a");
        link.href = "/main";
        link.click();
    }

</script>
</body>
</html>