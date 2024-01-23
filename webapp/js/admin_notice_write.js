window.onload = function(){
   var ck = CKEDITOR.replace("boardtext",{
      width:800,
      height:300
   });
}

function logout() {
	location.href = '/admin/logout.do';
}


var html = new Vue({
	el:"#box",
	data:{
		data1:"",
		data2:"",
		abcd:"0",
		method: "POST",
        enctype: "multipart/form-data",
        action: "./notice_writeok.do"
	},
	methods:{
		border_add:function(){
    	  var ck = CKEDITOR.instances.boardtext.getData();

         if(this.data1 == ""){
            alert("공지사항 제목을 입력하세요.");
            }
         else if(ck == "") {
             alert("공지사항 내용을 입력하세요.");	 
          }
 
         else {
        	 f.method = this.method;
        	 f.action = this.action;
        	 f.enctype = this.enctype;
        	 f.submit();
         }
      },
       border_del:function(){
            location.href='./notice_list.do'	
      },
      checkbox:function(){
	        if(f.ck.checked == true){
		      this.abcd = "1";
	    }
        else {
	         this.abcd = "0";
}
     }
   }
	
});

