

function productCheck() {
	
	var username = document.frm.username.value;
	var userid = document.frm.userid.value;
	var userpw = document.frm.userpw.value;
	var userpw_check = document.frm.userpw_check.value;
	var ban = document.frm.ban.value;
	var usergrade = document.frm.usergrade.value;
	
	
	if (username.length == 0 || username == "") {
		alert("이름을 입력해주세요.");
		frm.username.focus();
		return false;
	}else if(userid.length == 0 || userid == ""){
		alert("아이디를 입력해주세요.");
		frm.userid.focus();
		return false;
	}else if (document.frm.hiddenid.value == "") {
		alert("아이디 중복체크를 해주세요.");
		return false;
	}else if (userpw.length == 0 || userpw == "") {
		alert("비밀번호를 입력해주세요.");
		frm.userpw.focus();
		return false;
	}else if (userpw_check.value == 0 || userpw_check == ""){
		alert("비밀번호를 다시 입력해주세요.");
		frm.userpw_check.focus();
		return false;
	}else if (userpw_check != userpw) {
		alert("비밀번호가 일치하지 않습니다.");
		frm.userpw.focus();
		return false;
	}else if(ban.value == 0 || ban == "") {
		alert("반을 선택해주세요.");
		return false;
	}else if (usergrade.value == 0 || usergrade == "") {
		alert("신분을 선택해주세요.(학생/선생)");
		return false;
	}else {
		return true;
	}
	
}

function idCheck() {
	var frm = document.frm;
	var userid = frm.userid.value;
	
	if(userid.length == 0 || userid == "") {
		alert("아이디를 입력해주세요.");
		frm.userid.focus();
	} else {
		window.open("checkId?userid=" + userid,"_blank_1","toolbar=no, menubar=no, scrollbar=yes, width=450,height=200")
	}
}



function ok(){
	
	opener.document.frm.userid.value = document.frm.userid.value;
	opener.document.frm.checkId.value = document.frm.userid.value;
	
	self.close(); // 현재 창 닫기
}



