package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NetflixManagerDAO;


@WebServlet("/NetflixManagerCreate")
public class NetflixManagerCreate extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		
		try {
			NetflixManagerDAO dao = NetflixManagerDAO.getInstance();
		int reslut = dao.insert(title, genre);
		
		
		response.sendRedirect("insert.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
