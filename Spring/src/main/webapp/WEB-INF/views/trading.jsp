<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="../../resources/style/index.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <style>
        .container {
            width: 300px;
            border: 1px solid black;
            padding: 10px;
        }
        .search-box {
            width: 100%;
            padding: 5px;
        }
        .stock-item {
            border-bottom: 1px solid gray;
            padding: 5px 0;
        }
        .stock-item:last-child {
            border-bottom: none;
        }
        .add-stock {
            text-align: center;
            padding: 10px 0;
            cursor: pointer;
            color: blue;
        }
        .stock-info {
            width: 100%;
            padding: 10px;
            border: 1px solid black;
            text-align: center;
        }

    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $('.search-box').on('input', function(){
                var input = $('.search-box').val();
                $.ajax({
                    url: '/stock-searching',  // API endpoint
                    type: 'GET',
                    data: {
                        'input': input
                    },
                    success: function(data) {
                        var resultHtml = '';
                        for (var i = 0; i < data.length; i++) {
                            resultHtml += '<div>' + data[i].name + '</div>'; // Assuming 'name' is a property of Stock
                        }
                        $('.search-result').html(resultHtml).show();
                    },
                    error: function (data) {
                    },
                });
            });

            // Hide the search result when the user clicks outside
            $(document).click(function(event) {
                if (!$(event.target).closest('.searchBar').length) {
                    $('.search-result').hide();
                }
            });

            // Select the search result and fill in the search box
            $(document).on('click', '.search-result div', function(){
                $('.search-box').val($(this).text());
            });
        });
    </script>
</head>
<body>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<!-- Search bar section -->
<div class="container">
    <div class="searchBar">
        <p>🔍검색</p>
        <input type="text" class="search-box" placeholder="Search...">
        <button class="search-button">검색</button>
    </div>

    <div class="search-result"></div>

    <div class="stock-item">
        <div>종목1</div>
        <div>종목번호1</div>
        <div>8000 KRW</div>
        <div>+0.00%</div>
    </div>

    <div class="stock-item">
        <div>종목2</div>
        <div>종목번호2</div>
        <div>4000 KRW</div>
        <div>+2.00%</div>
    </div>

    <div class="stock-item">
        <div>종목3</div>
        <div>종목번호3</div>
        <div>4000 KRW</div>
        <div>+2.00%</div>
    </div>

    <div class="stock-item">
        <div>종목4</div>
        <div>종목번호4</div>
        <div>4000 KRW</div>
        <div>+2.00%</div>
    </div>

    <!-- Additional stock items... -->
    <div class="add-stock">+ 종목 추가</div>
    <div class="add-stock-item">
        <div>종목명</div>
        <div>종목번호</div>
        <div>4000 KRW</div>
        <div>+2.00%</div>
    </div>
</div>
<!-- Search bar section END -->

<!-- Price section -->
<div class="stock-info" id="stock-info">
    <h2>종목명(종목번호)</h2>
    <h3>60,000원</h3>
    <h3>+1.67%</h3>
</div>

<!-- Price section END -->


<!-- Buy/Sell Form section -->
<form>
    <label for="availableFunds">주문 가능:</label><br>
    <input type="text" id="availableFunds" name="availableFunds" value="0 KRW" readonly><br>

    <label for="buyPrice">매수 가격:</label><br>
    <input type="text" id="buyPrice" name="buyPrice" placeholder="1000 KRW"><br>

    <label for="orderQuantity">주문 수량:</label><br>
    <input type="text" id="orderQuantity" name="orderQuantity" placeholder="0"><br>

    <label for="quantityPercent">주문 비율:</label><br>
    <button type="button" id="10percent">10%</button>
    <button type="button" id="25percent">25%</button>
    <button type="button" id="50percent">50%</button>
    <button type="button" id="100percent">100%</button><br>

    <label for="totalOrder">주문 총액:</label><br>
    <input type="text" id="totalOrder" name="totalOrder" readonly><br>

    <input type="submit" value="Submit">
</form>


<!-- Buy/Sell Form section END -->

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>