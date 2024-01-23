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
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/basic.css">
<link rel="stylesheet" type="text/css" href="./css/login.css?v=${today}">
<link rel="stylesheet" type="text/css" href="./css/main.css">
<link rel="stylesheet" href="./css/faq.css?v=${today}">
<link rel="icon" href="./img/logo.png" sizes="128x128">
<link rel="icon" href="./img/logo.png" sizes="64x64">
<link rel="icon" href="./img/logo.png" sizes="32x32">
<link rel="icon" href="./img/logo.png" sizes="16x16">
<title>항공공사 및 번호 등록</title>
</head>
<script src="https://cdn.jsdelivr.net/npm/vue@2"></script>
<body>
<header>
<%@ include file="./admin_header.jsp" %>
</header>
<nav>
<%@ include file="./admin_menu.jsp"%> 
</nav>
<!-- FAQ 등록 시작 -->
<form id="f">
<main class="maincss">
<section class="page_section" id="box">
<input type="hidden" name="fidx" v-model="fidx">

<p>FAQ 확인 및 수정</p>
<div class="listbody">
    <div class="procho">
       <section class="data_listsview">
       <ol>
       <li>질문 제목</li>
       <li><input type="text" class="notice_in in1" name="ftitle" value="${ff.get(1)}" ></li>
       <li>글쓴이</li>
       <li><input type="text" class="notice_in in2" readonly name="fwriter" value="${ff.get(2)}"></li> <li style="height:520px;">질문 내용</li>
       <li style="height:520px; padding-top: 10px;">
       <textarea class="notice_in in3" id="boardtext" name="ftext"  >${ff.get(3)}</textarea>
       </li>      
       </ol>
       <span class="notice_btns">
        <input type="button" value="FAQ 리스트" class="meno_btn1" @click="meno_btn1()">
        <input type="button" value="FAQ 수정" class="meno_btn2" @click="meno_btn2()"></span>
       </section>
    </div>
</div> 
</section>
</main>
</form>
<!-- FAQ 등록 끝 -->
<%@ include file="./admin_footer.jsp"%>
</body>
<script>
var title = "${ff.get(1)}";
</script>
<script src="./ckeditor/ckeditor.js"></script>
<script src="./js/admin_faq_modify.js?v=${today}"></script>
</html>