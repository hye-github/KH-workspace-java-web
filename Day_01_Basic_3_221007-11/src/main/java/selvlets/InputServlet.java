package selvlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessagesDAO;

@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String writer = request.getParameter("writer");
		String msg = request.getParameter("message");
		
		try {
		MessagesDAO dao = MessagesDAO.getInstance();
//		MessagesDAO dao = new MessagesDAO();
		int reslut = dao.insert(writer, msg);
			
		response.sendRedirect("index.html");
		// redirect 구분 : 
		// forward 구분 : 


		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}



//
//package selvlets;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/InputServlet")
//public class InputServlet extends HttpServlet {
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		String writer = request.getParameter("writer");
//		String msg = request.getParameter("msg");
//		
//		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
//		String dbID = "kh";
//		String dbPW = "kh";
//		
//		String sql = "insert into message (seq,writer,message) values(message_seq.nextval,?,?)";
//		
//		try {
//		Class.forName("oracle.jdbc.driver.OracleDriver"); // 낮은 버전의 ojbc를 사용하면 넣어줘야한다.
//
//		Connection con  = DriverManager.getConnection(dbURL, dbID, dbPW);
//		PreparedStatement pstat = con.prepareStatement(sql);
//
//		pstat.setString(1, writer);
//		pstat.setString(2, msg);
//		
//		int result = pstat.executeUpdate();
//		
//		con.commit(); // 높은 버전의 ojbc 사용할 때 commit 하면 error 난다. 조심
//		con.close();
//		
//		PrintWriter pwr = response.getWriter(); // ctrl shift o 하면 import
//		pwr.append("<html>");
//		pwr.append("<head>");
//		pwr.append("</head>");
//		pwr.append("<body>");
//		pwr.append("Request process comlete");
//		pwr.append("</body>");
//		pwr.append("</html>");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.sendRedirect("error.html");
//		}
//	
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//}








