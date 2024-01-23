<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>관리자 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/notice.css?v=1">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    <script src="./js/jquery.js"></script>
</head>
<script src="https://cdn.jsdelivr.net/npm/vue@2"></script>
<body>
<header>
<%@include file="./admin_header.jsp" %>
</header>
<nav class="navcss">
<%@include file="./admin_menu.jsp" %>
</nav>
<form id="f">
<main class="maincss">
<section id="box">
    <p>공지사항 관리페이지</p>
    <div class="subpage_view">
    <ul>
        <li><input type="checkbox" onclick="toggleCheckboxes()"></li>
        <li>NO</li>
        <li>제목</li>
        <li>글쓴이</li>
        <li>날짜</li>
        <li>조회</li>
    </ul>
    
    <c:forEach items="${noticeList}" var="notice">
    <ol>
        <li><input type="checkbox" value="${notice.midx}" onclick="updateCheckAll()" @click="ck(${notice.midx},'${notice.fileName}')" id="ck" ></li>
        <li>${notice.midx}</li>
        <li onclick="abc(${notice.midx})">${notice.mtitle}</li>
        <li>${notice.mname}</li>
        <li>${fn:substring(notice.mdate,0,10)}</li>
        <li>${notice.mviews}</li>
    </ol>
    </c:forEach>
    
    
    <c:if test="${fn:length(noticeList) == 0}">
	 	<ol class="none_text">
	        <li>등록된 공지 내용이 없습니다.</li>
	    </ol>
    </c:if>
    
    
    
    
    
    </div>
    <div class="board_btn">
        <button type="button" class="border_del" @click="border_del()">공지삭제</button>
        <button type="button" class="border_add" @click="border_add()" id="border_add">공지등록</button>
    </div>
    <div class="border_page">
        <ul class="pageing">
            <li><img src="./ico/double_left.svg"></li>
            <li><img src="./ico/left.svg"></li>
            <li>1</li>
            <li><img src="./ico/right.svg"></li>
            <li><img src="./ico/double_right.svg"></li>
        </ul>
    </div>
</section>
<section></section>
<section></section>
</main>
</form>
<%@ include file="./admin_footer.jsp" %>
</body>
<script src="./js/admin_notice.js?v=${today}"></script>
</html>