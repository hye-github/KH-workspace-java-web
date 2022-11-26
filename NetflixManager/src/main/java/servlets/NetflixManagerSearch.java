package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NetflixManagerDAO;
import dto.NetflixManagerDTO;

@WebServlet("/NetflixManagerSearch")
public class NetflixManagerSearch extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String titlesearch = request.getParameter("search");
		
		try {
			NetflixManagerDAO dao = NetflixManagerDAO.getInstance();

			List<NetflixManagerDTO> list = dao.search(titlesearch);

			request.setAttribute("list", list);
			request.getRequestDispatcher("netflixsearch.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
