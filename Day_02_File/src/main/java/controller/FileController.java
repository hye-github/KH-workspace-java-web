package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.FilesDAO;
import dto.FilesDTO;

@WebServlet("*.file")
public class FileController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		아파치커먼 라이브러리는 스프링이랑 같이 사용해야 편함.
//		cos. 라이브러리로 사용해서 file 가져올 예정.

		String uri = request.getRequestURI();
		
		try {
			
			if (uri.equals("/upload.file")) {

				int maxSize = 1024 * 1024 * 10; // 업로드 파일 최대 사이즈 (10메가바이트) 서버에서 제한 둬도 되지만 필요한 사이즈라서 여기에 생성
				// 파일 업로드 마음대로 가능은 한데, 다운로드가 불가능함. 보안 설정때문에

				// String savePath = "c:/myFile;"; // 이렇게 안됨

				String savePath = request.getServletContext().getRealPath("/files");
				// getRealPath 안쪽에 폴더를 만들고 거기에 업로드해야함.
				System.out.println(savePath);
				// savePath 이 경로를 객체로 만들어서 files 폴더를 만들어달라고 해야함

				File fileSavePath = new File(savePath);

				if (!fileSavePath.exists()) {
					fileSavePath.mkdir(); // make directory=folder
				}

				MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8",
						new DefaultFileRenamePolicy()); // 파일 이름 중복일 때 이름 재설정해주는 정책
				// multi.getParameter() 이거 가능

				String oriName = multi.getOriginalFileName("file");
				String sysName = multi.getFilesystemName("file");

				FilesDTO dto = new FilesDTO(0, oriName, sysName, 0);

				int result = FilesDAO.getInstance().insert(dto);
				response.sendRedirect("index.jsp");

			} else if(uri.equals("/list.file")) {
				
				List<FilesDTO> list = FilesDAO.getInstance().selectALL();
				request.setAttribute("filelist", list);
				request.getRequestDispatcher("/board/boarddetail.jsp").forward(request, response);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
