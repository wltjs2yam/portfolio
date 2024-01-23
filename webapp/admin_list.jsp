<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="admin.AdminVo"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<main class="maincss">
<section>
    <p>신규등록 관리자</p>
    <ol class="new_admin_title">
        <li>NO</li>
        <li>관리자명</li>
        <li>아이디</li>
        <li>전화번호</li>
        <li>이메일</li>
        <li>담당부서</li>
        <li>담당직책</li>
        <li>가입일자</li>
        <li>승인여부</li>
    </ol>
    
    
     <c:if test="${fn:length(adminList) == 0}">
    <ol class="new_admin_none">
        <li>신규 등록된 관리자가 없습니다.</li>
    </ol>
     </c:if>
    
    ${fn:substring(notice.mdate,0,10)}
    
	<c:forEach items="${adminList}" var="admin">
		<ol class="new_admin_lists">
	            <li>${admin.midx}</li>
	            <li>${admin.mname}</li>
	            <li>${admin.mid}</li>
	            <li>${admin.mhp}</li>
	            <li>${admin.memail}</li>
	            <li>${admin.mselect1}</li>
	            <li>${admin.mselect2}</li>
	            <li>${fn:substring(admin.mdate,0,10)}</li>
	            <li>
	            
	            <c:if test="${admin.mcheck eq 'Y'}">
	            	<input type="button" value="승인" class="new_addbtn1 approval_toggle_switch" title="승인" data-midx='${admin.midx}' >
	            </c:if>
	            
	            
	            <c:if test="${admin.mcheck eq 'N'}">
	            	<input type="button" value="미승인" class="new_addbtn2 approval_toggle_switch" title="미승인" data-midx='${admin.midx}'>
	            </c:if>
	            
	            
	
	            </li>
	        </ol>
	</c:forEach>

    
</section>
<section></section>
<section></section>
</main>