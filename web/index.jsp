<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 表单元素必须有name值 -->
<form name="uploadform" action="uploadServlet" method="post" enctype="multipart/form-data">
	用户名：<input type="text" value="zhangsan" name="loginname" /><br/>
	照片：<input type="file" name="pic" /><br/>
	<input type="submit" value="提交" />
</form>
</body>
</html>