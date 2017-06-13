<!DOCTYPE HTML>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>RegisterUser</title>
</head>
<body>
	<h2>user register page. 注册完成后直接跳入用户用户信息完善页面。</h2>
	<hr>
	<div align="center">
		<form action="registerUser" method="post">
			UserNameEmail:<input type="email" name="uemail" ><br>
			PassWord Once:<input type="password" name="password"><br>
			PassWordAgain:<input type="password" name="password1"><br>
			<%=request.getSession().getAttribute("message") %><br>
			<font color="red">这里假装有邮箱的验证。已通过。两次密码验证也通过。</font><br>
			<input type="submit" value="Regist" >
		</form>
	</div>
</body>
</html>