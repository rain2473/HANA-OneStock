<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="../../resources/style/common.css">
    <link rel="stylesheet" href="../../resources/style/mypage.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
    <script>
        var cash;
    </script>
</head>
<body>
<div class="container">
    <%@ include file="include/header.jsp" %>
    <div class="content">
        <div class="member_info_div">
            <div class="chart_div">
                <div class="chart">
                    <h3><%=session.getAttribute("name")%>님의 현재 보유 현금입니다.</h3>
                    <div class="money">
                        <div style="display: flex; align-items: center;">
                            <img src="../../resources/img/wallet.png" width="50"
                                 style="vertical-align: middle; margin-right: 30px">
                            <span id="cash" style="vertical-align: middle; font-weight: 900; font-size: 40px;">0원</span>
                        </div>
                        <div style="display: flex; align-items: center; margin: 30px 0px 30px 0px; justify-content: center">
                            <input type="button" id="deposit" class="small-btn" value="충전하기">
                            <input type="button" class="small-btn" value="충전내역">
                        </div>
                    </div>
                    <h3><%=session.getAttribute("name")%>님의 현재 수익률입니다.</h3>
                    <div style="font-weight: 900; font-size: 32px;">
                        <p>목표수익률: <span id="goal">%</span></p>
                        <p>당일수익률: <span id="profit"></span></p>
                    </div>
                    <div style="display: flex; align-items: center; margin: 10px 0px 30px 0px">
                        <input type="button" class="small-btn" value="변경하기">
                    </div>
                </div>
                <div class="chart">
                    <div class="piechart">
                        <h3 id="chartTitle"></h3>
                        <canvas id="myPieChart"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="button-container">
        <a href="mypage2" class="button">회원정보 수정</a>
        <a href="#" class="button" onclick="goToDashboard2();">수익률 확인하기</a>
    </div>
    <%@ include file="include/footer.jsp" %>
</div>
</body>
<script>
    function goToDashboard2() {
        alert("페이지 이동");
        var link = document.createElement("a");
        link.href = "/dashboard2";
        link.click();
    }

    $(document).ready(function () {
        $.ajax({
            url: '/selectDayOfTransaction',
            type: 'GET',
            data: {
                id: '<%=session.getAttribute("id")%>'
            },
            success: function (data) {
                $('#profit').text(data+"%");
            },
            error: function (xhr, status, error) {
                console.error('Error:', error);
            }
        });
    });
    $(document).ready(function() {
         $.ajax({
            url: '/selectAssetsById',
            type: 'GET',
            success: function (json) {
                 const data = JSON.parse(json);
                 // 차트 그리는 코드
                 drawChart(data);
            },
            error: function (xhr, status, error) {
                console.error('Error:', error);
            }
        });
    });
    $(document).ready(function() {
         $.ajax({
            url: '/selectAssetsById',
            type: 'GET',
            success: function (json) {
                 const data = JSON.parse(json);
                 // 차트 그리는 코드
                 drawChart(data);
            },
            error: function (xhr, status, error) {
                console.error('Error:', error);
            }
        });
    });
</script>
<script>
    getUserCash("<%=session.getAttribute("id")%>");
    getUserGoal("<%=session.getAttribute("id")%>");

    /**
     *  사용자 잔고 받아오기
     */
    function getUserCash(id) {
        $.ajax({
            url: '/get-user-cash',
            type: 'GET',
            data: {
                id: id // 대체할 사용자 식별자
            },
            success: function (data) {
                const currentCash = document.getElementById("cash");
                currentCash.innerText = data + " 원";
            },
            error: function (xhr, status, error) {
                console.error('Error:', error);
            }
        });
    }

    /**
     *  사용자 Goal 받아오기
     */
    function getUserGoal(id) {
        $.ajax({
            url: '/get-user-goal',
            type: 'GET',
            data: {
                id: id // 대체할 사용자 식별자
            },
            success: function (data) {
                const currentCash = document.getElementById("goal");
                currentCash.innerText = data + "%";
            },
            error: function (xhr, status, error) {
                console.error('Error:', error);
            }
        });
    }

    // "충전하기" 버튼 클릭 시 실행되는 함수
    document.getElementById('deposit').addEventListener('click', function () {
        depositUserCash('<%=session.getAttribute("id")%>');
    });

    /**
     * 사용자 cash deposit (100만원)
     */
    function depositUserCash(id) {
        $.ajax({
            url: '/deposit-user-cash',
            type: 'GET',
            data: {
                id: id // 대체할 사용자 식별자
            },
            success: function (data) {
                getUserCash(id);
                alert("100만원 입금되었습니다.");
            },
            error: function (xhr, status, error) {
                console.error('Error:', error);
            }
        });
    }
        function drawChart(data) {
            // 결제내역 데이터 가져오기
            const assetData = data;

            // 차트 데이터 설정
            const chartData = {
                datasets: [{
                    backgroundColor: ['rgba(255, 99, 132, 0.5)',
                        'rgba(54, 162, 235, 0.5)',
                        'rgba(255, 206, 86, 0.5)',
                        'rgba(75, 192, 192, 0.5)',
                        'rgba(153, 102, 255, 0.5)'],
                    data: assetData.map(item => item.totalPrice)
                }],
                labels: assetData.map(item => [item.name + " : "+ item.totalPrice])
            };

            // 차트 생성
            var ctx1 = document.getElementById("myPieChart");
            var myPieChart = new Chart(ctx1, {
                type: 'pie',
                data: chartData,
                options: {
                    layout: {
                        padding: {
                            top: 0,
                            bottom: 80,
                            left: 90,
                            right: 90
                        }
                    },
                    cutoutPercentage: 30,
                    maintainAspectRatio: false,
                    legend: {
                        position: 'bottom',
                        align: 'start',
                        labels: {
                            usePointStyle: true,
                            fontSize: 14,
                            padding: 25,
                            fontColor: "black",
                        },
                    },
                    tooltips: {
                        callbacks: {
                            title: (tooltipItem, data) => {
                                const index = tooltipItem[0].index;
                                const category = data.labels[index];
                                return category;
                            },
                            label: (tooltipItem, data) => {
                                const dataset = data.datasets[tooltipItem.datasetIndex];
                                const amount = dataset.data[tooltipItem.index];
                                return `Amount : ${amount}`;
                            }
                        }
                    },
                    tooltips: {
                        callbacks: {
                            title: (tooltipItem, data) => {
                                const index = tooltipItem[0].index;
                                const category = data.labels[index];
                                return category;
                            },
                            label: (tooltipItem, data) => {
                                const index = tooltipItem.index;
                                const category = data.labels[index];
                                const amount = data.datasets[0].data[index];
                                return `${category} : ${amount}`;
                            }
                        }
                    },
                    plugins: {
                        title: {
                            display: true,
                            text: "보유 자산",
                        }
                    }
                }
            });
            // 차트 제목 업데이트
            document.getElementById("chartTitle").textContent = myPieChart.options.plugins.title.text;
        }
</script>
</html>
