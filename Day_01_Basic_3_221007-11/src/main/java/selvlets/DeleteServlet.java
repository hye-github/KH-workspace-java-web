package selvlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessagesDAO;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int seq = Integer.parseInt(request.getParameter("delSeq")); // 공란 입력 안되게 자바스크립트 처리 필요
		
		try {
			MessagesDAO dao = MessagesDAO.getInstance();
//			MessagesDAO dao = new MessagesDAO();
			int result = dao.delete(seq);
			response.sendRedirect("OutputServlet");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
