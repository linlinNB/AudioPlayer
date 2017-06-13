<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Index</title>
</head>
<body>
	<div align="center">
		<h2>This is test file</h2>
	</div>
	<hr>
	<span> 
		<a href="findone.jsp"><button>查询</button></a>
	</span>
	<hr>
	<form action="saveSong" method="post" enctype="multipart/from-data"> 
		Simage:<input type="file" name="simage" id="simage" accept="image/gif,image/jpeg,image/png,image/jpg" ><br>
		Sname:<input type="text" name="sname"><br>
		Songname:<input type="text" name="songname"><br>
		Songpath:<input type="file" name="songpath"><br>
		<input type="submit">
	</form>
	<hr>
	<a href="listFile"><button>下载库音乐</button></a>
	<hr>
		<h3>没有解决的问题</h3>
		<ol>
			<li>《绝对路径》的文件上传</li>
			<li>《保存文件路径上传到服务器文件夹》</li>
			<li>《二进制头像写入到数据库中》</li>
		</ol>
	<!-- 
	这里的代码获取文件的本地绝对路径，暂时没用了，使用文件上传下载的插件。
	<script type="text/javascript">
	$("input[type='file']").change(function(){   
		 var file = this.files[0];
		   if (window.FileReader) {    
		            var reader = new FileReader();    
		            reader.readAsDataURL(file);    
		            //监听文件读取结束后事件    
		          reader.onloadend = function (e) {
		            $(".img").attr("src",e.target.result);    //e.target.result就是最后的路径地址
		            };    
		       }
		});
	</script>
	 -->
</body>
</html>