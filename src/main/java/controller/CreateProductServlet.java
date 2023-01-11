package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bo.CreateProductBO;

/**
 * Servlet implementation class CreateProductServlet
 */
@WebServlet("/CreateProductServlet")
public class CreateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");

         response.setCharacterEncoding("UTF-8");

         response.setContentType("text/html; charset=UTF-8");


         HttpSession session = request.getSession();

         if (session.getAttribute("accountInfor") == null) {
                 response.sendRedirect("login.jsp?error=1");
         } else {
        	 
                 String tenHH = request.getParameter("tenHH");
                 String dvt = request.getParameter("dvt");
                 String donGiaTK = request.getParameter("donGiaTK");
                 String ghiChu = request.getParameter("ghiChu");

                 CreateProductBO createProductBO = new CreateProductBO();
                 String returnedMessage = createProductBO.insertProduct(tenHH, dvt,donGiaTK, ghiChu);
                 System.out.println(returnedMessage);
                 RequestDispatcher rd = null;

                 if ("No error".equals(returnedMessage)) {

                         // Thêm mới thành công
                         rd = request.getRequestDispatcher("ShowProductListServlet?message=2");

                 } else if ("Duplicate ID Error".equals(returnedMessage)) {
                        // Lỗi trùng mã hàng hóa
                         rd = request.getRequestDispatcher("ShowCreateProductServlet?message=1");
                 } else if ("Required Fields error".equals(returnedMessage)) {
                        // Lỗi thiếu thông tin các field bắt buộc nhập

                         rd = request.getRequestDispatcher("ShowCreateProductServlet?message=3");
                 } else if("Invalid DGTK error".equals(returnedMessage)){
                	 rd = request.getRequestDispatcher("ShowCreateProductServlet?message=4");
                 }else {
                         // Lỗi không xác định
                         rd = request.getRequestDispatcher("ShowCreateProductServlet?message=2");
                         //System.out.println(returnedMessage);

                 }
                 rd.forward(request, response);
         }
	}
	

}