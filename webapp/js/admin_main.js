function logout() {
	location.href = '/admin/logout.do';
}




document.querySelectorAll('.approval_toggle_switch').forEach(function(btn) {
	btn.addEventListener('click', async function(e) {
		e.stopPropagation();
		e.preventDefault();
		let midx = this.getAttribute('data-midx');
		
		let requestData = {"midx":midx};
		
		
		const response = await fetch('/admin/approval', {
                method: 'post',
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify(requestData)
            });
		if(response.ok){
			location.href='/admin/admin_main.do';
		}
	});
}); 