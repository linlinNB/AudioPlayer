<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>songUp</title>
</head>
<body>
	<h2 align="center">歌曲上传,可以放在表格中布局</h2>
	
	<form action="uploadHandle" method="post" enctype="multipart/form-data">
		songname:<input type="text" name="songname"><br>
		songauthor:<input type="text" name="songauthor"><br>
		songpath:<input type="file" name="songpath"><br>
		<input type="submit" value="提交">
		
	</form>
	<hr>
	<a href="listFile"><button>下载库音乐</button></a>
	<hr>
</body>
</html>