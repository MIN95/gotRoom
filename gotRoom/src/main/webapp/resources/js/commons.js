$(document).ready(function(){
	$("#logout").on("click",function(){
		 if(confirm("로그아웃하시겠습니까?") == true){    //확인
			 window.location.replace('/logout');
		 }else{   //취소
		     return false; 
		 }
	});
	$("#arrowLeft").on("click",function(){
		$('#searchModal').modal('hide');
	}); 
});

