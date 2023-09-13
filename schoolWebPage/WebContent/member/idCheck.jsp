<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 체크 페이지</title>
<script type="text/javascript" src="./script/member.js"></script>
</head>
<body>
	<h1>아이디 중복 체크</h1>
	<br>

	<form action="checkId" method="get" name="frm">
		아이디 : <input type="text" name="userid" value="${userid}" disabled="disabled">
		<br>
		<c:if test="${result == 1 }"> 
			<script type="text/javascript">
				opener.document.frm.userid.value = '';
				alert('이미 사용중인 아이디 입니다.');
				self.close();
			</script>
		</c:if>
		<c:if test="${result == 0 }"> 
			<p style="color: red;">사용 가능한 아이디 입니다.</p>
			<button type="button" onclick="ok()" style="cursor: pointer;">사용하기</button>
		</c:if>
	
	</form>


</body>
</html>