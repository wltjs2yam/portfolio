function logout() {
	location.href = '/admin/logout.do';
}


var html = new Vue({
	  el:"#box",
	  data:{
		data1:"",
		data2:"",
		data4:"",
		abc:""
	  },
      methods:{
		newcode:function(){
			if(this.data1 == ""){
				alert("공항코드를 입력해주세요.")
			}
			else if(f.aircode.value=="") {
				alert("항공코드를 다시 확인해주세요.")
			}
			else if (this.abc == "") {
				alert("중복확인을 완료하여 주세요")
			}
			else {
				f.method = this.method;
                f.enctype = this.enctype;
                f.action = "./air_newcodeok.do";
                f.submit();
			}
		},
		convertToUpperCase(){
			this.data1 = this.data1.toUpperCase();
		},
		codeList:function(){
			location.href='./air_list.do';
		},
	    checkbutton:function(){
            if(f.aircode.value==""){
			alert("항공 코드를 입력해주세요");
		}
		else {
			fetch("./aircodecheck.do?aircode="+f.aircode.value).then(function(aa){
				return aa.text();
			}).then(function(bb){
				html.message(bb);
			}).catch(function(error){
				console.log("통신오류 발생!!");
			});
		}
		
	}	,
	    message:function(bb){
		this.abc="";
		if(bb == 0){
			alert("사용 가능한 항공코드 입니다.");
			this.abc = "0";
			f.aircode.readOnly = true;
		}
		else {
			alert("사용 불가능한 항공코드 입니다.");
		
		}
			console.log(this.abc);
	  }
    }
});
