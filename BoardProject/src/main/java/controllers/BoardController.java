package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDAO;
import dao.CommentsDAO;
import dao.FilesDAO;
import dto.BoardDTO;
import dto.CommentsDTO;
import dto.FilesDTO;

@WebServlet("*.board")
public class BoardController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String uri = request.getRequestURI();
		System.out.println("요청 URI : " + uri);
		try {
			if (uri.equals("/list.board")) {
				System.out.println("자유게시판 접속");

				int cpage = Integer.parseInt(request.getParameter("cpage"));
				
//				BoardDAO dao = BoardDAO.getInstance();
//				List<BoardDTO> list = dao.selectAll(); // 아래에서 한줄로 표현.
			
//				List<BoardDTO> list = BoardDAO.getInstance().selectAll(); // 게시글 전체가 보이면 안됨.
				
				List<BoardDTO> list = BoardDAO.getInstance().selectByRange(cpage*10-9,cpage*10);
				
				
				
				for(BoardDTO dto : list) {
					
					dto.setCommentNum(CommentsDAO.getInstance().getCommentsCount(dto.getSeq()));
//					속성 추가 안해도 list에 담겨서 나가는거라 ㄱㅊ
					
//					list.get(0).setCommentNum(CommentsDAO.getInstance().getCommentsCount(list.get(0).getSeq()));
//					list.get(1).setCommentNum(CommentsDAO.getInstance().getCommentsCount(list.get(1).getSeq()));
//					list.get(2).setCommentNum(CommentsDAO.getInstance().getCommentsCount(list.get(1).getSeq()));
//					list.get(3).setCommentNum(CommentsDAO.getInstance().getCommentsCount(list.get(2).getSeq()));
//					...
//					list.get(9).setCommentNum(CommentsDAO.getInstance().getCommentsCount(list.get(9).getSeq()));
				}
				
				
				
				
//				BoardDTO , 댓글개수
//
//
//
//				{
//				   dto:BoardDTO,
//				   count:댓글개수
//				}
//
//
//				Map<String, Object>
//				map.put("dto",BoardDTO);
//				map.put("count",댓글개수);
				
				
				
				
				
//				for(BoardDTO dto : list) {
//					int commentsCount = CommentsDAO.getInstance().getCommentsCount(dto.getSeq());
//					// dto 에 컬럼 추가. 생성자 가지지않는 get set 
//					// commentsCount 얘를 필드에 담아야함
//				}
//
//				request.setAttribute("commentsCount", commentsCount); // 이렇게 하면 어떤 댓글 갯수라고 하는건지 모름.
				
				
				request.setAttribute("list", list);
				// 내가 1페이지에 있으면 1~10번글을 가지고 와야하고, 내가 2페이지에 있으면 11~20번글을 가지고 와야함.
				// recordCountPerPages는 dao 안에 있고, 지역 변수에 있어서 안된다.

				String navi = BoardDAO.getInstance().getPageNavi(cpage);
				request.setAttribute("navi", navi);
				
				
				
//				int commentsCount = CommentsDAO.getInstance().getCommentsCount();
//				request.setAttribute("commentsCount", commentsCount);
				
				
				request.getRequestDispatcher("/board/boardlist.jsp").forward(request, response);

			} else if (uri.equals("/write.board")) {
				System.out.println("자유게시판 글쓰기 접속");
				response.sendRedirect("/board/boardwrite.jsp");
				
			} else if (uri.equals("/checkwrite.board")) {
				 
				// 리스트(cpage)/디테일 클릭(여긴 없어) > 디테일페이지/수정 클릭> 수정페이지/확인 클릭 > 디테일페이지/리스트 클릭 (여기에서 1페이지로 넘어옴)
				// 마지막에 cpage 값 어디서 가져오지??
				// 세션에 cpage 값을 담아서 다녀라.
				
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
				// 하드디스크에 저장되는 작업은 언제든 문제가 생길 수 있다. 저장 속도의 문제! 
				// 업로드도 문제지만, 삭제할 때가 제일 문제가 됨. 사용 중인 파일 에러때문에 삭제 안되는 현상 발생
				
				MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8",
						new DefaultFileRenamePolicy()); // 파일 이름 중복일 때 이름 재설정해주는 정책
				// multi.getParameter() 이거가 나중에 form으로 액션 보낼 때 값 가져오는 기능 (기존에서 이걸로 업그레이드 된 것)

				
				String loginID = (String) request.getSession().getAttribute("loginID");
				
				String title = multi.getParameter("titlepost");
				String contents = multi.getParameter("contentspost");
			
				int nextVal = BoardDAO.getInstance().getNextVal();
				BoardDAO.getInstance().insert(new BoardDTO(nextVal, loginID, title, contents, null, 0));
			
				
				Enumeration<String> e = multi.getFileNames();
				// 어레이 리스트에 저장한다는 느낌임
				
				while(e.hasMoreElements()) { // next() 라는 느낌
					String name = e.nextElement();
					System.out.println(name);
					
					String oriName = multi.getOriginalFileName(name);
					String sysName = multi.getFilesystemName(name);
					
//					String oriName = multi.getOriginalFileName("file"); 다중 업로드로 인해 name으로 변경
//					String sysName = multi.getFilesystemName("file");
					
					if(name != null) { // 널값을 만나면 컨티뉴 되게 하기 // 프론트에서 onsubmit 만나면 서브밋 안되게 값 삭제하기 
						if(oriName == null) { continue; };
						FilesDAO.getInstance().insert(new FilesDTO(0, oriName, sysName, nextVal));
						// parent_seq는 글이 등록되고나면 번호가 생성된다.
						// 가장 최신 번호를 parent_seq로 등록하는 코드를 짜게 되면, 
						// 동시 접속자가 그 parent_seq를 사용하게 될 수도 있어서 파일 첨부를 하지않은 사람이 파일 첨부 하게 된 것처럼 보이게 된다.
					}
						
				}
				
				response.sendRedirect("/list.board?cpage=1");
				// request.getRequestDispatcher("/board/boardlist.jsp").forward(request, response); 
				// 글 쓰고 나서 리다이렉트로 가야함. 새로고침하면 글 똑같은거 생성됨.
			
			} else if (uri.equals("/detail.board")) {
				
				int detailseq = Integer.parseInt(request.getParameter("seq"));
				BoardDTO dto =  BoardDAO.getInstance().selectBySeq(detailseq);
				request.setAttribute("dto", dto);
				
//				System.out.println(dto);
//				System.out.println(dto.getContents());
				
				List<CommentsDTO> list = CommentsDAO.getInstance().selectCommentBySeq(detailseq);
				request.setAttribute("list", list);
				
//				System.out.println(list.get(0).getContents());  // list 에 담긴거 꺼내오려면 겟인덱스에서 값 꺼내기

				BoardDAO.getInstance().addViewCount(detailseq);
				
				List<FilesDTO> filelist = FilesDAO.getInstance().selectBySeq(detailseq);
				request.setAttribute("filelist", filelist);
				
				request.getRequestDispatcher("/board/boarddetail.jsp").forward(request, response);
				
			} else if (uri.equals("/delete.board")) {

				int detailseq = Integer.parseInt(request.getParameter("seq"));
				BoardDAO.getInstance().delete(detailseq);
				CommentsDAO.getInstance().deleteCommentByParentSeq(detailseq);
				FilesDAO.getInstance().deleteFilesByParentSeq(detailseq);
				response.sendRedirect("/list.board?cpage=1");
				
			} else if (uri.equals("/modify.board")) {
				
				int detailseq = Integer.parseInt(request.getParameter("seq"));
				
				BoardDAO dao = BoardDAO.getInstance();
				BoardDTO dto = dao.selectBySeq(detailseq); // list로 해도 되지만 하나라서 불필요하다. dto로만 해도 된다.
				request.setAttribute("list", dto);
				request.getRequestDispatcher("/board/boardmodify.jsp").forward(request, response);
				
			} else if (uri.equals("/checkmodify.board")) {
				
				int detailseq = Integer.parseInt(request.getParameter("seq"));
				System.out.println(detailseq);
				
				String title = request.getParameter("titlepost");
				String contents = request.getParameter("contentspost");

				BoardDAO dao = BoardDAO.getInstance();
				dao.modifyBySeq(title,contents,detailseq);
				
//				이 방법도 되고		
//				response.sendRedirect("/detail.board?seq="+detailseq);

// 				이 방법도 되고
				request.setAttribute("seq", detailseq);
				request.getRequestDispatcher("/detail.board").forward(request, response);
				
//				이 방법도 되고(비효율적)
//				BoardDTO dto = dao.selectBySeq(detailseq);
//				request.setAttribute("list", dto);
//				request.getRequestDispatcher("/board/boarddetail.jsp").forward(request, response);
				
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
