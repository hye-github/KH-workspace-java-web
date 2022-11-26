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

@WebServlet("*.NetflixManager")
public class NetflixManagerController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		try {
		
			if(uri.equals("/create.NetflixManager")) {
				String title = request.getParameter("title");
				String genre = request.getParameter("genre");
				NetflixManagerDAO dao = NetflixManagerDAO.getInstance();
				int reslut = dao.insert(title, genre);
				response.sendRedirect("insert.jsp");
				
			} else if(uri.equals("/delete.NetflixManager")) {
				int id = Integer.parseInt(request.getParameter("del")); // 공란 입력 안되게 자바스크립트 처리 필요
				NetflixManagerDAO dao = NetflixManagerDAO.getInstance();
				int result = dao.delete(id);
				response.sendRedirect("read.NetflixManager");
					
			} else if(uri.equals("/read.NetflixManager")) {
				NetflixManagerDAO dao = NetflixManagerDAO.getInstance();
				List<NetflixManagerDTO> list = dao.selectAll();
				request.setAttribute("list", list);
				request.getRequestDispatcher("netflixread.jsp").forward(request, response);
				
			} else if(uri.equals("/search.NetflixManager")) {
				String titlesearch = request.getParameter("search");
				NetflixManagerDAO dao = NetflixManagerDAO.getInstance();
				List<NetflixManagerDTO> list = dao.search(titlesearch);
				request.setAttribute("list", list);
				request.getRequestDispatcher("netflixsearch.jsp").forward(request, response);
					
			} else if(uri.equals("/update.NetflixManager")) {
				int id = Integer.parseInt(request.getParameter("id"));
				String title = request.getParameter("title");
				String genre = request.getParameter("genre");
				NetflixManagerDAO dao = NetflixManagerDAO.getInstance();
				NetflixManagerDTO dto = new NetflixManagerDTO(id,title,genre);
				int result = dao.update(dto);
				response.sendRedirect("read.NetflixManager");
					
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
