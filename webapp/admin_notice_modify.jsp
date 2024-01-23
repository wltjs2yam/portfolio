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
${script}
<!DOCTYPE html>
<html lang="ko">  
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=${today}">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/notice.css?v=${today}">
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
   <form id="f" enctype="multipart/form-data" >
    <main class="maincss">
    <input type="hidden" name="midx" value="${one.get(0)}" v-model="midx">
    <input type="hidden" name="mcheck" value="${one.get(1)}" v-model="midx">
    <section id="abcd">
        <p>공지사항 수정 페이지</p>
        <div class="write_view">
        <ul>
            <li>공지번호</li>
            <li>${one.get(0)} 번째 공지글</li>
        </ul>
        <ul>
            <li>공지사항 여부</li>
            <li>
                <label class="label_notice"><em class="cbox"><input type="checkbox" name="mcheck" value="0"></em> 공지 출력</label> ※ 공지출력을 체크할 시 해당 글 내용은 최상단에 노출 되어 집니다.
            </li>
        </ul>
        <ul>
            <li>공지사항 제목</li>
            <li>
                <input type="text" class="notice_input1" v-model="data1" value="${one.get(2)}" name="mtitle"> ※ 최대 150자까지 입력이 가능
            </li>
        </ul>
        <ul>
            <li>글쓴이</li>
            <li>
                <input type="text" class="notice_input2" readonly value="${adminName}" name="mname"> ※ 관리자 이름이 노출 됩니다.       
            </li>
        </ul>
        <ul>
            <li>첨부파일</li>
            <!-- v-if 공백을 한 이유는 Back-end 변수에 값이 없을 경우 파일을 신규로 첨부할 수 있도록 조정함 -->
            <li >
                <input type="file" name="userfile" multiple="multiple" > ※ 새로운 첨부파일 적용시 기본 첨부파일은 삭제 됩니다.
                <em class="fileview">기존 첨부 파일명 :   ${one.get(3)}</em>
            </li>
        </ul>
        <ul class="ul_height">
            <li>공지내용</li>
            <li>
                <textarea class="notice_input3" id="boardtext" name="mtext" >${one.get(4)}</textarea>
            </li>
        </ul>
        </div>
        
        <div class="board_btn">
            <button type="button" class="border_list" @click="border_list()">공지목록</button>
            <button type="button" class="border_modify" @click="border_modify()">공지수정 완료</button>
        </div>
    </section>
    <section></section>
    <section></section> 
    </main>
     </form>
    <%@ include file="./admin_footer.jsp" %>
    </body>
    <script>
    var title = "${one.get(2)}";
    var filenm = "${one.get(3)}";
    console.log(filenm);
    </script>
    <script src="./js/admin_notice_modify.js?v=${today}"></script>
    <script src="./ckeditor/ckeditor.js"></script>
    </html>