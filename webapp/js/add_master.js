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
       method: "POST",
       enctype: "multipart/form-data",
       action: "./joinok.do"
	},
	methods:{
		idcheck:function(){
           if(this.data1.length < 5){
              alert("아이디는 5자 이상 입력하셔야 합니다.");
           } 
           else {  //vue - ajax 통신
             fetch("./idcheck.do?mid="+this.data1).then(function(aa){
                  return aa.text();
             }).then(function(bb){
                html.message(bb);              
             }).catch(function(error){
                  console.log("통신오류 발생!!");
             });
           }
        },
        message:function(bb){
        if(bb.value != null){
              alert("해당 아이디는 사용하실 수 없습니다.");
             }
        else {
             alert ("사용 가능한 아이디 입니다.");
             }
        },
      		
		ok:function(){
			var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
			
			if(this.data1 == ""){
				alert("생성할 관리자 아이디를 입력하세요.");
			}
			else if(this.data1.length < 5){
				alert("아이디는 최소 5글자 부터 사용 가능합니다.");
			}
			else if(this.data2 == ""){
				alert("접속할 패스워드를 입력하세요.")
			}
			else if(this.data2.length < 5){
				alert("패스워드는 최소 5글자 부터 사용 가능합니다.")
			}
			else if(this.data2 != this.data3){
				alert("동일한 패스워드를 입력하세요.")
			}
			else if(this.data4 == ""){
				alert("담당자 이름을 입력하세요.")
			}
			else if(this.data5 == ""){
				alert("담당자 이메일을 입력하세요")
			}
			
			
			else {
				 f.method = this.method;
                f.enctype = this.enctype;
                f.action = this.action;
                f.submit();
			}
		}		
	}
});

document.querySelector('#id_check_button').addEventListener('click', async function(e){
	let mid = document.querySelector('#mid').value;
	
	if(mid.length < 5){
		alert("아이디는 최소 5글자 부터 사용 가능합니다.");
		return;
	}
	
	const response = await fetch('/admin/idcheck.do?mid='+mid, {
                method: 'get'
    });

	const data = await response.json();

	if(data.duplicated){
		alert('사용 불가능한 아이디입니다.');
	}else{
		alert('사용 가능한 아이디입니다.');
	}
});









