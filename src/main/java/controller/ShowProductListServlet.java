	package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bo.ShowProductListBO;
import java.util.ArrayList;
import model.bean.HangHoa;
/**
 * Servlet implementation class ShowProductListServlet
 */
@WebServlet("/ShowProductListServlet")
public class ShowProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProductListServlet() {
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
	 protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

     request.setCharacterEncoding("UTF-8");
     response.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=UTF-8");

     String message = request.getParameter("message");
     System.out.println("message=" + message);
     HttpSession session = request.getSession();
     session.removeAttribute("searchProductText");
     
     if (session.getAttribute("accountInfor") == null) {
            response.sendRedirect("login.jsp?error=1");
     } else {
             ShowProductListBO showProductListBO = new ShowProductListBO();
             //ArrayList<HangHoa> dsHangHoa = showProductListBO.getDsHangHoa();
             String page = request.getParameter("page");
            
             int pageNumber = 1; // Mặc định là trang 1, trang đầu tiên         
             if (page != null && !"".equals(page)) {
                    pageNumber = Integer.valueOf(page);
             }
            
             ArrayList<HangHoa> dsHangHoa = showProductListBO.getDsHangHoa(pageNumber);
            
             int totalPageNumber = showProductListBO.getTotalPageNumber();

             request.setAttribute("dsHangHoa", dsHangHoa);
             request.setAttribute("currentPageNumber", pageNumber);
             request.setAttribute("totalPageNumber", totalPageNumber);
             RequestDispatcher rd = request.getRequestDispatcher("productList.jsp");

             rd.forward(request, response);

     }

}

}
