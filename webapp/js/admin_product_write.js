function logout() {
	location.href = '/admin/logout.do';
}


var html = new Vue({
	 el:"#box",
     data:{
	
    },
    methods:{
	   product_btn:function(){
		   location.href='./air_newcode.do'
	   }
    }
	
});