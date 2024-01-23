<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    Date today = new Date();
%>    
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
    <link rel="stylesheet" type="text/css" href="./css/product.css?v=5">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    <script src="./js/jquery.js"></script>
</head>
<script src="https://cdn.jsdelivr.net/npm/vue@2"></script>
<body>
<header>
<%@ include file="./admin_header.jsp" %>
</header>
<nav>
<%@ include file="./admin_menu.jsp"%> 
</nav>
<main class="maincss">
<section>
    <p>항공편 및 번호 등록 페이지</p>
<div class="subpage_view">
    <span>등록된 항공사 0건</span>
    <span>
        <form>
        <select class="p_select1">
            <option>항공명</option>
            <option>항공코드</option>
        </select>
        <input type="text" class="p_input1" placeholder="검색어를 입력해 주세요">
        <input type="submit" value="검색" title="항공검색" class="p_submit">
        </form>
    </span>
</div>
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox"></li>
        <li>공항코드</li>
        <li>항공사명</li>
        <li>항공코드</li>
        <li>항공편명</li>
        <li>출발지</li>
        <li>도착지</li>
        <li>좌석형태</li>
        <li>좌석수</li>
        <li>비행사항 유/무</li>
        <li>관리</li>
    </ul>
    <ul>
        <li><input type="checkbox"></li>
        <li>GMP</li>
        <li>대한항공</li>
        <li>KE017</li>
        <li>Korean Air</li>
        <li>한국</li>
        <li>중국</li>
        <li>일반석</li>
        <li>80</li>
        <li>Y</li>
        <li>관리</li>
    </ul>
    <ul>
        <li style="width: 100%;">등록된 항공편이 없습니다.</li>
    </ul>
</div>
<div class="subpage_view3">
    <ul class="pageing">
        <li><img src="./ico/double_left.svg"></li>
        <li><img src="./ico/left.svg"></li>
        <li>1</li>
        <li><img src="./ico/right.svg"></li>
        <li><img src="./ico/double_right.svg"></li>
    </ul>
</div>
<div class="subpage_view4">
    <input type="button" value="선택항공편 삭제" title="선택상품 삭제" class="p_button">
    <span style="float: right;">
    <input type="button" value="신규항공편 등록" title="신규상품 등록" class="p_button p_button_color1" id="p_button p_button_color1">
    <input type="button" value="항공 코드 등록" title="카테고리 등록" class="p_button p_button_color2" id="p_button p_button_color2">
    </span>
</div>
</section>
</main>
<footer>
<%@ include file="./admin_footer.jsp" %>
</footer>
</body>
<script src="./js/admin_product.js?v=<%=today%>"></script>
</html>