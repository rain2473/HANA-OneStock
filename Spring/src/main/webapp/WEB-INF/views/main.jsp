<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="../../resources/style/common.css">
    <link rel="stylesheet" href="../../resources/style/main.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
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
                    <a class="nav-menu">서비스소개</a>
                <li class="nav-list">
                    <a class="nav-menu">마이페이지</a>
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
    <div class="memberinfo_div">
        <div class="chart_div">
            <div class="chart">
                <h3>증시현황</h3>
            </div>
            <div class="chart">
                <h3>특징종목</h3>
            </div>
        </div>
        <div class="list_div">
            <c:forEach var="stock" items="${stockList}" varStatus="status">
                <div class="stock_list">
                    <fmt:formatNumber value="${stock.close}" pattern="#,##0" var="formattedClose" />
                    <h3><c:out value="${stock.name}" /></h3>
                    <c:choose>
                        <c:when test="${stock.updown >= 0}">
                            <span class="small_text red_text">
                                <c:out value="${stock.updown}" />%
                                ▲<c:out value="${stock.gap}" />
                            </span>
                        </c:when>
                        <c:otherwise>
                            <span class="small_text blue_text">
                                <c:out value="${stock.updown}" />%
                                ▼<c:out value="${stock.gap}" />
                            </span>
                        </c:otherwise>
                    </c:choose>
                    <h3><c:out value="${formattedClose}" /></h3>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<div class="button-container">
    <input type="button" class="button" value="거래소 둘러보기" onclick="goToDashboard();">
    <a href="join" class="button">수익률 확인하기</a>
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
    function goToDashboard() {
        alert("페이지 이동");
        var link = document.createElement("a");
        link.href = "/dashboard";
        link.click();
    }
</script>
</body>
</html>