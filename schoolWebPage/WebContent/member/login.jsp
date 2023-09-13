<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
  <section>
    <div class="wrap">
      <div class="top_box">
        <p>그린 컴퓨터 - 로그인</p>
      </div>
      <hr>
      <form action="login" method="post" class="form_box">
        <p>아이디</p>
        <input type="text" placeholder="아이디" name="userid" value="${userid}">
        <p>비밀번호</p>
        <input type="password" placeholder="비밀번호" name="userpw">
        <button type="submit">로그인</button>
      </form>
      <hr>
      <div class="bot_box">
        <p>
          ${message}
        </p>
        <a href="productId">회원가입</a>
      </div>
    </div>
  </section>
  <footer class="footer"> <p>copyright (c) 2022 green all rights reserved</p></footer>
</body>
</html>