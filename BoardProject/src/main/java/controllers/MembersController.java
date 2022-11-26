package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MembersDAO;
import dto.MembersDTO;

@WebServlet("*.mem")
public class MembersController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		
		String uri = request.getRequestURI();
		System.out.println("요청 URI : " + uri);
					
				
		try {
			if (uri.equals("/duplCheck.mem")) { // /member/duplCheck.mem 상대경로
			String id = request.getParameter("id");
			
			MembersDAO dao = MembersDAO.getInstance();
			boolean result = dao.isIdCheck(id);
			response.getWriter().append(String.valueOf(result));
			System.out.println(result);
			
			// ajax 방식을 사용하게 되면서 forward 하지않는다.
//			request.setAttribute("result", result);
//			request.setAttribute("id", id);
//			request.getRequestDispatcher("/member/duplCheckView.jsp").forward(request, response);
			
			} else if (uri.equals("/signin.mem")) {

				String signinId = request.getParameter("signinId");
				String signinPassword = request.getParameter("signinPassword");
				
				MembersDAO dao = MembersDAO.getInstance();
//				boolean result = dao.isIdPwCheck(signinId,signinPassword);

				boolean result = dao.isIdPwCheck(signinId,dao.getSHA512(signinPassword));
				
				if(result) {
					System.out.println("로그인 성공");
					request.getSession().setAttribute("loginID",signinId);
//					request.getSession().setAttribute("loginID",null);
				}
					response.sendRedirect("/");
					System.out.println("로그인 실패");

			} else if (uri.equals("/signup.mem")) {
				
				System.out.println("회원가입 진행");
				MembersDAO dao = MembersDAO.getInstance();
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String zipcode = request.getParameter("zipcode");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");

				try {
					dao.insert(id, dao.getSHA512(pw), name, phone, email, zipcode, address1, address2);
					response.sendRedirect("/");
					System.out.println("회원가입 완료");
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("/error.jsp");
				}
				
			} else if (uri.equals("/logout.mem")) {
				// 값만 빼기 or 서랍 아예 빼기
				
				request.getSession().invalidate(); // 로그아웃 기능은 보편적으로 이 한줄이면 된다. invalidate: 무효화 시키다. 내 키 값에 달려있는 창고 데이터를 날리는 것이다.
				response.sendRedirect("/");
				
			} 
			
			else if (uri.equals("/memberout.mem")) {

				String loginID = (String) request.getSession().getAttribute("loginID");
//				String loginID = request.getSession().getAttribute("loginID").toString();
				request.getSession().invalidate();
				
				MembersDAO dao = MembersDAO.getInstance();
				dao.delete(loginID);
				response.sendRedirect("/");
				
			} else if (uri.equals("/mypage.mem")) {
				String loginID = (String) request.getSession().getAttribute("loginID");
				
				MembersDAO dao = MembersDAO.getInstance();
				MembersDTO list = dao.selectById(loginID);

				request.setAttribute("list", list);
				request.getRequestDispatcher("/member/mypageView.jsp").forward(request, response);
			
			} else if (uri.equals("/modifyView.mem")) {	
				// 데이터 가져오기
				String loginID = (String) request.getSession().getAttribute("loginID");
				MembersDAO dao = MembersDAO.getInstance();
				
				MembersDTO list = dao.selectById(loginID);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/member/mypageModify.jsp").forward(request, response);
				
			} else if (uri.equals("/modify.mem")) {
				String loginID = (String) request.getSession().getAttribute("loginID");
				MembersDAO dao = MembersDAO.getInstance();

				String pw = request.getParameter("pw");
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String zipcode = request.getParameter("zipcode");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");
				
				dao.modifyById(dao.getSHA512(pw), name, phone, email, zipcode, address1, address2, loginID);
				
				MembersDTO list = dao.selectById(loginID);
				request.setAttribute("list", list);
				
				request.getRequestDispatcher("/member/mypageView.jsp").forward(request, response);
				 
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//			[KH 김일중] [오후 2:24] duplcheck 통과했으면
//			[KH 김일중] [오후 2:24] 어디 boolean 변수같은거 하나 만들어놔서
//			[hi] [오후 2:24] 아 조건을 그 안에 넣는건가?
//			[hi] [오후 2:25] 중복 체크를 필수적으로 하게끔
//			[KH 김일중] [오후 2:25] 통과했을때만 true 넣어놓고
//			[hi] [오후 2:25] 해야겠네요
//			[KH 김일중] [오후 2:25] ㅇㅇ
//			[hi] [오후 2:25] 안하면 다른거 못하게
//			[KH 김일중] [오후 2:25] submit에도 그걸
//			[hi] [오후 2:25] 하 복잡하네...
//			[KH 김일중] [오후 2:25] boolean이 true일때만 통과시키면대긴함
//			[hi] [오후 2:26] 컨트롤러는 조건 걸면 될텐데... ul 띄우고 하는게.... 에반데.....
//			[hi] [오후 2:26] 아 아니면 회원가입 실패하면 왜 실패했는지를 
//			[hi] [오후 2:27] 그 창에서 보여주면 되겠다. 일단 중복 확인이랑 똑같이 새창 넘어가게하는거니까
			
			
//			[KH 김일중] [오후 2:42] 유효성검사 은근 생각할거 많네요
//			[KH 김일중] [오후 2:43] 중복확인 패스한것만 가입시켜야하는데 한번 패스시키고 멋대로 아이디 바꿔서 가입하려는것도 막아야하고..
//			[KH 김일중] [오후 3:50] 1. 중복확인후 사용누르면 input 입력 막아버리기
//			[KH 김일중] [오후 3:51] 2. boolean 하나 만들어서 input keyup마다 false시키고 중복확인 했을때만 true시키고 유효성검사에 이거 true일때만 통과
//			[KH 김일중] [오후 3:52] 2번이 계속 수정가능해서 사용자편의에는 좋을듯여

		
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
