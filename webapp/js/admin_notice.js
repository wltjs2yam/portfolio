function logout() {
	location.href = '/admin/logout.do';
}

function abc(midx){
	window.location.href='./notice_view.do?midx=' + midx;
};

// 전체 선택 체크박스 클릭 시 개별 체크박스 모두 선택 또는 해제
function toggleCheckboxes() {
  var checkboxes = document.querySelectorAll('input[type="checkbox"]');
  var checkAllCheckbox = checkboxes[0];
  for (var i = 1; i < checkboxes.length; i++) {
    checkboxes[i].checked = checkAllCheckbox.checked;
  }
}

// 개별 체크박스 클릭 시 전체 선택 체크박스 상태 변경
function updateCheckAll() {
  var checkboxes = document.querySelectorAll('input[type="checkbox"]');
  var checkAllCheckbox = checkboxes[0];
  var checkedCount = 0;
  for (var i = 1; i < checkboxes.length; i++) {
    if (checkboxes[i].checked) {
      checkedCount++;
    }
  }
  checkAllCheckbox.checked = (checkedCount === checkboxes.length - 1);
}

// 전체 선택 체크박스 클릭 시 개별 체크박스 상태 변경 이벤트 등록
var checkAllCheckbox = document.querySelector('input[type="checkbox"]');
checkAllCheckbox.addEventListener('click', toggleCheckboxes);

// 개별 체크박스 클릭 시 전체 선택 체크박스 상태 변경 이벤트 등록
var checkboxes = document.querySelectorAll('input[type="checkbox"]');
for (var i = 1; i < checkboxes.length; i++) {
  checkboxes[i].addEventListener('change', updateCheckAll);
}

var html = new Vue({
	el:"#box",
	data:{
		abc:[],
		def:[]
	},
	methods:{
		border_del:function(){
			
			if(confirm("해당 데이터 삭제시 복구 되지 않습니다. 삭제 하시겠습니까?")){
             // f.method = 'GET';
             // f.enctype = 'application/x-www-form-urlencoded';
             location.href= "./notice_deleteokay.do?midx="+this.abc+"&url="+this.def;
             // f.submit();
			}			
		},
		border_add:function(){
			location.href='./admin_notice_write_page.do';
				
		},
		ck:function(midx,url){
			this.abc.push(midx)
			this.def.push(url)
		}
	}
	
});