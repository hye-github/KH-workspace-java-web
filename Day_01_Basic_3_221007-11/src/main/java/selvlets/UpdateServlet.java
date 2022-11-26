package selvlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessagesDAO;
import dto.MessagesDTO;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int seq = Integer.parseInt(request.getParameter("seq"));
		String writer = request.getParameter("writer");
		String message = request.getParameter("message");
		
		try {
			MessagesDAO dao = MessagesDAO.getInstance();
//			MessagesDAO dao = new MessagesDAO();
			MessagesDTO dto = new MessagesDTO(seq,writer,message);
			int result = dao.update(dto);
			
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
