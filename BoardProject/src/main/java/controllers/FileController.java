package controllers;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.file")
public class FileController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		System.out.println(uri);

		String filePath = request.getServletContext().getRealPath("/files");

		if (uri.equals("/download.file")) {
			// 다운로드 했을 때 기록을 남길 때, 권한을 줄 때 등 여기서 작성하면 체크 가능.
			
			
			String sysName = request.getParameter("sysname");
			String oriName = request.getParameter("oriname"); // DB 엑세스 해서도 값 받아올 수 있지만, 그냥 클라이언트에서 가져오기. DB에 부하 주지않기위해서.
		
			System.out.println(filePath + "/" + sysName); // 파일 위치의 절대 경로

			File target = new File(filePath + "/" + sysName); // 파일 객체 만들기
			byte[] fileContents = new byte[(int) target.length()]; // 파일 객체(target.length())가 파일 사이즈 가지고 있음.
			// CPU가 램에 있는 것만 처리 가능하니까.
			// 배열 만드는 이유 : 램에 파일 옮길 수 있도록 공간 처리
			// 배열의 사이즈는 int 밖에 못넣는다. length()는 long값을 받아온다. 강제로 int 캐스팅 한다.
			// 강제 형 변환 하면 데이터 날아가지않나요? 애초에 제한은 10메가로 해놨기때문에 int 안에서 해결 가능. 데이터 손실은 일어나지않는다.
			// byte 형 배열 만들기 - 하드디스크에 있는 파일을 배열에 담기..
			
			oriName = new String(oriName.getBytes("utf8"),"ISO-8859-1"); // oriName을 한글명으로 다운받을 수 있게.

			response.reset(); // 혹시나하는 에러 상황이 발생할까봐 써놓는 코드 / 필수 코드 아님.

			response.setHeader("Content-Disposition", "attachment;filename=\"" + oriName + "\"");
			// 지금 내가 보내는건 html 파일이 아니야. 랜더링 파일이 아니야. 내가 보내는건 첨부파일이야 라고 알려주는 코드
			// http가 파일의 내용이라고 인식하게 됨. 그래서 다운하게 됨.
			// request = 페이지 전환, a태그, location 등
			// attachment라고 하는 순간부터 페이지 전환은 일어나지 않음.

			try (ServletOutputStream sos = response.getOutputStream();
					FileInputStream fis = new FileInputStream(target); // Ram에 들어오면 input , Ram에서 나가면 output
					DataInputStream dis = new DataInputStream(fis); // 업그레이드
					) {
				dis.readFully(fileContents); // 하드 디스크에 있는 파일 내용을 복사하는 기능 코드
				sos.write(fileContents);
				sos.flush(); // 내보내기
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
