<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL 표현식으로 리스트 출력 -->
<%@ taglib prefix="app" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 배열 갯수를 확인하는 라이브러리 & JSTL 명령어 length,split -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%   
    Date today = new Date();
    request.setAttribute("today", today); 
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
<section id="box">
    <p>항공편 및 번호 등록 페이지</p>
    <div class="product_insert">
        <ul>
            <li>공항코드</li>
            <li>
                <select class="product_input1_1">
                    <option>공항코드를 선택하세요</option>
                    <app:forEach var="item" items="${aa}">
                    <option>${item.getAirplane()}</option>
                    </app:forEach>
                </select>
            </li>
        </ul>
        <ul>
            <li>항공사명</li>
            <li>
                <select class="product_input1_1">
                    <option>항공사명를 선택하세요</option>
                    <app:forEach var="item1" items="${aa}">
                    <option>${item1.getAirname()}</option>
                    </app:forEach>
                </select>
            </li>
        </ul>
        <ul>
            <li>항공코드</li>
            <li>
                <select class="product_input1_1">
                    <option>항공코드를 선택하세요</option>
                    <app:forEach var="item2" items="${aa}">
                    <option>${item2.getAircode()}</option>
                    </app:forEach>
                </select>
                <input type="button" value="항공 코드 등록" title="항공코드 등록" class="product_btn" @click="product_btn()"> <span class="help_text">※ 항공코드는 절대 중복되지 않도록 합니다.</span>
            </li>
        </ul>
        <ul>
            <li>항공편명</li>
            <li>
                <input type="text" class="product_input2" maxlength="100" readonly name="aircode">
            </li>
        </ul>
        <ul>
            <li>출발지</li>
            <li>
                <input type="text" class="product_input3"><span class="help_text" name="start_point">※ 출발지와 도착지가 같을 수 없습니다.</span>
            </li>
        </ul>
        <ul>
            <li>도착지</li>
            <li>
                <input type="text" class="product_input3"><span class="help_text" name="end_point">※ 출발지와 도착지가 같을 수 없습니다.</span>
            </li>
        </ul>
        <ul>
            <li>좌석형태</li>
            <li>
                <input type="text" class="product_input3"> <span class="help_text" name="seat_text">※ 예시 일반석, 프레스티지석, 일등석, 비지니스석으로 나누어집니다.</span>
            </li>
        </ul>
        <ul>
            <li>좌석수</li>
            <li>
                <input type="text" class="product_input3" maxlength="4" value="0">EA <span class="help_text" name="seat_int">※ 숫자만 입력하세요. 재고가 0일 경우 soldout이 됩니다.</span>
            </li>
        </ul>
        <ul>
            <li>비행사항</li>
            <li>
                <label class="product_label">
                <input type="radio" name="a" style="vertical-align:-1px;" checked name="air_use" value="Y"> 운행가능
                </label>
                <label class="product_label">
                <input type="radio" name="a" style="vertical-align:-1px;" name="air_use" value="N"> 운행종료
                </label> <span class="help_text">※ 운행종료로 선택할 경우 고객은 해당 항공을 이용하지 못합니다.</span>
            </li>
        </ul>
    </div>
    <div class="subpage_view4" style="text-align:center;">
        <input type="button" value="항공편 리스트" title="항공편 리스트" class="p_button p_button_color1" style="margin-right: 5px;">
        <input type="button" value="신규항공편 등록" title="신규항공편 등록" class="p_button p_button_color2" @click="newair()">
        </span>
    </div>
</section>
</main>
<%@ include file="./admin_footer.jsp" %>
</body>
<script src="./js/admin_product_write.js?v=<%=today%>"></script>

</html>