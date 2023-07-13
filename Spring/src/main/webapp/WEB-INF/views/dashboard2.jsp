<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.hanaonestock.stock.model.dto.RecommendedStock" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="../../resources/style/common.css">
    <link rel="stylesheet" href="../../resources/style/dashboard2.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://unpkg.com/lightweight-charts/dist/lightweight-charts.standalone.production.js"></script>
    <script>
        var cash;
    </script>
</head>
<body style="background-image: url(" ..
/img/background2.png")">
<div class="container">
    <%@ include file="include/header.jsp" %>
    <div class="content">
        <div class="content-container">
            <div class="chart2">
            </div>
            <div class="chart_div">
                <div class="chart">
                    <h3>${name}님의 수익률입니다.</h3>
                </span>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="include/footer.jsp" %>
</div>
</body>
<script>
    getUserCash("<%=session.getAttribute("id")%>");
</script>
</html>