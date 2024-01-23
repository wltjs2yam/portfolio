function logout() {
	location.href = '/admin/logout.do';
}

function abc(fidx){
	location.href='./faq_view.do?fidx=' + fidx;
};






var html = new Vue({
	 el:"#box",
     data:{
	    
     },
     methods:{	
  	     meno_btn2:function(){
	         location.href='./faq_write.do'
        },
        delbtn:function(fidx){
			if(confirm("해당 데이터 삭제시 복구 되지 않습니다. 삭제 하시겠습니까?")){
		       location.href='./faq_delete.do?fidx='+fidx;
			}			
		},
		searchbtn:function(){
		     g.submit();
		}    

    }
})