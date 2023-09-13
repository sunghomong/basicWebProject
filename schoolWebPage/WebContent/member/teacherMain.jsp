<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선생 메인 페이지</title>
<style type="text/css">
</style>
<script src="./script/jquery-3.7.1.js"></script>
</head>
<body>
	<header>
		<h1>${userInfo.ban}반&nbsp;&nbsp;담임
			&nbsp;&nbsp;${userInfo.username}</h1>
	</header>
	<hr>
	<section>
		<a
			href="banGradeCheck?ban=${userInfo.ban}&usergrade=${userInfo.usergrade}">과목
			성적 조회</a>&nbsp;&nbsp; <a href="banMemberList?ban=${userInfo.ban}">학생
			목록</a>&nbsp;&nbsp; <a href="noGradeCheck?ban=${userInfo.ban}"> 성적 등록 </a>

	</section>
	<hr>

	<c:if test="${sessionScope.chResult == '미등록자조회'}">
		<table id="studentTable">
			<tr>
				<th>학생</th>
				<th>이름</th>
				<th>번호</th>
			</tr>
			<c:forEach var="list" items="${memberList}">
				<tr>
					<td>${list.usergrade}</td>
					<td><a href="gradePut?username=${list.username}">${list.username}</a></td>
					<td>${list.num}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${sessionScope.chResult == '성적등록' }">
		<form action="gradePut" method="post" name="frm">
			<table>
				<tr>
					<td>
					${gradePutUsername} 학생 성적등록
					<input type="hidden" name="username" value="${gradePutUsername}">
					</td>
					
				</tr>
				<tr>
					<th>국어 점수 : </th>
					<td><input type="text" name="kor_score" placeholder="국어 점수" required="required"></td>
				</tr>
				<tr>
					<th>영어 점수 : </th>
					<td><input type="text" name="eng_score" placeholder="영어 점수" required="required"></td>
				</tr>
				<tr>
					<th>수학 점수 : </th>
					<td><input type="text" name="math_score" placeholder="수학 점수" required="required"></td>
				</tr>
				<tr>
					<th>과학 점수 : </th>
					<td><input type="text" name="science_score" placeholder="과학 점수" required="required"></td>
				</tr>
				<tr>
					<th>사회 점수 : </th>
					<td><input type="text" name="social_score" placeholder="사회 점수" required="required"></td>
				</tr>
				<tr>
					<td><button type="submit" style="cursor: pointer;">성적 등록</button></td>
				</tr>
			</table>
		</form>
	</c:if>


	<c:if test="${sessionScope.chResult == '반성적조회'}">
		<c:choose>
			<c:when test="${gAVo != null}">
				<div class="box">
					<table>
						<tr>
							<td>${userInfo.ban}반성적조회</td>
						</tr>
						<tr>
							<th>국어 평균 점수 :</th>
							<td>${gAVo.kor_avg}</td>
						</tr>
						<tr>
							<th>영어 평균 점수 :</th>
							<td>${gAVo.eng_avg}</td>
						</tr>
						<tr>
							<th>수학 평균 점수 :</th>
							<td>${gAVo.math_avg}</td>
						</tr>
						<tr>
							<th>과학 평균 점수 :</th>
							<td>${gAVo.sci_avg}</td>
						</tr>
						<tr>
							<th>사회 평균 점수 :</th>
							<td>${gAVo.sc_avg}</td>
						</tr>

					</table>
				</div>
			</c:when>
			<c:when test="${gAVo == null}">
				<p style="color: red;">${userInfo.ban}반성적을조회할수없습니다.</p>
			</c:when>
		</c:choose>
	</c:if>

	<c:if test="${sessionScope.chResult == '학생리스트'}">
		<c:choose>
			<c:when test="${memberList != null}">
				<div class="box">
					<table>
						<tr>
							<td>${userInfo.ban}반학생명단${memberCount}명</td>
						</tr>
						<c:forEach var="list" items="${memberList}">
							<tr>
								<td>${list.num}</td>
								<td>${list.userid}</td>
								<td><a
									href="gradeCheck?username=${list.username}&usergrade=${userInfo.usergrade}">${list.username}</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:when>
			<c:when test="${memberList == null}">
				<p style="color: red;">${userInfo.ban}반학생명단을조회할수없습니다.</p>
			</c:when>
		</c:choose>
	</c:if>

	<c:if test="${sessionScope.chResult == '학생성적조회'}">
		<c:choose>
			<c:when test="${grades != null}">
				<div class="box">
					<table>
						<tr>
							<td>${grades.username}학생성적조회</td>
						</tr>
						<tr>
							<th>국어 점수 :</th>
							<td>${grades.kor_score}점</td>
						</tr>
						<tr>
							<th>영어 점수 :</th>
							<td>${grades.eng_score}점</td>
						</tr>
						<tr>
							<th>수학 점수 :</th>
							<td>${grades.math_score}점</td>
						</tr>
						<tr>
							<th>과학 점수 :</th>
							<td>${grades.science_score}점</td>
						</tr>
						<tr>
							<th>사회 점수 :</th>
							<td>${grades.social_score}점</td>
						</tr>
						<tr>
							<th>총점 :</th>
							<td>${totalGrade}점</td>
						</tr>
						<tr>
							<th>평균 :</th>
							<td>${avgGrade}점</td>
						</tr>

					</table>
				</div>
			</c:when>
			<c:when test="${grades == null}">
				<p style="color: red;">성적을 조회할 수 없습니다.</p>
			</c:when>
		</c:choose>
	</c:if>
	<c:if test="${chResult == null}">
		<div>
			<p style="color: red;">원하는 메뉴를 선택해주세요. ${message}</p>
		</div>
	</c:if>
	<footer>
		<h2>하단 내용</h2>
	</footer>
</body>
</html>