package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;

public class BoardDAO {

	private static BoardDAO instance;

	synchronized public static BoardDAO getInstance() throws Exception {
		if (instance == null) { // 동시에 여러명 들어오면 뚫릴 수 있어서 synchronized 키워드 붙여야함.
			instance = new BoardDAO(); // 인스턴스를 하나만 만들어서 빌려주려고하기 때문에 개수 제한 없어도 된다.
		}
		return instance;
	}

// 생성자 더 생성 안되게 막음
	private BoardDAO() {
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext(); // tomcat 환경 찾아서 요구하는 코드
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle"); // java:comp/env/ 고정값

		return ds.getConnection();
	}

	public int insert(BoardDTO dto) throws Exception {

		String sql = "insert into board values(?,?,?,?,sysdate,0)";
		//String sql = "insert into board values(board_seq.nextval,?,?,?,sysdate,0)";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			
			pstat.setInt(1, dto.getSeq());
			pstat.setString(2, dto.getWriter());
			pstat.setString(3, dto.getTitle());
			pstat.setString(4, dto.getContents());

			int result = pstat.executeUpdate();

			con.commit(); // 높은 버전의 ojbc 사용할 때 commit 하면 error 난다. 조심

			return result;
		}
	}

	public int getNextVal() throws Exception{
		String sql = "select board_seq.nextval from dual";
		// board 테이블 대상으로 select 하게 되면 nextval 이라는건 반복이라 반복문이 되게 된다. 우리가 원하는건 단 한번.
		
		try(Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()){
			rs.next();
			
			return rs.getInt(1);
			// https://aricode.tistory.com/10
		}
		
	}
	
	
//	public List<BoardDTO> selectAll() throws Exception {
//		String sql = "select * from board order by seq desc";
//
//		try (Connection con = this.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);
//				ResultSet rs = pstat.executeQuery();) {
//
//			List<BoardDTO> list = new ArrayList<>();
//
//			while (rs.next()) {
//				BoardDTO dto = new BoardDTO();
//
//				dto.setSeq(rs.getInt("seq"));
//				dto.setWriter(rs.getString("writer"));
//				dto.setTitle(rs.getString("title"));
//				dto.setContents(rs.getString("contents"));
//				dto.setWrite_date(rs.getTimestamp("write_date"));
//				dto.setView_count(rs.getInt("view_count"));
//				list.add(dto);
//			}
//
//			return list;
//		}
//	}

	
	public List<BoardDTO> selectByRange(int start, int end) throws Exception {
		// int start, int end 는 행번호이다. seq 번호가 아니다.
		
		
		String sql = "select * from (select board.*, row_number() over(order by seq desc) rn from board) where rn between ? and ?";

		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, start);
			pstat.setInt(2, end);
			
			try (ResultSet rs = pstat.executeQuery();) {
				
				List<BoardDTO> list = new ArrayList<>();
				
				while (rs.next()) {

				BoardDTO dto = new BoardDTO();

				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getTimestamp("write_date"));
				dto.setView_count(rs.getInt("view_count"));
				list.add(dto);
				
				}
				
				return list;
			}
		}
	}
	
	
	public BoardDTO selectBySeq(int seq) throws Exception {
		String sql = "select * from board where seq= ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, seq);

			try (ResultSet rs = pstat.executeQuery();) {

				rs.next();

				BoardDTO dto = new BoardDTO();

				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getTimestamp("write_date"));
				dto.setView_count(rs.getInt("view_count"));

				return dto;
			}
		}
	}

	public int delete(int seq) throws Exception {

		String sql = "delete from board where seq= ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, seq);

			int result = pstat.executeUpdate();
			con.commit();

			return result;
		}
	}

	public int modifyBySeq(String title, String contents, int seq) throws Exception {
		String sql = "update board set title=?, contents=? where seq=?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setString(1, title);
			pstat.setString(2, contents);
			pstat.setInt(3, seq);

			int result = pstat.executeUpdate();
			con.commit();

			return result;

		}
	}

	public int addViewCount(int seq) throws Exception {
		String sql = "update board set view_count = view_count + 1 where seq=?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.commit();

			return result;

		}
	}

	// 게시글의 개수를 반환하는 코드 짜기
	public int getRecordCount() throws Exception {
		String sql = "select count(*) from board";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()) {
			rs.next();
			return rs.getInt(1);
		}
	}

	public String getPageNavi(int currentPage) throws Exception {

		int recordTotalCount = this.getRecordCount(); // DB를 통해서 데이터 총 갯수를 넣어줘야한다.
		// board 테이블에 총 144개의 글이 있다고 가정
		int recordCountPerPage = 10; // 게시판 한 페이지당 10개의 글씩 보여주기로 설정
		int naviCountPerPage = 10; // 게시판 하단의 Page Navigator 가 한번에 몇 개씩 보여질지 설정

		int pageTotalCount = 0;

		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = (recordTotalCount / recordCountPerPage) + 1; // 나누기하고 +1 해서 전체 페이지 개수를 보여준다.
			// 게시글의 개수 / 한 페이지당 보여줄 게시글 + 1 = 전체 페이지의 개수
			// + 1은 나머지가 0이 아닐 때만 해야한다.
		} else {
			pageTotalCount = (recordTotalCount / recordCountPerPage);
		}

		// if 문을 안써도 되는 공식 같은 문장 : 안지훈 : int pageTotalCount= (recordTotalCount+9) /
		// recordCountPerPage;
		// [KH 김일중] [오후 12:31] 이거도 될거같은데 소수점 올림 : int pageTotalCount = (int)
		// Math.ceil(1.0 * recordTotalCount / recordCountPerPage);
		// 박세진 : recordCountPerPage = 10일때만 해당될겁니다 간단하게 페이지당 2페이지 보이게하면 저걸로하면 페이지 엄청불어남
		// 안지훈 : int pageTotalCount= (recordTotalCount+(recordCountPerPage-1)) /
		// recordCountPerPage;

		// int currentPage = 12; // 현재페이지
		if (currentPage < 1) {
			currentPage = 1;
		}
		// 공격자들이 get 방식으로 currentPage 값이 -1이 넘어오게되면 error가 생기게 된다. 보안코드1
		// 제일 작은 페이지 값으로 보게 만든다.

		if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		// 최종 페이지가 페이지토탈페이지보다 크다면~ 최종페이지가 페이지토탈페이지가 되도록 조건 걸기. 보안코드2

		// 7 : 1 ~ 10
		// 15 : 11 ~ 20
		// 28 : 21 ~ 30 // 몇 페이지에 있느냐에 따라 아래 네비게이터가 보이는 숫자가 달라진다.
		// 1의 자리를 날리고 1을 붙이면 된다.

		// 20 : 11 ~ 20
		// int = (currentPage / 10) * 10 + 1 ; // 일반 숫자에서는 의미 없는 계산이지만, java에서는 int 에서
		// 나누면 int만 나오기 때문에 의미가 생긴다.
		// 10의 배수일 땐 이 공식은 성립하지않는다.

		// int startNavi = (currentPage-1) / 10 * 10 + 1 ; // recordCountPerPage = 10 일
		// 때만 성립됨.

		int startNavi = (currentPage - 1) / recordCountPerPage * recordCountPerPage + 1;

		int endNavi = startNavi + naviCountPerPage - 1;

		// endNavi 는 pageTotalCount 보다 클 수 없다.

		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		System.out.println("현재 페이지 : " + currentPage);
		System.out.println("네비게이터 시작 : " + startNavi);
		System.out.println("네비게이터 끝 : " + endNavi);

		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;
		}

		if (endNavi == pageTotalCount) {
			needNext = false;
		}

//		if(needPrev) {
//			System.out.print("< ");
//		}
//		
//		for(int i = startNavi ; i <= endNavi ; i++) {
//			System.out.print(i + " ");
//		}
//		
//		if(needNext) {
//			System.out.print(" >");
//		}

		StringBuilder sb = new StringBuilder(); // 문자열 연결해주는 용도로 사용하는 클래스이다.

		if (needPrev) {
			sb.append("<li class=\"page-item\"><a class=\"page-link\" href='/list.board?cpage=" + (startNavi - 1)
					+ "'>Previous</a></li>");
		}

		for (int i = startNavi; i <= endNavi; i++) {
			
			if (currentPage==i) {
				sb.append("<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href=\"/list.board?cpage=" + i + "\">" + i
						+ "</a></li>");
			} else {
				sb.append("<li class=\"page-item\"><a class=\"page-link\" href=\"/list.board?cpage=" + i + "\">" + i
						+ "</a></li>");
			}
			
		}

		if (needNext) {
			sb.append("<li class=\"page-item\"><a class=\"page-link\" href='/list.board?cpage=" + (endNavi + 1)
					+ "'>Next</a></li>");
		}

		
		
		return sb.toString();
		
	}

//	public static void main(String[] args) throws Exception {
//		new BoardDAO().getPageNavi();
//	}
	
	

}
