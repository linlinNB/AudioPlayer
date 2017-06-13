<!DOCTYPE HTML >
<%@page import="java.io.InputStream"%>
<%@page import="com.javabean.User"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>message</title>
</head>
<body>
	<h2>
		<font color="red">Message Test</font>
	</h2>
	<%
		String string = (String) request.getAttribute("message");
	%>
	<h2>
		message:<%=string%></h2>
	<br>
	<hr>
	<p>下面显示的是用户的登录信息</p>
	<%
		User user = (User) request.getSession().getAttribute("user");
		//将显示用户信息的表格修改为input的标签，在放到一个from中，可以实现能显示能修改了。
		if (user != null) {
	%>
	<div>
		<div>
			<ol>
				<li>enctype="multipart/form-data" //设置表单上传数据的编码方式.
					enctype 参数 一共有三个属性，这个是在上传文件的时候使用的格式 //设置为了不对字符进行编码</li>
				<li>FileUpLoad参考:http://blog.csdn.net/zhoukun1008/article/details/52471776</li>
			</ol>
		</div>
		<hr><hr>
		<div align="center"><p><font color="red"><%=request.getSession().getAttribute("updateMessage")%></font></p></div>
		<hr>
		<form action="savaUpdateUser" method="post" enctype="multipart/form-data">
			<table border="1">
				<tr>
					<td align="right" width="200px">UserID</td>
					<td width="200px"><%=user.getUid()%></td>
					<td><input type="hidden" name="uid"
						value="<%=user.getUid()%>"></td>
				</tr>
				<tr>
					<td align="right">UserName</td>
					<td><%=user.getUsername()%></td>
					<td><input type="text" name="username"
						value="<%=user.getUsername()%>"></td>
				</tr>
				<tr>
					<td align="right">UserEmail</td>
					<td><%=user.getUemail()%></td>
					<td><input type="hidden" name="uemail"
						value="<%=user.getUemail()%>"></td>
				</tr>
				<tr>
					<td align="right">UserPassWord</td>
					<td><%=user.getPassword()%></td>
					<td><input type="text" name="password"
						value="<%=user.getPassword()%>"></td>
				</tr>
				<tr>
					<td align="right">UserAutograph</td>
					<td><%=user.getUantograph()%></td>
					<td><input type="text" name="uantograph"
						value="<%=user.getUantograph()%>"></td>
				</tr>
				<tr>
					<td align="right">UserImage：</td>
					<td><img src="<%=(InputStream)user.getUimage()%>"></td>
					<td><input type="file" name="uimage" accept="image/gif,image/jpeg,image/png,image/jpg"></td>
				</tr>
				<tr>
					<td><img src="./aaa.jpg" alt="uimage" height="100px" width="100px"></td>
					<td><img src="returnImage?id=<%=user.getUid()%>"></td>
					<td>用户头像二进制文件的格式设置</td>
				</tr>
				<tr align="center">
					<td><a href="logout">注销用户</a></td>
					<td><a href="likeList">收藏的歌单</a></td>
					<td><input type="submit" value="更新用户信息"></td>
				</tr>
			</table>
		</form>
	</div>
	<% 
	} else {
		%>
	<p>
		用户没有登录，<a href="login.jsp">请登录</a>。
	</p>
	<%
	}
%>




</body>
</html>