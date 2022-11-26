package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dto.BoardDTO;

@WebServlet("*.ajax")
public class AjaxController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();

		if(uri.equals("/exam01.ajax")){
			System.out.println("Exam01 : 비동기 통신 도착");
		} else if(uri.equals("/exam02.ajax")){
			String p1 = request.getParameter("key1");
			String p2 = request.getParameter("key2");
			System.out.println(p1 + " : " + p2); // json모양으로 생긴 String 을 받는 것 // DATA 타입은 String 타입으로 통신 된다.

		} else if(uri.equals("/exam03.ajax")){
			System.out.println("exam03 동작");
			response.getWriter().append("Ajax Works!");

		} else if(uri.equals("/exam04.ajax")){
			System.out.println("exam04 동작");
			// response.getWriter().append("[\"Apple\",\"Orange\",\"Mango\"]"); 
			// 분석될 수 있는 형태로 보내야한다. 오타나면 절대 안됨.

			// 자바에서 자바스크립트로 보낼 때 배열, 변수명으로 사용되지않도록 \" 써서 구분해서 보내줌.
			// 받는 자바스크립트에서는 문자열로 인식해서 첫번째 글자만 나간다. 0번째 글자 : [ / charAt(0)
			// 통신에서 다루어지는 내용은 모두 문자열로 인식한다. 통신방식은 무조건 문자열만 가능
			// 객체를 문자열로 - 직렬화
			// 문자열을 객체 - 역직렬화
			// 크로스랭귀지

			Gson g = new Gson(); // SPRING 에서는 다른걸 사용. 그래도 Gson 좋음.
			String[] arr = new String[] {"Apple","Orange","Mango"};
			String jsonString = g.toJson(arr); 
			response.getWriter().append(jsonString);

		} else if(uri.equals("/exam05.ajax")){
			System.out.println("exam05 동작");

			Gson g = new Gson();
	
			BoardDTO dto = new BoardDTO(10,"Tester","Title","Contents",null,0); //실전이라면 dao에서 뽑아낸 dto다.
			// response.getWriter().append(dto.toString()); // 주소값임
	
			// dto는 jason에 가깝다. 배열이 아니다.
			// response.getWriter().append("{\"+seq\":\""+dto.getSeq()+"\", ~ }");
			// list면 이것보다 더 복잡하다. 별로임
	
			// 직렬화 라이브러리가 존재 java 에서 json 을 직렬화해주는 Gson.
			// 상황에 따라서 역직렬화도 제공
			
			
			String jsonString = g.toJson(dto);
			response.getWriter().append(jsonString);
			//  response.getWriter().append("{\"+seq\":\""+dto.getSeq()+"\", ~ }"); 를 대신해줌
		
		} else if(uri.equals("/exam06.ajax")){
			System.out.println("exam06 동작");

			List<BoardDTO> list = new ArrayList<>();
			list.add(new BoardDTO(10,"Tester1","Title1","Contents1",null,0));
			list.add(new BoardDTO(11,"Tester2","Title2","Contents2",null,0));
			list.add(new BoardDTO(12,"Tester3","Title3","Contents3",null,0));
			
			Gson g = new Gson();
			String jsonString = g.toJson(list);
			response.getWriter().append(jsonString);
			
		} else if(uri.equals("/exam07.ajax")){
			
			JsonObject total = new JsonObject(); // dto랑 obj 넣어서 직렬화해서 보내기
			
			
			JsonObject obj = new JsonObject(); 
			// toJson 으로 써도 되는데 왜 JsonObject? type이 없는 데이터(dto가 아닌 데이터면 어떻게 할껀데.
			obj.addProperty("emp_id","101");
			obj.addProperty("emp_name","Jack");
			obj.addProperty("dept_title","해외영업부");
			obj.addProperty("job_name","대리");
			// 한글이 깨지는 이유 : 인코딩 깨져서? 
			// 기존에 jsp 에서는 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
			// 얘가 인코딩을 대신 처리해줬었는데 지금은 처리 구문이 하나도 없어서 한글이 깨진다.
			
			BoardDTO dto = new BoardDTO(10,"Tester","Title","Contents",null,0); //실전이라면 dao에서 뽑아낸 dto다.

			total.addProperty("obj", obj.toString());
			total.addProperty("dto", new Gson().toJson(dto));
			
			response.setContentType("text/html;charset=UTF-8"); //response에 ContentType을 설정해주면 한글 안깨지고 넘어오게 하기 가능
			response.getWriter().append(total.toString());
			
			
			
//			JsonObject obj = new JsonObject(); 
//			// toJson 으로 써도 되는데 왜 JsonObject? type이 없는 데이터(dto가 아닌 데이터면 어떻게 할껀데.
//			obj.addProperty("emp_id","101");
//			obj.addProperty("emp_nmae","Jack");
//			obj.addProperty("dept_title","해외영업부");
//			obj.addProperty("job_name","대리");
//			// 한글이 깨지는 이유 : 인코딩 깨져서? 
//			// 기존에 jsp 에서는 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
//			// 얘가 인코딩을 대신 처리해줬었는데 지금은 처리 구문이 하나도 없어서 한글이 깨진다.
//			
//			
//			response.setContentType("text/html;charset=UTF-8"); //response에 ContentType을 설정해주면 한글 안깨지고 넘어오게 하기 가능
//			response.getWriter().append(obj.toString());
			
			
			
			
			 request.getRequestDispatcher("/index.jsp").forward(request, response);
			// 포워드로 보내면 주소창 안바뀜
			// jsp도 서블릿(request, response를 가지고 있음)이라서 
			// html 내용들이 response 한테 담긴다
			
			// 리다이렉트 / 포워드 실행 아예 안됨. 코드 쓸 데가 없어서 작성할 이유가 없음.
			// ajax는 String값으로 받아오기때문에 소스 코드 받아와서 랜더링 하는게 아님.
			// 
		
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
