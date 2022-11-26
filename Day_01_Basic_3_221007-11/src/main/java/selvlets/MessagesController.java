package selvlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessagesDAO;
import dto.MessagesDTO;


//FrontController 패턴
//4개의 기능을 하나로 모아준다.


@WebServlet("*.message")
public class MessagesController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		// System.out.println(uri);
		// 컨트롤러의 방문 이유를 알아낼 수 있다.
		
		try { // 에러 처리를 다르게 해야하는 경우가 아니라면 한번에 묶어 처리해도 된다.
		
			if(uri.equals("/input.message")) {
				
				String writer = request.getParameter("writer");
				String msg = request.getParameter("message");
				
				MessagesDAO dao = MessagesDAO.getInstance();
				int reslut = dao.insert(writer, msg);
				response.sendRedirect("index.html");
				
			} else if(uri.equals("/output.message")) {
				
				MessagesDAO dao = MessagesDAO.getInstance();
				List<MessagesDTO> list = dao.selectAll();
				
				request.setAttribute("list", list);
				request.getRequestDispatcher("outputView.jsp").forward(request, response);
				
			} else if(uri.equals("/delete.message")) {
				
				int seq = Integer.parseInt(request.getParameter("delSeq"));
				MessagesDAO dao = MessagesDAO.getInstance();
				
				int result = dao.delete(seq);
				response.sendRedirect("output.message");
				
			} else if(uri.equals("/update.message")) {
				
				int seq = Integer.parseInt(request.getParameter("seq"));
				String writer = request.getParameter("writer");
				String message = request.getParameter("message");
				
				MessagesDAO dao = MessagesDAO.getInstance();
				MessagesDTO dto = new MessagesDTO(seq,writer,message);
				int result = dao.update(dto);
				response.sendRedirect("output.message");
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
