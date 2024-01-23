<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<header class="headercss">
    <div class="header_div">
        <p id="welcomeMessage"><img src="./img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
        <p id="logoutLink"><span id="adminName"></span> ${adminName} 관리자님  <a href="#">[개인정보 수정]</a> <a href="#" onclick="logout()">[로그아웃]</a></p>
    </div>
</header>