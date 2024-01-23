var html = new Vue({
	el:"#box",
	data:{
		data:[],
		part:part
	},
	methods:{
		payno:function(){
                   console.log(this.data);
			 if(confirm("해당 데이터 삭제시 복구 되지 않습니다. 삭제 하시겠습니까?")){
			       location.href='./pay_deleteok.do?aidx='+this.data;
			} 
		}
	}
	
});

function logout() {
	location.href = '/admin/logout.do';
}