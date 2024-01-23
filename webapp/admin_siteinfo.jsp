<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="admin.member_dto"%>
    
    
<%
	member_dto siteinfo = (member_dto) request.getAttribute("siteinfo");
   
%>

<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>사이트 환경설정</title>
        <link rel="stylesheet" type="text/css" href="./css/basic.css">
        <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
        <link rel="stylesheet" type="text/css" href="./css/main.css">
        <link rel="stylesheet" type="text/css" href="./css/subpage.css?v=3">
        <link rel="icon" href="./img/logo.png" sizes="128x128">
        <link rel="icon" href="./img/logo.png" sizes="64x64">
        <link rel="icon" href="./img/logo.png" sizes="32x32">
        <link rel="icon" href="./img/logo.png" sizes="16x16">
        <script src="./js/jquery.js"></script>
    </head>
<script src="https://cdn.jsdelivr.net/npm/vue@2"></script>    
<body>
<section id="box">
<form id="f">
<header>
<%@ include file="./admin_header.jsp" %>
</header>
<nav>
<%@ include file="./admin_menu.jsp" %>
</nav>
<main class="maincss">
<section>
    <p>홈페이지 가입환경 설정</p>
<div class="subpage_view">
<ul class="info_form">
    <li>홈페이지 제목</li>
    <li>
        <input type="text" value="" class="in_form1" name="stitle" v-model="data1" id="pageTitle" required="required" title=""> 
    </li>
</ul>    
<ul class="info_form">
    <li>관리자 메일 주소</li>
    <li>
        <input type="text" class="in_form2" v-model="data2" id="adminEmail" name="semail1"> ※ 관리자가 보내고 받는 용도로 사용하는 메일 주소를 입력합니다.(회원가입,인증메일,회원메일발송 등에서 사용)
    </li>
</ul> 
<ul class="info_form">
    <li>포인트 사용 유/무</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" name="spoint1" value="Y" v-model="data18">포인트 사용</label></em> 
        <em><label><input type="radio" class="ckclass" checked name="spoint1" value="N" v-model="data18">포인트 미사용</label></em>
    </li>
</ul>
<ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
    <li>회원가입시 적립금</li>
    <li>
        <input type="text" class="in_form3" maxlength="5" v-model="data3" name="sreward"> 점
    </li>
    <li>회원가입시 권한레벨</li>
    <li>
        <input type="text" class="in_form3" maxlength="1" value="1" v-model="data4" name="slevel"> 레벨
    </li>
</ul> 
</div>
<p>홈페이지 기본환경 설정</p>
<div class="subpage_view">
<ul class="info_form2">
    <li>회사명</li>
    <li>
        <input type="text" class="in_form0"  v-model="data5" name="scompany"> 
    </li>
    <li>사업자등록번호</li>
    <li>
        <input type="text" class="in_form0" v-model="data6" name="scompany_number"> 
    </li>
</ul> 
<ul class="info_form2">
    <li>대표자명</li>
    <li>
        <input type="text" class="in_form0" v-model="data7" name="sname"> 
    </li>
    <li>대표전화번호</li>
    <li>
        <input type="text" class="in_form0" v-model="data8" name="stel"> 
    </li>
</ul>
<ul class="info_form2">
    <li>통신판매업 신고번호</li>
    <li>
        <input type="text" class="in_form0" v-model="data9" name="snumber1"> 
    </li>
    <li>부가통신 사업자번호</li>
    <li>
        <input type="text" class="in_form0" name="snumber2" v-model="data23"> 
    </li>
</ul>
<ul class="info_form2">
    <li>사업장 우편번호</li>
    <li>
        <input type="text" class="in_form0" v-model="data10" name="snumber3" id="postalCode" required="required"> 
    </li>
    <li>사업장 주소</li>
    <li>
        <input type="text" class="in_form2" v-model="data11" name="saddress" id="address" required="required"> 
    </li>
</ul>
<ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
    <li>정보관리책임자명</li>
    <li>
        <input type="text" class="in_form0" v-model="data12" name="sname2"> 
    </li>
    <li>정보책임자 E-mail</li>
    <li>
        <input type="text" class="in_form1" v-model="data13" name="semail2"> 
    </li>
</ul>
</div>
<p>결제 기본환경 설정</p>
<div class="subpage_view">  
<ul class="info_form2">
    <li>무통장 은행</li>
    <li>
        <input type="text" class="in_form0" v-model="data14" name="sbank"> 
    </li>
    <li>은행계좌번호</li>
    <li>
        <input type="text" class="in_form1" @input="formatAccountNumber" v-model="data15" name="snumber4" id="accountNumber" required="required" pattern="[0-9]{3}-[0-9]{2}-[0-9]{5}"> 
    </li>
</ul>
<ul class="info_form">
    <li>신용카드 결제 사용</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" name="scard" value="Y" v-model="data19"> 사용</label></em> 
        <em><label><input type="radio" class="ckclass" checked name="scard" value="N" v-model="data19"> 미사용</label></em> ※ 해당 PG사가 있을 경우 사용으로 변경합니다.
    </li>
</ul>
<ul class="info_form">
    <li>휴대폰 결제 사용</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" name="shp" value="Y" v-model="data20"> 사용</label></em> 
        <em><label><input type="radio" class="ckclass" checked name="shp" value="N" v-model="data20"> 미사용</label></em> ※ 주문시 휴대폰 결제를 가능하게 할 것인지를 설정합니다.
    </li>
</ul>
<ul class="info_form">
    <li>도서상품권 결제사용</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" name="sgift" value="Y" v-model="data21"> 사용</label></em> 
        <em><label><input type="radio" class="ckclass" checked name="sgift" value="N" v-model="data21"> 미사용</label></em> ※ 도서상품권 결제만 적용이 되며, 그 외에 상품권은 결제 되지 않습니다.
    </li>
</ul>
<ul class="info_form2">
    <li>결제 최소 포인트</li>
    <li>
        <input type="text" class="in_form0" maxlength="5" v-model="data16" name="spoint2"> 점
    </li>
    <li>결제 최대 포인트</li>
    <li>
        <input type="text" class="in_form0" maxlength="5" v-model="data17" name="spoint3"> 점
    </li>
</ul>
<ul class="info_form" style="border-bottom:1px solid rgb(81, 61, 61);">
    <li>현금 영수증 발급사용</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" name="sreceipt" value="Y" v-model="data22"> 사용</label></em> 
        <em><label><input type="radio" class="ckclass" checked name="sreceipt" value="N" v-model="data22"> 미사용</label></em> ※ 현금영수증 관련 사항은 PG사 가입이 되었을 경우 사용가능 합니다.
    </li>
</ul>
</div>
<div class="btn_div">
    <button type="button" class="sub_btn1" title="설정 저장" @click="btn()" id="okbutton">설정 저장</button>
    <button type="button" class="sub_btn2" id="cancelButton" @click="btn2()" title="저장 취소">저장 취소</button>
</div>
</form>
</section>
</main>
<footer>
<%@ include file="./admin_footer.jsp" %>
</footer>
</section>
</body>
<!-- <script src="./js/admin_siteinfo.js?v=2"></script> -->
<script>

function logout() {
	location.href = '/admin/logout.do';
}

var html = new Vue({
	 el:"#box",
	  data:{
		    data1:"<%=siteinfo == null?"":siteinfo.getStitle()%>",
	       data2:"<%=siteinfo == null?"":siteinfo.getSemail1()%>",
	       data3:"<%=siteinfo == null?"":siteinfo.getSreward()%>",
	       data4:"<%=siteinfo == null?"":siteinfo.getSlevel()%>",
	       data5:"<%=siteinfo == null?"":siteinfo.getScompany()%>",
	       data6:"<%=siteinfo == null?"":siteinfo.getScompany_number()%>",
	       data7:"<%=siteinfo == null?"":siteinfo.getSname()%>",
	       data8:"<%=siteinfo == null?"":siteinfo.getStel()%>",
	       data9:"<%=siteinfo == null?"":siteinfo.getSnumber1()%>",
	       data10:"<%=siteinfo == null?"":siteinfo.getSnumber3()%>",
	       data11:"<%=siteinfo == null?"":siteinfo.getSaddress()%>",
	       data12:"<%=siteinfo == null?"":siteinfo.getSname2()%>",
	       data13:"<%=siteinfo == null?"":siteinfo.getSemail2()%>",
	       data14:"<%=siteinfo == null?"":siteinfo.getSbank()%>",
	       data15:"<%=siteinfo == null?"":siteinfo.getSnumber4()%>",
	       data16:"<%=siteinfo == null?"":siteinfo.getSpoint2()%>",
	       data17:"<%=siteinfo == null?"":siteinfo.getSpoint3()%>",
	       data18:"<%=siteinfo == null?"N":siteinfo.getSpoint1()%>",
	       data19:"<%=siteinfo == null?"N":siteinfo.getScard()%>",
	       data20:"<%=siteinfo == null?"N":siteinfo.getShp()%>",
	       data21:"<%=siteinfo == null?"N":siteinfo.getSgift()%>",
	       data22:"<%=siteinfo == null?"N":siteinfo.getSreceipt()%>",
	       data23:"<%=siteinfo == null?"":siteinfo.getSnumber2()%>",
	       method: "POST",
	       enctype: "multipart/form-data",
	       action: "./save.do"
	    },
    methods:{
    	formatAccountNumber() {
    	    // 하이픈 추가
    	    this.data15 = this.data15.replace(/(\d{3})(\d{2})(\d{6})/, '$1-$2-$3');
    	  },
	
	    btn:function(){
        if(this.data1== ""){
			 alert("홈페이지 제목을 입력해주세요.");
		}
		else if(this.data2 == ""){
			 alert("관리자 메일 주소를 입력해주세요.");
		}
		else if(this.data3 == ""){
			alert("회원가입시 적림금을 입력해주세요.");
		}
		else if(this.data4 == ""){
			alert("회원가입시 권한레벨을 입력해주세요.");
		}
		else if(this.data5 == ""){
			alert("회사명을 입력해주세요.");
		}
		else if(this.data6 == ""){
			alert("사업자등록번호를 입력해주세요.");
		}
		else if(this.data7 == ""){
			alert("대표자명을 입력해주세요.");
		}
		else if(this.data8 == ""){
			alert("대표전화번호를 입력해주세요.");
		}
		else if(this.data9 == ""){
			alert("통신판매업 신고번호를 입력해주세요.");
		}
		else if(this.data10 == ""){
			 alert("사업장 우편번호를 입력해주세요.");
		}
		else if(this.data11 == ""){
			 alert("사업장 주소를 입력해주세요.");
		}
		else if(this.data12 == ""){
			 alert("정보관리책임자명을 입력해주세요.");
		}
		else if(this.data13 == ""){
			 alert("정보책임자 E-mail을 입력해주세요.");
		}
		else if(this.data14 == ""){
			 alert("무통장 은행을 입력해주세요.");
		}
		else if(this.data15 == ""){
			 alert("은행계좌번호를 입력해주세요.");
		}
		else if(this.data16 == ""){
			 alert("결제 최소 포인트를 입력해주세요.");
		}
		else if(this.data17 == ""){
			 alert("결제 최대 포인트를 입력해주세요.");
		}
		
		else {
			  if (confirm("저장하시겠습니까?")) {
		     f.method = this.method;
            f.enctype = this.enctype;
            f.action = this.action;
            f.submit();
         }
		}                 
      },
      btn2:function(){
	       if (confirm("저장을 취소하시겠습니까?")) {
          // 페이지 새로 고침
            //location.reload();
         }
      }
    },
});
</script>


</html>

