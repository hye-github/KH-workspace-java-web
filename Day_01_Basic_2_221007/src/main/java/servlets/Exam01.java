package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Exam01")
public class Exam01 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("접속자 IP : " + request.getRemoteAddr());
//		response.getWriter().append("Servlet Activate! ");
		
		String msg = request.getParameter("msg");
		System.out.println(request.getRemoteAddr() + " 로 부터 의 메세지 : " + msg);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
