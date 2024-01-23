function logout() {
	location.href = '/admin/logout.do';
}


const newair = document.getElementById("p_button p_button_color1");
newair.addEventListener('click',function(){
	window.location.href= './product_new.do';
});

const newcode = document.getElementById("p_button p_button_color2");
newcode.addEventListener('click',function(){
	window.location.href= './air_newcode.do';
});