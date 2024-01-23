
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>쇼핑몰 환경설정</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=${today}">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/notice.css?v=${today}">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    <script src="https://cdn.jsdelivr.net/npm/vue@2"></script>
    
</head> 
<body>
<header>
<%@ include file="./admin_header.jsp" %>
</header>
<nav>
<%@ include file="./admin_menu.jsp"%> 
</nav>
    <main class="maincss" id="page_main">
    
    <section id="box">
    <form id="f" enctype="multipart/form-data">
    <input type="hidden" name="mcheck" v-model="abcd">
        <p>공지사항 등록페이지</p>
        <div class="write_view">
        <ul>
            <li>공지사항 여부</li>
            <li>
                <label class="label_notice"><em class="cbox"><input type="checkbox" name="ck"  id="mcheck" value="1" @click="checkbox()"></em> 공지 출력</label> ※ 공지출력을 체크할 시 해당 글 내용은 최상단에 노출 되어 집니다.
            </li>
        </ul>
        <ul>
            <li>공지사항 제목</li>
            <li>
                <input type="text" class="notice_input1" name="mtitle" v-model="data1" id="mtitle"> ※ 최대 150자까지 입력이 가능
            </li>
        </ul>
        <ul>
            <li>글쓴이</li>
            <li>
                <input type="text" class="notice_input2" readonly name="mname"  id="mname" value="${adminName}"> ※ 관리자 이름이 노출 됩니다.       
            </li>
        </ul>
        <ul>
            <li>첨부파일</li>
            <li>
                <input type="file" name="userfile" id="userfile" multiple="multiple"> ※ 첨부파일 최대 용량은 2MB 입니다.       
            </li>
        </ul>
        <ul class="ul_height">
            <li>공지내용</li>
            <li>
                <textarea class="notice_input3" id="boardtext" placeholder="공지내용을 입력하세요!" name="mtext" v-model="data2"></textarea>
            </li>
        </ul>
        </div>
        <div class="board_btn">
            <button type="button" class="border_del" @click="border_del()">공지목록</button>
            <button type="button" class="border_add" @click="border_add()" id="border_add">공지등록</button>
        </div>
    </form>    
    </section>  
    <section></section>
    <section></section>
    </main>
    <footer>
    <%@ include file="./admin_footer.jsp"%>
    </footer>
    </body>
    <script src="./js/admin_notice_write.js?v=${today}"></script>
    <script src="./ckeditor/ckeditor.js"></script>
    </html>