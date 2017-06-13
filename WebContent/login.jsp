<!DOCTYPE HTML >
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login</title>
</head>
<body>
	<hr>
	<div align="center">
	<p>欢迎使用。</p>
	<%
		String info = ""; 
		info = (String)request.getSession().getAttribute("user");
		if(info == "" || info==null){
			
		} else {
			%>
			<font color="red" ><%=info %></font>
			<%
		}
	%>
	<form action="loginUser" method="post">
	<%
	String username = request.getParameter("username");
	if(username == null){
		username="";
	}
	%>
		UserEmail：<input type="text" name="username" value="<%=username %>" ><br>
		Pass Word：<input type="password" name="password" ><br>
		<input type="submit" value="Login"><br>
		<a href="registerUser.jsp"><font color="red">注册新用户</font></a>
		<a href="findpass.jsp"><font color="green">忘记密码</font></a>
	</form>
	
	</div>
</body>
</html>