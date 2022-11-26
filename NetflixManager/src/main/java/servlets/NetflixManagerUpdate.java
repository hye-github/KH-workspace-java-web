package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NetflixManagerDAO;
import dto.NetflixManagerDTO;


@WebServlet("/NetflixManagerUpdate")
public class NetflixManagerUpdate extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		
		try {
			NetflixManagerDAO dao = NetflixManagerDAO.getInstance();
			NetflixManagerDTO dto = new NetflixManagerDTO(id,title,genre);
			int result = dao.update(dto);
			
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
