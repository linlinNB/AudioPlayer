<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>listfile</title>
</head>
<body>
<h2>这是下载列表的显示页面</h2>
 <!-- 遍历Map集合 -->
    <c:forEach var="me" items="${fileNameMap}">
         <c:url value="downLoad" var="downurl">
             <c:param name="filename" value="${me.key}"></c:param>
         </c:url>
         ${me.value}<a href="${downurl}">下载</a>
        <br/>
    </c:forEach>
</body>
</html>