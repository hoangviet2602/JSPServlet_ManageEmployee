<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Trang chủ của admin</title>

</head>

<body>
	
	<%
	if (session.getAttribute("accountInfor") == null) {
		response.sendRedirect("login.jsp?error=1");
	}
	%>
	<div><%=(String) session.getAttribute("accountInfor")%></div>
	<div style="background-color: yellow; width: 100px">
		<a href="LogoutServlet">Đăng xuất</a>
	</div>
	<div style="background-color: yellow; width: 100px">
		<a href="ShowProductListServlet">Hàng hóa</a>
	</div>
</body>

</html>