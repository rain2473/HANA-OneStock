<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="../../resources/style/common.css">
    <link rel="stylesheet" href="../../resources/style/dashboard2.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
    <script>
        var cash;
    </script>
</head>
<body>
<div class="container">
    <%@ include file="include/header.jsp" %>
    <div class="content">
        <div class="content-container">
            <div class="chart2">
                <c:set var="results" value="${requestScope.results}" />
                <table>
                    <tr>
                        <th>Buy</th>
                        <th>Sell</th>
                        <th>Volume</th>
                        <th>ISIN</th>
                    </tr>

                    <c:forEach items="${resultList}" var="result">
                        <tr>

                            <td class="box">${result.buy}</td>
                            <td class="box">${result.sell}</td>
                            <td class="box">${result.volume}</td>
                            <td class="box">${result.isin}</td>

                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="chart_div">
                <div class="chart">
                    <h3>${name}님의 수익률입니다.</h3>
                    <canvas id="myLineChart"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="include/footer.jsp" %>
</div>
</body>
<script>
    $(document).ready(function() {
         $.ajax({
            url: '/dashboard',
            type: 'GET',
            success: function (json) {
                 const data = JSON.parse(json);
                 alert("data "+data);
            },
            error: function (xhr, status, error) {
                console.error('Error:', error);
            }
        });
    });
    // JSON 파일 가져오기
    fetch("../../resources/json/result.json")
        .then((response) => response.json())
        .then((data) => {
            // 결제내역 데이터 가져오기
            const assetData = data.assetData;

            // 차트 데이터 설정
            const chartData = {
                datasets: [{
                    borderColor: 'rgba(255, 99, 132, 1)',   // Red
                    fill: false,
                    data: assetData.map(item => item.amount)
                }],
                labels: assetData.map(item => item.date)
            };

            // 추가 직선 데이터 설정
            const fixedLineData = {
                label: 'Fixed Line',
                borderColor: 'rgba(54, 162, 235, 1)',    // Blue
                fill: false,
                data: [3, 3, 3, 3, 3],    // 원하는 값으로 변경
                pointRadius: 0   // 데이터 포인트 표시 없음
            };

            chartData.datasets.push(fixedLineData);

            // 차트 생성
            var ctx1 = document.getElementById("myLineChart");
            var myLineChart = new Chart(ctx1, {
                type: 'line',
                data: chartData,
                options: {
                    layout: {
                        padding: {
                            top: 0,    // 위쪽 패딩 조정
                            bottom: 0, // 아래쪽 패딩 조정
                            left: 0,   // 왼쪽 패딩 조정
                            right: 0   // 오른쪽 패딩 조정
                        }
                    },
                    maintainAspectRatio: false,
                    legend: {
                        display: false
                    },
                    scales: {
                        x: {
                            display: true,
                            title: {
                                display: true,
                                text: 'Date'
                            }
                        },
                        y: {
                            display: true,
                            title: {
                                display: true,
                                text: 'Amount'
                            }
                        }
                    }
                }
            });

            // 차트 제목 업데이트
            document.getElementById("chartTitle").textContent = "수익률";
        })
        .catch((error) => {
            console.error("JSON 파일을 로드하는 중 오류가 발생했습니다:", error);
        });
</script>
</html>