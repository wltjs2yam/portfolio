<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Date today = new Date();
%>
<!-- JSTL 표현식으로 리스트 출력 -->
<%@ taglib prefix="app" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 배열 갯수를 확인하는 라이브러리 & JSTL 명령어 length,split -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/notice.css?v=5">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<script src="https://cdn.jsdelivr.net/npm/vue@2.7"></script>
<body>
    <header>
<%@ include file="./admin_header.jsp" %>
</header>
   <nav>
<%@ include file="./admin_menu.jsp"%> 
</nav>
	<div id="notice">
	<form id="g">
    <main class="maincss">
    <input type="hidden" value="${one.get(1)}">
    <section> 
        <p>공지사항 VIEW 페이지</p>
        <div class="write_view">
        <ul>
            <li>공지번호</li>
            <li>${one.get(0)} 번째 공지글</li>
        </ul>
        <ul>
            <li>공지사항 여부</li>
             <app:if test = "${one.get(1) == '1'}">
                <li><label class="label_notice"><em class="cbox"><input type="checkbox" checked></em> 공지 출력</label> ※ 공지출력을 체크할 시 해당 글 내용은 최상단에 노출 되어 집니다.</li>
             </app:if>
             <app:if test = "${one.get(1) == '0'}">
                <li><label class="label_notice"><em class="cbox"><input type="checkbox"></em> 공지 출력</label> ※ 공지출력을 체크할 시 해당 글 내용은 최상단에 노출 되어 집니다.</li>
             </app:if>
        </ul>
        <ul>
            <li>공지사항 제목</li>
            <li>
                ${one.get(2)}
            </li>
        </ul>
        <ul>
            <li>글쓴이</li>
            <li>
                ${one.get(3)}   
            </li>
        </ul>
        <ul>  
            <li>첨부파일</li>
            <li><a style="color:#333333" href="https://kjs539967.cdn1.cafe24.com/${one.get(4)}" target="_blank">
                ${one.get(4)}
                첨부파일이 있을 경우 첨부파일명 출력 및 클릭시 다운로드 됩니다.
                </a>
            </li>
        </ul>
        <ul class="ul_height">
            <li>공지내용</li>
            <li>
                <div class="notice_input3" style="overflow:hidden; overflow-y: auto;">${one.get(5)}</div>
            </li>
        </ul>
        </div>
        <div class="board_btn">
            <button type="button" class="border_list" @click="border_list()">공지목록</button>
            <button type="button" class="border_modify" @click="border_modify(${one.get(0)})">공지수정</button>
            <button type="button" class="border_del" @click="border_del(${one.get(0)},'${one.get(4)}')">공지삭제</button>
        </div>
    </section>
    <section></section>
    <section></section>
    
    </main>
    </form>
    </div>
    <footer>
<%@ include file="./admin_footer.jsp" %>
</footer>
    </body>
<script src="./js/admin_notice_view.js?v=<%=today%>"></script>
<script>
    var message = "${msg}";
    if (message !== "") {
        alert(message);
    }
</script>
    </html>