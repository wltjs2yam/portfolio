<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL 표현식으로 리스트 출력 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
   Date today = new Date();
   request.setAttribute("today",today);
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
    <link rel="stylesheet" type="text/css" href="./css/category.css?v=2">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    <script src="./js/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2"></script>
</head>
<body>
${script}
       <header>
<%@ include file="./admin_header.jsp" %>
</header>
<nav>
<%@ include file="./admin_menu.jsp"%> 
</nav>
        <div id="box">
        <form id="f">
        <main class="maincss">
        <section>
        
        <input type="hidden" name="aidx" v-model="aidx">
            <p>항공 코드 수정 페이지</p>
            <div class="cate_insert">
                <ul>
                    <li>공항코드</li>
                    <li><input type="text" class="cate_input1" style="margin-right: 10px;" name="airplane" value="${air.get(1)}"  @input="convertToUpperCase"></li>
                    <li>※ 공항코드는 무조건 대문자로 입력 되도록 합니다.</li>
                </ul>
                <ul>
                    <li>항공사명</li>
                    <li>
                        <input type="text" class="cate_input3" style="margin-right: 10px;" name="airname" value="${air.get(2)}" >
                    </li>
                    <li>※ 항공사명은 특수문자는 입력 되지 않습니다.</li>
                </ul>
                <ul>
                    <li>항공코드</li>
                    <li><input type="text" class="cate_input3" style="margin-right: 10px;" name="aircode" value="${air.get(3)}"> <input type="button" value="중복확인" title="중복확인" class="product_btn" @click="checkbutton()"></li>
                    <li>※ 항공코드는 중복 입력이 되지 않습니다.</li>
                </ul>
                <ul>
                    <li>항공편명</li>
                    <li>
                        <input type="text" class="cate_input3" list="lg_menu" name="airflight" value="${air.get(4)}">
                    </li>
                    <li>※ 예시) Korean Air, Jeju Air, Air Busan |&nbsp;&nbsp;<a href="https://www.airportal.go.kr/knowledge/airlines/KgAirline01.jsp?area=name" target="_blank" style="color: red;">예시 사이트</a></li>
                </ul>
                <ul>
                    <li>사용 유/무</li>
                    
                    <c:if test="${air.get(5) == 'N'}">
                    <li>
                       <label class="rmargin"><input type="radio" name="airuse" value="Y">사용함 </label>
                       <label class="rmargin"><input type="radio" name="airuse" checked value="N">사용안함</label>
                    </li>
                    </c:if>
                    <c:if test ="${air.get(5) == 'Y'}">                    
                    <li>
                        <label class="rmargin"><input type="radio" name="airuse" checked value="Y">사용함 </label>
                        <label class="rmargin"><input type="radio" name="airuse" value="N">사용안함</label>
                    </li>
                    </c:if>
                    <li>※ 사용안함으로 선택하게 되면 항공편 등록시 해당 항공코드가 출력 되지 않습니다.</li>
                </ul>
            </div>
            <div class="subpage_view4" style="text-align:center;">
                <input type="button" value="수정완료" title="수정완료" class="p_button p_button_color2" style="margin-right: 5px;" @click="airmodify()">
                <input type="button" value="항공 코드 리스트" title="항공 코드 리스트" class="p_button p_button_color1" style="margin-right: 5px;" @click="p_button_color1()">
                <input type="button" value="항공 코드 삭제" title="항공 코드 삭제" class="p_button p_button_color3" @click="p_button_color3(${air.get(0)})">
                </span>
            </div>
         
        </section>
        </main>
        </form>
        </div>
        
<%@ include file="./admin_footer.jsp"%>
</body>
<script src="./js/admin_category_modify.js?v=${today}">
</script>
</html>