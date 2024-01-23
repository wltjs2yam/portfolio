var urls = document.location.search;
var fdata = urls.split("?fidx=")


function logout() {
	location.href = '/admin/logout.do';
}

window.onload = function(){
   var ck = CKEDITOR.replace("boardtext",{
      width:900,
      height:400
   });
}

var html = new Vue({
	el:"#box",
	data:{
		fidx:fdata[1],
		method: "POST",
        enctype: "application/x-www-form-urlencoded",
        action: "./faq_modifyok.do"
	},
	methods:{
		meno_btn1:function(){
			location.href='./faq_list.do';
		},
		meno_btn2:function(){
			 f.method = this.method;
        	 f.action = this.action;
        	 f.enctype = this.enctype;
        	 f.submit();
		}
	}
});