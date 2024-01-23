<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="admin.domain.PayListVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL 표현식으로 리스트 출력 -->
<%@ taglib prefix="app" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 배열 갯수를 확인하는 라이브러리 & JSTL 명령어 length,split -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="stylesheet" type="text/css" href="./css/ticketing.css?v=1">
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
    <p>예매 리스트</p>
<div class="subpage_view">
    <span>
        <form id="h" enctype="application/x-www-form-urlencoded" method="POST" action="./ticketing_list.do">
        <select class="p_select1" name="part" v-model="part">
            <option value="1">고객명</option>
            <option value="2">연락처</option>
        </select>
        <input type="text" class="p_input1" placeholder="검색할 단어를 입력하세요" name="search" value="${search}">
        <input type="submit" value="검색" title="예매 정보 검색" class="p_submit">
        </form>
    </span>
</div>
<div class="subtick_view2">
    <ul>
        <li></li>
        <li>공항코드</li>
        <li>항공사명</li>
        <li>항공코드</li>
        <li>출발지</li>
        <li>도착지</li>
        <li>좌석형태</li>
        <li>고객명</li>
        <li>연락처</li>
        <li>예약일</li>
        <li>인원수</li>
        <li>결제금액</li>
    </ul>
    <app:forEach var="pay" items="${pdata}">
    <ul>
        <li><input type="radio" name="gener" v-model="data" value="${pay.aidx}"></li>
        <li>${pay.airplane}</li>
        <li style="text-align: left; text-indent: 3px;">${pay.airname}</li>
        <li>${pay.aircode}</li>
        <li>${pay.start}</li>
        <li>${pay.end}</li>
        <li>${pay.seat}</li>
        <li>${pay.aname}</li>
        <li>${pay.atel}</li>
        <li>${pay.areserve}</li>
        <li>${pay.amember}</li>
        <li>${pay.apay}</li>
    </ul>
    </app:forEach>
    
    <app:if test="${fn:length(pdata) == 0}">
    <ul>
        <li style="width: 100%;">예매 하신 사용자 또는 검색하신 내용이 없습니다.</li>
    </ul>
    </app:if>
</div>
<div class="subpage_view4">
    <span style="text-align: right;">
    <input type="button" value="예매취소" title="예매취소" class="p_button p_button_color2" @click="payno()">
    </span>
    
</div>
</section>
</main>
<%@ include file="./admin_footer.jsp" %>
</body>
<script>
var part = '${part}'
</script>
<script src="./js/admin_ticketing.js?v=<%=today%>"></script>
</html>