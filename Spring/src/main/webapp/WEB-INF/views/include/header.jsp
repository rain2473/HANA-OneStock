<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="../../../resources/style/common.css">
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
                    <a href="" class="nav-menu">서비스소개</a>
                </li>
                <li class="nav-list">
                    <a href="/mypage" class="nav-menu">마이페이지</a>
                </li>
                 <%-- provider 데이터가 "kakao"일 경우 카카오 로그인 버튼을 표시 --%>
                 <%
                       String id = (String) session.getAttribute("id");
                       if (id != null) {
                 %>
                         <c:choose>
                             <c:when test="${empty provider}">
                                 <li class="nav-list">
                                     <a href="/logoutMember" class="nav-menu">로그아웃</a>
                                 </li>
                             </c:when>
                             <c:when test="${provider eq 'kakao'}">
                                 <li class="nav-list">
                                     <a href="/oauth/logout" class="nav-menu">로그아웃</a>
                                 </li>
                             </c:when>
                             <c:when test="${provider eq 'general'}">
                                 <li class="nav-list">
                                     <a href="/logoutMember" class="nav-menu">로그아웃</a>
                                 </li>
                             </c:when>
                             <c:otherwise>
                                 <li class="nav-list">
                                     <a href="/logoutMember" class="nav-menu">로그아웃</a>
                                 </li>
                             </c:otherwise>
                         </c:choose>
                 <%} else{%>
                        <li class="nav-list">
                             <a class="nav-menu" onclick="openModal()">로그인</a>
                        </li>
                 <%}%>
            </ul>
        </div>
    </nav>
</header>
</body>
</html>