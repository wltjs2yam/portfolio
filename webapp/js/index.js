var html = new Vue({
	el:"#box",
	data:{
	  data1:"",
      data2:"",

	},
	methods:{
		btn:function(){
			if(this.data1 == ""){
				alert("관리자 아이디를 입력하세요!");
			}
			else if(this.data1.length < 5){
				alert("아이디는 최소 5글자 입니다.");
			}
			else if(this.data2 == ""){
				alert("관리자 패스워드를 입력하세요!");
			}
			else if(this.data2.length < 5){
				alert("패스워드는 최소 5글자 입니다.");
			}
		    else {
			    f.method = "POST";
                f.enctype = "application/x-www-form-urlencoded";
                f.action = "./login.do";
                f.submit();
		    }
		}
	}
});

const nm = document.getElementById("newmember");
nm.addEventListener('click',function(){
	window.location.href= './add_master'
});

