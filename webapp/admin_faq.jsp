<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="admin.domain.FaqVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL 표현식으로 리스트 출력 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 배열 갯수를 확인하는 라이브러리 & JSTL 명령어 length,split -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<script>
</script>
</head>
<script src="https://cdn.jsdelivr.net/npm/vue@2.7"></script>
<body>
<header>
<%@ include file="./admin_header.jsp" %>
</header>
   <nav>
<%@ include file="./admin_menu.jsp"%> 
</nav>
<!-- FAQ 리스트 시작 -->
<main class="maincss">
<section class="page_section" id="box">
<p>FAQ 관리페이지</p>
<div class="listbody">
    <div class="procho">
       <section class="search_part">
        
        <form id="g" method="POST" enctype="application/x-www-form-urlencoded" action="./faq_list.do">
        <ol>
        <li>FAQ 모든 내용 검색</li>     
        <li>
        <input type="text" class="search_input"  name="search" >
        <input type="button" value="검색" class="datebtn" onclick="searchItems()" @click="searchbtn()" >
        </li>        
        <li></li>
        <li></li>  
        </ol>
        </form>
        
        
       </section> 
       <section class="data_listsview2">
       <ul>
        <li>QA</li>
        <li>질문/답변</li>
        <li>글쓴이</li>
        <li>등록일</li>
        <li>삭제</li>
       </ul>
          
<!-- FAQ 샘플 HTML 코드 시작 -->           
     <span>
       <c:forEach var="faq" items="${fdata}">
       <ul>
        <li>Q</li>
        <li style="text-align: left; justify-content: flex-start;" onclick="abc(${faq.fidx})">${faq.ftitle}</li>
        <li>${faq.fwriter}</li>
        <li>${fn:substring(faq.fdate,0,10)}</li>
        <li>
        <input type="button" value="삭제" class="delbtn" @click="delbtn(${faq.fidx})">
        </li>
       </ul>
       </c:forEach>
       
     </span>
<!-- FAQ 샘플 HTML 코드 끝 -->          
        
        <c:if test="${fn:length(fdata) == 0}">
       <ul class="nodatas">   
        <li>등록된 FAQ 내용 없습니다.</li>
       </ul>
       </c:if>
       <span class="notice_btns">
       <input type="button" value="FAQ 등록" class="meno_btn2" @click="meno_btn2()"></span>
       <aside>
        <div class="subpage_view3">
            <ul class="pageing">
               <li><img src="./ico/double_left.svg" onclick="goToPage(currentPage - 2)"></li>
<li><img src="./ico/left.svg" onclick="goToPage(currentPage - 1)"></li>
<li><button onclick="goToPage(1)">1</button></li>
<li><img src="./ico/right.svg" onclick="goToPage(currentPage + 1)"></li>
<li><img src="./ico/double_right.svg" onclick="goToPage(currentPage + 2)"></li>
            </ul>
        </div>
       </aside>
       </section>
    </div>
</div> 
</section>
</main>
<!-- FAQ 리스트 끝 -->
<%@ include file="./admin_footer.jsp" %>
</body>
<script src="./js/admin_faq.js?v=<%=today%>"></script>
</html>