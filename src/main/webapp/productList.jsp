<%@ page import="model.bean.HangHoa"%>
<%@ page import="common.StringCommon"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Danh sách hàng hóa</title>

</head>

<body>
	<%
	if (session.getAttribute("accountInfor") == null) {
		response.sendRedirect("login.jsp?error=1");
	}
	%>

	<%
	String error = request.getParameter("message");
	%>
	<%=("1".equals(error)) ? "Đã xóa hàng hóa thành công!" : ""%>
	<%=("2".equals(error)) ? "Đã thêm mới thành công!" : ""%>
	<%=("3".equals(error)) ? "Đã chỉnh sửa hàng hóa thành công!" : ""%>
	<h1>TÊN CỬA HÀNG</h1>
	<h2>QUẢN LÝ DANH MỤC HÀNG HÓA</h2>

	<%
	ArrayList<HangHoa> dsHangHoa = (ArrayList<HangHoa>) request.getAttribute("dsHangHoa");
	%>
	<input type="button" onclick="location.href='ShowCreateProductServlet'"
		value="Tạo mới" />

	<% String searchText = session.getAttribute("searchProductText") != null ? (String)session.getAttribute("searchProductText") : ""; 
	String functionPrefix = "".equals(searchText) ? "ShowProductList" : "SearchProduct";  %>




	<form action="SearchProductServlet" method="post">

		<input type="text" name="searchText" value="<%=searchText%>" /> <input
			type="submit" value="Search" />

	</form>
<% 	  int currentPageNumer = (Integer) request.getAttribute("currentPageNumber");  // Do server trả về
	  int totalPageNumber = (Integer) request.getAttribute("totalPageNumber"); // Do server trả về
      int[] pageNumberList = new int[10]; // Do client tự tính toán
      int pageQuantity = 0; // Do client tự tính toán
      // Tình huống số 1:
      if (totalPageNumber <= 10) {
              for (int j = 0; j < totalPageNumber; j++) {
                      pageNumberList[j] = j+1;
                      pageQuantity++;
              }
      }
      // Tình huống số 2 nằm trong tình huống số 4 rồi
      // Tình huống số 3:
      if (totalPageNumber > 10 && currentPageNumer <= 4) {
              for (int j = 0; j < 10; j++) {
                      pageNumberList[j] = j+1;
                      pageQuantity++;
              }
      }
      // Tình huống số 4:
      if (totalPageNumber > 10 && currentPageNumer >= (totalPageNumber - 5)) {
              for (int j = 10; j >= 1; j--) {
                      pageNumberList[j-1] = totalPageNumber - (10 - j);
                      pageQuantity++;
              }
      }
      // Tình huống số 5:
      if (totalPageNumber > 10 && currentPageNumer >= 5 && currentPageNumer <=(totalPageNumber - 5)) {
             for (int j = 0; j < 10; j++) {
                    pageNumberList[j] = currentPageNumer - 3 + j;
                    pageQuantity++;
              }
      }                
   %>
	<table border="1">
		<tr>
			<th>STT</th>

			<th>Mã hàng</th>

			<th>Tên hàng</th>

			<th>Đơn giá tham khảo</th>

			<th>Đơn vị tính</th>

			<th>Ghi chú</th>

			<th>Hành động</th>

		</tr>
		<%
		 int i = (currentPageNumer - 1)*20 + 1; // 20 là số dòng trên 1 trang 
		%>
		<%
		if (dsHangHoa != null) {
		%>
		<%
		for (HangHoa hh : dsHangHoa) {
		%>
		<tr>

			<td><%=i++%></td>

			<td><%=hh.getMaHH()%></td>

			<td><%=hh.getTenHH()%></td>

			<td><%=StringCommon.convertDoubleToStringWithComma(hh.getDonGiaThamKhao())%></td>

			<td><%=hh.getDonViTinh()%></td>

			<td><%=hh.getGhiChu()%></td>

			<td><input type="button"
				onclick="location.href='ShowEditProductServlet?proId=<%=hh.getMaHH()%>';"
				value="Chỉnh sửa" /> <input type="button"
				onclick="deleteProduct('<%=hh.getMaHH()%>')" value="Xóa" /> <a
				onclick="return confirm('Bạn có muốn xóa không?');"
				href="DeleteProductServlet?proId=<%=hh.getMaHH()%> ">Xóa</a></td>
		</tr>
		<%
		}
		%>
		<%
		}
		%>

		<div style="background-color: yellow; width: 100px">
			<a href="LogoutServlet">Đăng xuất</a>
		</div>
	</table>


	<script>
		function deleteProduct(proID) {
			if (confirm('Bạn có muốn xóa không?')) {
				location.href = "DeleteProductServlet?proId=" + proID;
			}
		}
	</script>
</body>

     <% if (currentPageNumer > 1) { %>

      <a href='<%=functionPrefix%>Servlet?page=1'>First</a>

      <a href='<%=functionPrefix%>Servlet?page=<%=currentPageNumer-1%>'>Previous</a>

   <%

      }
      for (int k = 0; k < pageQuantity; k++) {
         if (pageNumberList[k] == currentPageNumer) { %>
            <a href='<%=functionPrefix%>Servlet?page=<%=pageNumberList[k]%>'><b><%=pageNumberList[k]%></b></a>
   <%    } else { %>
            <a href='<%=functionPrefix%>Servlet?page=<%=pageNumberList[k]%>'><%=pageNumberList[k]%></a>
   <%    }
      }
   %>
   <% if (currentPageNumer < totalPageNumber) { %>
      <a href='<%=functionPrefix%>Servlet?page=<%=currentPageNumer+1%>'>Next</a>
      <a href='<%=functionPrefix%>Servlet?page=<%=totalPageNumber%>'>Last</a>
   <% } %>
</html>





