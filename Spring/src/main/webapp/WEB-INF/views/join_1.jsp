<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../resources/style/main.css">
    <link rel="stylesheet" href="../../resources/style/join2.css">
</head>
<body>
<header>
    <nav>
        <img src="../../resources/img/logo.png" height="40">
        <div class="justify-content-end">
            <ul class="justify-content-end">
                <li class="nav-list">
                    <a class="nav-menu">서비스소개</a>
                <li class="nav-list">
                    <a class="nav-menu" onclick="openModal()">로그인</a>
            </ul>
        </div>
    </nav>
</header>
<form id="joinform" name="joinform" method="post">
    <div class="member">
        <h1>회원가입</h1>
        <br>
        <!-- 2. 필드 -->
        <div class="field">
            <b>아이디</b>
            <span class="placehold-text"><input type="text" name="id"></span>
            <span class="idChk" id="idChk" style="color: green;"></span>
        </div>
        <div class="field">
            <b>비밀번호</b>
            <input class="userpw placehold-text" type="password" name="pw">
            <span class="pwChk" style="color: green;"></span>
        </div>
        <div class="field">
            <b>비밀번호 재확인</b>
            <input class="userpw-confirm" type="password" name="pwRe">
            <span class="pwChkRe" style="color: orange;"></span>
        </div>
        <div class="field">
            <b>이름</b>
            <input type="text" name="name" value="${name}">
        </div>

        <!-- 5. 이메일_전화번호 -->
        <div class="field email">
            <b>본인 확인 이메일</b>
            <div>
                <input type="email" value="${email}" name="email">
                <input type="button" value="인증메일 받기" >
                <input type="number" placeholder="인증번호를 입력하세요">
            </div>
        </div>

        <div class="field">
            <b>전화번호</b>
            <input type="tel" placeholder="전화번호 입력" name="tel">
        </div>

        <!— 6. 가입하기 버튼 —>
        <input type="button" value="가입하기" onclick="joinFormFunc(); return false;">
    </div>
</form>
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
</body>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
    function joinFormFunc(){
		var formData = $("#joinform").serialize();
		$.ajax({
			type : 'post',
            url : '/insertMember',
			data : formData,
    		error: function(error){
				alert(error+"error");
			},
			success : function(response){
                if (response === "회원 등록 성공") {
                    alert("회원 등록 성공");
                    var link = document.createElement("a");
                    link.href = "/";
                    link.click();
                } else {
                    console.error("회원 등록 실패");
                }
			}
		});
	}
	var timer; // 타이머 변수

    $('[name=id]').on('input', function() {
        clearTimeout(timer);
        var id = $(this).val();
        timer = setTimeout(function() {
            $.ajax({
                url: "/idCheck",
                data: { id: id },
                type: "post",
                success: function(response) {
                    if (response.exists) {
                        $("#idChk").html("이미 사용중인 아이디 입니다.");
                    } else {
                        $("#idChk").html("사용가능한 아이디 입니다.");
                    }
                }
            });
        }, 300);
    })

    $('[name=pw]').on('keyup',function(event) {
        if (/(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$/g.test($('[name=pw]').val())) {
            $('.pwChk').html("<i class='bi bi-exclamation-circle'></i>");
        } else {
            $('.pwChk').html("<i class='bi bi-exclamation-circle'></i> 숫자, 특문 각 1회 이상, 영문은 2개 이상 사용하여 8자리 이상 입력");
        }
    });
    $('[name=pwRe]').focusout(function() {
        var pwd1 = $("[name=pw]").val();
        var pwd2 = $("[name=pwRe]").val();

        if (pwd1 != '' && pwd2 == '') {
            null;
        } else if (pwd1 != "" || pwd2 != "") {
            if (pwd1 == pwd2) {
                $('.pwChkRe').html("비밀번호가 일치합니다.");
            } else {
                $('.pwChkRe').html("비밀번호를 다시 입력해주세요.");
            }
        }
    });
</script>

</html>