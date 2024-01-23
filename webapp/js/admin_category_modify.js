var urls = document.location.search;
var adata = urls.split("?aidx=")

function logout() {
	location.href = '/admin/logout.do';
}


var html = new Vue({
	el:"#box",
	data:{
	  	aidx:adata[1],
        method:"POST",
        enctype:"application/x-www-form-urlencoded",
        action:"./air_code_modifyok.do",
        abc:""
	},
	methods:{
		airmodify:function(){
			f.method = this.method;
        	f.action = this.action;
        	f.enctype = this.enctype;
        	f.submit();
		},
		p_button_color1:function(){
			location.href="./air_list.do";
		},
		p_button_color3:function(aidx){
			if(confirm("해당 데이터 삭제시 복구 되지 않습니다. 삭제 하시겠습니까?")){
		       location.href='./category_deleteok.do?aidx='+aidx;
		 }
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
          
        },
        convertToUpperCase(){
			this.data1 = this.data1.toUpperCase();
		},
  }
});