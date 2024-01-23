function logout() {
	location.href = '/admin/logout.do';
}

var html = new Vue({
	 el:"#box",
     data:{
	    data1:"",
        data2:"",
        data3:"",
        data4:"",
        data5:"",
        data6:"",
        data7:"",
        data8:"",
        data9:"",
        data10:"",
        data11:"",
        data12:"",
        data13:"",
        data14:"",
        data15:"",
        data16:"",
        data17:"",
       
        method: "POST",
        enctype: "multipart/form-data",
        action: "./save.do"
     },
     methods:{
	    
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