<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="admin.domain.AirCodeVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL 표현식으로 리스트 출력 -->
<%@ taglib prefix="app" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 배열 갯수를 확인하는 라이브러리 & JSTL 명령어 length,split -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%
  List<AirCodeVo> ac = (ArrayList<AirCodeVo>)request.getAttribute("adata");
  //out.print(da.get(0).getFsubject());
  request.setAttribute("ea", ac.size()); //리스트 갯수 확인
  request.setAttribute("list", ac);
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
    <link rel="stylesheet" type="text/css" href="./css/category.css?v=3">
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
<form id="f">
<main class="maincss">
<section style="height: auto;" id="box">
    <p>항공 코드 관리 페이지</p>
    <div class="subpage_view">
        <span>등록된 항공 코드 0건</span>
        <span>
            <form id="h" enctype="application/x-www-form-urlencoded" method="POST" action="./air_list.do">
            <select class="p_select1" name="part" v-model="part">
                <option value="1" >항공사명</option>
                <option value="2" >항공코드</option>
            </select>
            <input type="text" class="p_input1" placeholder="검색어를 입력해 주세요" name="search" value="${search}">
            <input type="submit" value="검색" title="검색" class="p_submit">
            </form>
        </span>
    </div>
    <div class="subpage_view2">
        <ul>
            <li><input type="checkbox" onclick="toggleCheckboxes()"></li>
            <li>공항코드</li>
            <li>항공사명</li>
            <li>항공코드</li>
            <li>항공편명</li>
            <li>사용 유/무</li>
            <li>관리</li>
        </ul>
        <app:if test="${ea > 0}">
        <app:forEach var="alist" items="${list}" varStatus="no">
        <ul>
            <li><input type="checkbox" onclick="updateCheckAll()" @click="ck(${alist.getAidx()})"></li>
            <li style="text-align: left; text-indent: 5px;">${alist.getAirplane()}</li>
            <li style="text-align: left; text-indent: 5px;">${alist.getAirname()}</li>
            <li style="text-align: left; text-indent: 5px;">${alist.getAircode()}</li>
            <li style="text-align: left; text-indent: 5px;">${alist.getAirflight()}</li>
            <li>${alist.getAiruse()}</li>
            <li onclick="location.href='./air_modifycode.do?aidx=${alist.getAidx()}'">[수정]</li>
        </ul>
        </app:forEach>
       </app:if>
        <ul>
            <app:if test="${fn:length(list) == 0}">
            <li style="width: 100%;">등록된 항공코드가 없습니다.</li>
            </app:if>
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
        <input type="button" value="공항코드 삭제" title="공항코드 삭제" class="p_button" @click="p_button()">
        <span style="float: right;">
        <input type="button" value="항공편 리스트" title="항공편 리스트" class="p_button p_button_color1" @click="p_button_color1()">
        <input type="button" value="항공코드 등록" title="항공코드 등록" class="p_button p_button_color2" @click="p_button_color2()">
        </span>
    </div>
</section>
</main>
</form>
<%@ include file="./admin_footer.jsp" %>
</body>
<script>
var part = '${part}'
</script>
<script src="./js/admin_category.js?v=<%=today%>"></script>
</html>