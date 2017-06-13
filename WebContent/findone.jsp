<!DOCTYPE HTML>
<%@page import="com.javabean.Song"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>FindOne</title>
</head>
<body>
	<h2>查看当前登录的用户信息</h2>
	<form action="findSong" method="get">
		Sname:<input type="search" name="sname" ><br>
		<input type="submit" value="tijiao">&nbsp;&nbsp;&nbsp;&nbsp;
	</form>
	<hr>
	<a href="index.jsp"><button>主页</button>	</a>
	<hr>
	<hr>
	<%
		Song songone = (Song)session.getAttribute("songone");
		if (songone.getSname() != null){
		%>
	<table border="2">
		<tr>
			<th>Sname</th>
			<th>Simage</th>
			<th>songname</th>
			<th>sdate</th>
			<th>songpath</th>
		</tr>
			<tr>
				<td><%= songone.getSname() %></td>
				<td><img src="findImageBySname?sname=<%=songone.getSname()%> "> </td>
				<td><%= songone.getSongname() %></td>
				<td><%= songone.getSdate() %></td>
				<td><%= songone.getSongpath() %></td>
			</tr>	
	</table>
	<%
		}else {
			%>
				<h3>song是空的，没有数据可以显示。</h3>
			<%
		}
		%>
</body>
</html>