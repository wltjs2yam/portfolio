<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>항공사 및 번호 등록</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/seat.css?v=5">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    <script src="./js/jquery.js"></script>
</head>
<body>
<%@ include file="./admin_header.jsp" %>
</header>
<nav>
<%@ include file="./admin_menu.jsp"%> 
</nav>
<main class="maincss">
<section>
    <p>좌석 및 예약설정</p>
<div class="subpage_view">
    <span><b>미설정 항공코드 0건</b> | 설정 완료 항공코드 0건</span>
    <span>
        <form>
        <select class="p_select1">
            <option>출발지</option>
            <option>도착지</option>
        </select>
        <input type="text" class="p_input1" placeholder="검색할 단어를 입력하세요">
        <input type="submit" value="검색" title="항공검색" class="p_submit">
        </form>
    </span>
</div>
<div class="subseat_view2">
    <ul>
        <li>공항코드</li>
        <li>항공사명</li>
        <li>항공코드</li>
        <li>항공편명</li>
        <li>출발지</li>
        <li>도착지</li>
        <li>좌석형태</li>
        <li>좌석수</li>
        <li>예약 시작일</li>
        <li>예약 종료일</li>
        <li>1인 금액</li>
    </ul>
    <ul>
        <li>GMP</li>
        <li style="text-align: left; text-indent: 3px;">대한항공</li>
        <li style="text-align: left; text-indent: 3px;">KE017</li>
        <li style="text-align: left; text-indent: 3px;">Korean Air</li>
        <li>한국</li>
        <li>중국</li>
        <li>일반석</li>
        <li>80</li>
        <li><input type="date" class="days"></li>
        <li><input type="date" class="days"></li>
        <li><input type="text" class="moneys" maxlength="8"></li>
    </ul>
    <ul>
        <li>GMP</li>
        <li style="text-align: left; text-indent: 3px;">대한항공</li>
        <li style="text-align: left; text-indent: 3px;">KE017</li>
        <li style="text-align: left; text-indent: 3px;">Korean Air</li>
        <li>한국</li>
        <li>중국</li>
        <li>비지니스석</li>
        <li>80</li>
        <li><input type="date" class="days"></li>
        <li><input type="date" class="days"></li>
        <li><input type="text" class="moneys" maxlength="8"></li>
    </ul>
    <ul>
        <li style="width: 100%;">검색된 항공편이 없습니다.</li>
    </ul>
</div>
<div class="subpage_view4">
    <span style="text-align: right;">
    <input type="button" value="등록완료" title="등록완료" class="p_button p_button_color2">
    </span>
</div>
</section>
</main>
<%@ include file="./admin_footer.jsp"%>
</body>
</html>