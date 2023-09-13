<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<script type="text/javascript" src="./script/member.js"></script>
</head>
<body>
	<header>
		<h1>그린 아카데미 - 회원 가입</h1>
	</header>
	<section>
		<form id="tableBox" name="frm" method="post" action="productId">
			<table>
				<tr>
					<th>이름 :</th>
					<td><input type="text" name="username" placeholder="이름" required="required">
					</td>
				</tr>
				<tr>
					<th>아이디 :</th>
					<td><input type="text" name="userid" size="20" required placeholder="아이디">
						<input type="hidden" name="checkId" size="20" value="uncheck">
						<button type="button" onclick="idCheck()">중복체크</button></td>
				</tr>
				<tr>
					<th>비밀번호 :</th>
					<td><input type="password" name="userpw" placeholder="비밀번호" required="required"></td>
				</tr>
				<tr>
					<th>비밀번호 확인 :</th>
					<td><input type="password" name="userpw_check"
						placeholder="비밀번호 확인" required="required"></td>
				</tr>
				<tr>
					<th>반 선택</th>
					<td><select name="ban">
							<option value="A">A반</option>
							<option value="B">B반</option>
					</select></td>
				</tr>
				<tr>
					<td><label> <input type="radio" value="학생"
							name="usergrade" checked="checked">학생
					</label> <label> <input type="radio" value="선생" name="usergrade">선생
					</label></td>
				</tr>
				<tr>
					<td>
						<button type="submit" onclick="return productCheck()">회원
							가입</button>
						<button type="reset">다시 작성</button>
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>