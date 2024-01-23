<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Date today = new Date();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/basic.css">
<link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
<link rel="stylesheet" type="text/css" href="./css/main.css">
<link rel="stylesheet" href="./css/faq.css?v=33">
<link rel="icon" href="./img/logo.png" sizes="128x128">
<link rel="icon" href="./img/logo.png" sizes="64x64">
<link rel="icon" href="./img/logo.png" sizes="32x32">
<link rel="icon" href="./img/logo.png" sizes="16x16">
<title>항공공사 및 번호 등록</title>
</head>
<script src="https://cdn.jsdelivr.net/npm/vue@2.7"></script>
<body>
<header>
<%@ include file="./admin_header.jsp" %>
</header>
<nav>
<%@ include file="./admin_menu.jsp"%> 
</nav>
<!-- FAQ 등록 시작 -->
<main class="maincss">
<section class="page_section" id="box">
<form id="f">
<p>FAQ 관리등록</p>
<div class="listbody">
    <div class="procho">
       <section class="data_listsview">
       <ol>
       <li>질문 제목</li>
       <li><input type="text" class="notice_in in1" v-model="data1" name="ftitle"></li>
       <li>글쓴이</li>
       <li><input type="text" class="notice_in in2" readonly value="${adminName}" name="fwriter"></li> <li style="height:520px;">질문 내용</li>
       <li style="height:520px; padding-top: 10px;">
       <textarea class="notice_in in3" id="boardtext" v-model="data2" name="ftext"></textarea>
       </li>      
       </ol>
       <span class="notice_btns">
       <input type="button" value="FAQ 등록" class="meno_btn2" @click="meno_btn2()"></span>
       </section>
    </div>
</div> 
</form>
</section>
</main>
<!-- FAQ 등록 끝 -->
<%@ include file="./admin_footer.jsp"%>
</body>
<script src="./js/admin_faq_write.js?v=<%=today%>"></script>
<script src="./ckeditor/ckeditor.js"></script>
</html>