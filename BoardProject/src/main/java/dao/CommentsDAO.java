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
import dto.CommentsDTO;

public class CommentsDAO {

	private static CommentsDAO instance;

	synchronized public static CommentsDAO getInstance() throws Exception {
		if (instance == null) { // 동시에 여러명 들어오면 뚫릴 수 있어서 synchronized 키워드 붙여야함.
			instance = new CommentsDAO(); // 인스턴스를 하나만 만들어서 빌려주려고하기 때문에 개수 제한 없어도 된다.
		}
		return instance;
	}

// 생성자 더 생성 안되게 막음
	private CommentsDAO() {
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext(); // tomcat 환경 찾아서 요구하는 코드
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle"); // java:comp/env/ 고정값

		return ds.getConnection();
	}
	
	public int insertComment(String writer, String contents, int parent_seq) throws Exception {

		String sql = "insert into comments values(comments_seq.nextval,?,?,sysdate,?)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setString(1, writer);
			pstat.setString(2, contents);
			pstat.setInt(3, parent_seq);
			
			int result = pstat.executeUpdate();

			con.commit();

			return result;
		}
	}


	public List<CommentsDTO> selectCommentBySeq(int parent_seq) throws Exception {
		String sql = "select * from comments where parent_seq= ? order by seq asc";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, parent_seq);

			try (ResultSet rs = pstat.executeQuery();) {

				List<CommentsDTO> list = new ArrayList<>();
				
				while (rs.next()) {
					CommentsDTO dto = new CommentsDTO();
					
					dto.setSeq(rs.getInt("seq"));
					dto.setWriter(rs.getString("writer"));
					dto.setContents(rs.getString("contents"));
					dto.setWrite_date(rs.getTimestamp("write_date"));
					dto.setParent_seq(rs.getInt("parent_seq"));
					list.add(dto);
				}
	
				return list;
			}
		}
	}
	
	

	public int deleteComment(int seq) throws Exception {

		String sql = "delete from comments where seq= ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, seq);

			int result = pstat.executeUpdate();
			con.commit();

			return result;
		}
	}

	public int deleteCommentByParentSeq(int parent_seq) throws Exception {

		String sql = "delete from comments where parent_seq= ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, parent_seq);

			int result = pstat.executeUpdate();
			con.commit();

			return result;
		}
	}

	
	
	public int modifyCommentBySeq(String contents, int seq) throws Exception {
		String sql = "update comments set contents=? where seq=?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setString(1, contents);
			pstat.setInt(2, seq);

			int result = pstat.executeUpdate();
			con.commit();

			return result;

		}
	}
	
	// 댓글의 개수를 반환하는 코드 짜기
//		public BoardDTO getCommentsCount() throws Exception {
//			String sql = "SELECT c.parent_seq,count(*) FROM comments c, board b WHERE c.parent_seq = b.seq GROUP BY c.parent_seq";
//			
//			try (Connection con = this.getConnection();
//					PreparedStatement pstat = con.prepareStatement(sql);
//					ResultSet rs = pstat.executeQuery();){
//						
//				rs.next();
//				BoardDTO dto = new BoardDTO();
//
//				dto.setSeq(rs.getInt("seq"));
//				dto.setWriter(rs.getString("writer"));
//
//				return dto;
//				
//			}
//		}
	
	
	
//	[KH 조성태 강사님] [오전 11:03] 쿼리문해결방법
//	[KH 조성태 강사님] [오전 11:03] select * from 
//	(select 
//	    board.*, 
//	    row_number() over(order by seq desc) rn, 
//	    (select count(*) from comments where parent_seq=board.seq) cmtCount
//	from board) 
//	where rn between 1 and 10;

		
			
		
		// 댓글의 개수를 반환하는 코드 짜기
				public int getCommentsCount(int parent_seq) throws Exception {
					String sql = "select count(*) from comments where parent_seq=?";
					try (Connection con = this.getConnection();
							PreparedStatement pstat = con.prepareStatement(sql);) {
						
						pstat.setInt(1, parent_seq);

						try(ResultSet rs = pstat.executeQuery();){
								
						rs.next();
						return rs.getInt(1);
						}
					}
				}
	
	
}
