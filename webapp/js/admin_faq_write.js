function logout() {
	location.href = '/admin/logout.do';
}


window.onload = function(){
   var ck = CKEDITOR.replace("boardtext",{
      width:950,
      height:400
   });
}


var html = new Vue({
	el:"#box",
   data:{
	    data1: "",
        data2: "",
        method: "POST",
        enctype: "multipart/form-data",
        action: "./notice_faq_writeok.do"
     },
  methods:{
	    meno_btn2:function() {
		   var ck = CKEDITOR.instances.boardtext.getData();
           
           if(this.data1 == "") {
	          alert("제목을 입력해주세요.");
          }
          else if(ck == ""){
	          alert("내용을 입력해주세요.");
         }
          else {
        	 f.method = this.method;
        	 f.action = this.action;
        	 f.enctype = this.enctype;
        	 f.submit();
         }
	   }
    }	
});