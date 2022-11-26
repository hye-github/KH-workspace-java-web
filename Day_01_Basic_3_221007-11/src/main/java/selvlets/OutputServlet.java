package selvlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessagesDAO;
import dto.MessagesDTO;

@WebServlet("/OutputServlet")
public class OutputServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			MessagesDAO dao = MessagesDAO.getInstance();
//			MessagesDAO dao = new MessagesDAO();
			
			List<MessagesDTO> list = dao.selectAll(); // 저장하는 작업 필요 // ArrayList 라고 안하고 List 라고하는 표현을 많이 쓴다.
				
			
			// <-- 여기서 부터는 클라이언트가 보게 될 화면을 구성하는 view 작업
//			response.sendRedirect("outputView.jsp");
			
			request.setAttribute("val1",10);
			request.setAttribute("val2","Hello");			
			request.setAttribute("val3",3.14);
			String[] arr = new String[] {"Apple","Orange"};
			request.setAttribute("arry",arr);
			
			MessagesDTO dto = new MessagesDTO(5000,"TEST","Hello");
			request.setAttribute("dto",dto);
			
			request.setAttribute("list", list); // ("list", list) : js로 치면 키 벨류임. 자바에는 맵이라고 불린다.
			request.getRequestDispatcher("outputView.jsp").forward(request, response);
			// request.getRequestDispatcher(getServletInfo());
			// request : 텅 비어있는 가방과도 같은 것(실제로는 클라이언트에 정보가 담겨있다.)
			// 가져갈 데이터가 있으면 forward고, 없으면 redirect 임
			
			
			
//				PrintWriter writer = response.getWriter(); // ctrl shift o 하면 import
//				writer.append("<html>");
//				writer.append("<head>");
//				writer.append("<title>Message List</title>");
//				writer.append("</head>");
//				writer.append("<body>");
//				writer.append("<table border=1 align=center>");
//				writer.append("<tr>");
//				writer.append("<th colspan=3>Message List");
//				writer.append("</tr>");
//				writer.append("<tr>");
//				writer.append("<th>seq");
//				writer.append("<th>writer");
//				writer.append("<th>message");
//				writer.append("</tr>");
//				
//				for (MessagesDTO dto : list) {
//					writer.append("<tr>");
//					writer.append("<td>" + dto.getSeq());	
//					writer.append("<td>" + dto.getWriter());		
//					writer.append("<td>" + dto.getMessage());
//					writer.append("</tr>");
//				}
//				
//				writer.append("<form action=DeleteServlet>");
//				writer.append("<tr>");
//				writer.append("<td colspan=3><input type=text name=delSeq placeholder='Input seq to delete'><button>Delete</button>");
//				writer.append("</tr>");
//				writer.append("</form>");
//				
//				writer.append("<form action=UpdateServlet>");
//				writer.append("<tr>");
//				writer.append("<td colspan=3>");
//				writer.append("<input type=text name=seq placeholder='Input seq to modify'><br>");	
//				writer.append("<input type=text name=writer placeholder='Input writer to modify'><br>");	
//				writer.append("<input type=text name=message placeholder='Input message to modify'>");
//				writer.append("<button>Modify</button>");
//				writer.append("</tr>");
//				writer.append("</form>");
//				
//				writer.append("<tr>");
//				writer.append("<td colspan=3 align=center><button type=button id=back>Back</button>");
//				writer.append("</tr>");
//				
//				writer.append("</table>");
//				writer.append("<script>");
//				writer.append("document.getElementById('back').onclick = function(){location.href='index.html'}");
//				writer.append("</script>");
//				
//				writer.append("</body>");
//				writer.append("</html>");
				

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
