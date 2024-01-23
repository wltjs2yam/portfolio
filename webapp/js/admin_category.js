function logout() {
	location.href = '/admin/logout.do';
}

function abc(aidx){
	window.location.href='./air_list.do?aidx=' + aidx;
};


// 전체 선택
function toggleCheckboxes() {
  var checkboxes = document.querySelectorAll('input[type="checkbox"]');
  for (var i = 0; i < checkboxes.length; i++) {
    checkboxes[i].checked = true;
  }
}


// 전체 해제
function clearCheckboxes() {
  var checkboxes = document.querySelectorAll('input[type="checkbox"]');
  for (var i = 0; i < checkboxes.length; i++) {
    checkboxes[i].checked = false;
  }
}

//전체 선택 후 하나만 해제했을 때, 전체 선택도 해제 해주기
function updateCheckAll() {
  var checkboxes = document.querySelectorAll('input[type="checkbox"]');
  var checkAllCheckbox = checkboxes[0];
  
  var isAnyUnchecked = false;
  for (var i = 1; i < checkboxes.length; i++) {
    if (!checkboxes[i].checked) {
      isAnyUnchecked = true;
      break;
    }
  }
  
  checkAllCheckbox.checked = !isAnyUnchecked;
}

//개별 선택으로 전체 다 선택되었을 때, 전체 선택에도 체크 해주기
function updateCheckIndividual() {
  var checkboxes = document.querySelectorAll('input[type="checkbox"]');
  var checkAllCheckbox = checkboxes[0];
  
  var isAllChecked = true;
  for (var i = 1; i < checkboxes.length; i++) {
    if (!checkboxes[i].checked) {
      isAllChecked = false;
      break;
    }
  }
  
  checkAllCheckbox.checked = isAllChecked;
}

var html = new Vue({
	 el:"#box",
     data:{
	     abc:[],
         part:part
	},
	methods:{
		p_button_color1:function(){
			alert("현재 서비스 준비중입니다.");
		},
		p_button_color2:function(){
			location.href='./air_newcode.do';
		},
		p_button:function(){
			console.log(this.abc)
			if(confirm("해당 데이터 삭제시 복구 되지 않습니다. 삭제 하시겠습니까?")){
             // f.method = 'GET';
             // f.enctype = 'application/x-www-form-urlencoded';
              location.href= "./category_deleteokay.do?aidx="+this.abc;
             // f.submit();
			}	
		},
		ck:function(aidx){
			this.abc.push(aidx)
			console.log(this.abc)
		}
	}
	
});