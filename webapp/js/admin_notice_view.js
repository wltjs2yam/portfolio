function logout() {
	location.href = '/admin/logout.do';
}


var html = new Vue({
	el:"#notice",
	data:{
		
	},
	methods:{
		border_del:function(midx,url){
			console.log(url)
			if(confirm("해당 데이터 삭제시 복구 되지 않습니다. 삭제 하시겠습니까?")){
		       location.href='./notice_deleteok.do?midx='+midx+"&url="+url;
		}
	  },
       border_list:function(){
	        location.href='./notice_list.do';
      },
       border_modify:function(midx){
	        location.href='./notice_modify.do?midx='+midx;
      }
   }
});