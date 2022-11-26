package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentsDAO;

@WebServlet("*.comment")
public class CommentController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String uri = request.getRequestURI();
		System.out.println("요청 URI : " + uri);
		try {
			if (uri.equals("/checkwrite.comment")) {
							
				String loginID = (String) request.getSession().getAttribute("loginID");
				String contents = request.getParameter("commentspost");
				int parent_seq = Integer.parseInt(request.getParameter("parent_seq"));
				
				CommentsDAO dao = CommentsDAO.getInstance();
				dao.insertComment(loginID, contents, parent_seq);
				
				response.sendRedirect("/detail.board?seq="+parent_seq);
				
			} else if (uri.equals("/delete.comment")) {
			
				int parent_seq = Integer.parseInt(request.getParameter("parent_seq"));
				int commseq = Integer.parseInt(request.getParameter("seq"));
				
				CommentsDAO dao = CommentsDAO.getInstance();
				dao.deleteComment(commseq);
				
				response.sendRedirect("/detail.board?seq="+parent_seq);
				
			} else if (uri.equals("/edit.comment")) {

				int parent_seq = Integer.parseInt(request.getParameter("parent_seq"));
				int commseq = Integer.parseInt(request.getParameter("seq"));
				String contents = request.getParameter("commentsviewpost");
				
				CommentsDAO dao = CommentsDAO.getInstance();
				dao.modifyCommentBySeq(contents, commseq);
				
				response.sendRedirect("/detail.board?seq="+parent_seq);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
