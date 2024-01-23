var urls = document.location.search;
var adata = urls.split("?midx=")

function logout() {
	location.href = '/admin/logout.do';
}


window.onload = function(){
   var ck = CKEDITOR.replace("boardtext",{
      width:800,
      height:300
   });
}


var html = new Vue({
	el:"#abcd",
	data:{
	  	htmltag:filenm,
        data1:title,
        midx:adata[1],
        method: "POST",
        enctype: "multipart/form-data",
        action: "./notice_modifyok.do"
	},
	methods:{
		border_list:function(){
			location.href="./notice_list.do";
		},
		border_modify:function(){
			var ck = CKEDITOR.instances.boardtext.getData();
			
			if(this.data1 == ""){
				alert("공지사항 제목을 입력하세요.")
			}
			else if(ck == ""){
				alert("내용을 입력해주세요.")
			}
			 f.method = this.method;
        	 f.action = this.action;
        	 f.enctype = this.enctype;
        	 f.submit();
		}	   
	}
});

