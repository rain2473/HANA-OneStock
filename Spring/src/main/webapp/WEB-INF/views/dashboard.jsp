<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
  <link rel="stylesheet" href="../../resources/style/common.css">
  <link rel="stylesheet" href="../../resources/style/dashboard.css">
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<header>
  <nav>
    <a href="/">
      <img src="../../resources/img/logo.png" height="40">
    </a>
    <div>
      <ul>
        <li class="nav-list">
          <a class="nav-menu">서비스소개</a>
        <li class="nav-list">
          <a class="nav-menu">마이페이지</a>
        <li class="nav-list">
          <a class="nav-menu">로그아웃</a>
      </ul>
    </div>
  </nav>
</header>

<div class="container">
  <!-- 왼쪽 서브 메뉴 -->
  <div class="left_sub_menu">
    <div class="sub_menu">
      <input type="search" name="SEARCH" placeholder="SEARCH">
      <h2>추천종목</h2>
      <ul class="stock_name">
        <li>삼성전자<i class="arrow fas fa-angle-right"></i></li>
        <ul class="small_menu">
          <li>종목번호 005930</li>
          <li>69,500 <span class="blue_text"> -0.57%</span></li>
        </ul>
      </ul>
      <ul class="stock_name">
        <li>카카오<i class="arrow fas fa-angle-right"></i></li>
        <ul class="small_menu">
          <li>종목번호 035720</li>
          <li>49,850 <span> 0.00%</span></li>
        </ul>
      </ul>
      <h2>종목추가</h2>
      <ul class="stock_name">
        <li>삼성전자<i class="arrow fas fa-angle-right"></i></li>
        <ul class="small_menu">
          <li>종목번호 005930</li>
          <li>69,500 <span class="blue_text"> -0.57%</span></li>
        </ul>
      </ul>
      <ul class="stock_name">
        <li>카카오<i class="arrow fas fa-angle-right"></i></li>
        <ul class="small_menu">
          <li>종목번호 035720</li>
          <li>49,850 <span> 0.00%</span></li>
        </ul>
      </ul>
      <ul class="stock_name">
        <li>카카오<i class="arrow fas fa-angle-right"></i></li>
        <ul class="small_menu">
          <li>종목번호 035720</li>
          <li>49,850 <span> 0.00%</span></li>
        </ul>
      </ul>
    </div>
  </div>
  <div class="chart_div">
    <div class="chart">
      <h3>차트 제발 들어왔음좋겠지렁이...</h3>
    </div>
    <div class="trade_div">
      <h2>종목명(종목번호)</h2>
      <h3>60,000원</h3>
      <h3>+1.67%</h3>
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
  function goToDashboard() {
    alert("페이지 이동");
    var link = document.createElement("a");
    link.href = "/dashboard";
    link.click();
  }
</script>
</body>
</html>