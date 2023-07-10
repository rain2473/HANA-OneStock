<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="../../resources/style/index.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="content">
    <h1>Hello Spring</h1> <p>회원 기능</p>
    <p>
        <a href="/members/new">회원 가입</a> <a href="/members">회원 목록</a>
    </p>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>