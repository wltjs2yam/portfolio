<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navcss">
	<div class="nav_div">
		<ol>
			<li title="사이트 기본설정" id="setting" onclick="a1()">사이트 기본설정</li>
			<li title="회원관리" id="membership" onclick="a2()">회원관리</li>
			<li title="공지사항" id="announcement" onclick="a3()">공지사항</li>
			<li title="항공사 등록" id="airnumber" onclick="a4()">항공사 및 번호 등록</li>
			<li title="좌석 및 예매등록" id="seat" onclick="a5()">좌석 및 예매등록</li>
			<li title="예매 확인 리스트" onclick="a6()">예매 리스트</li>
			<li title="고객관리 FAQ" onclick="a7()">고객관리 FAQ</li>
		</ol>
	</div>
</nav>

<script>

       function a1() {
    	   location.href = './admin_siteinfo.do';
       }
       function a2() {
    	   location.href = './admin_main.do';
       }
       function a3() {
    	   location.href = './notice_list.do';
       }
       function a4() {
    	   location.href = './air_list.do';
       }
       function a5() {
    	   alert("현재 서비스 준비중입니다.");
       }
       function a6() {
    	   location.href = './ticketing_list.do'
       }
       function a7(){
    	   location.href= 'faq_list.do';
       }
       
       

	
</script>