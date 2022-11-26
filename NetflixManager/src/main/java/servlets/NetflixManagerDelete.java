package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NetflixManagerDAO;

@WebServlet("/NetflixManagerDelete")

public class NetflixManagerDelete extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("del")); // 공란 입력 안되게 자바스크립트 처리 필요
			
			try {
				NetflixManagerDAO dao = NetflixManagerDAO.getInstance();
				int result = dao.delete(id);
				response.sendRedirect("NetflixManagerRead");
				
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error.jsp");
			}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
