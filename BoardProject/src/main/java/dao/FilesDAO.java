package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.FilesDTO;

public class FilesDAO {

	private static FilesDAO instance;

	synchronized public static FilesDAO getInstance() throws Exception {
		if (instance == null) { // 동시에 여러명 들어오면 뚫릴 수 있어서 synchronized 키워드 붙여야함.
			instance = new FilesDAO(); // 인스턴스를 하나만 만들어서 빌려주려고하기 때문에 개수 제한 없어도 된다.
		}
		return instance;
	}

	// 생성자 더 생성 안되게 막음
	private FilesDAO() {
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext(); // tomcat 환경 찾아서 요구하는 코드
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle"); // java:comp/env/ 고정값

		return ds.getConnection();
	}

	public int insert(FilesDTO dto) throws Exception {
		String sql = "insert into files values(files_seq.nextval,?,?,?)";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getOriName());
			pstat.setString(2, dto.getSysName());
			pstat.setInt(3, dto.getParent_seq());
			int result = pstat.executeUpdate();
			con.commit(); // 높은 버전의 ojbc 사용할 때 commit 하면 error 난다. 조심
			return result;
		}
	}

//		public List<FilesDTO> selectALL() throws Exception {
//			String sql = "select * from files";
//			try (Connection con = this.getConnection(); 
//					PreparedStatement pstat = con.prepareStatement(sql);
//					ResultSet rs = pstat.executeQuery();) {
//				
//				List<FilesDTO> list = new ArrayList<>();
//				
//				while(rs.next()) {
//					FilesDTO dto = new FilesDTO();
//					dto.setSeq(rs.getInt("seq"));
//					dto.setOriName(rs.getString("oriname"));
//					dto.setSysName(rs.getString("sysname"));
//					dto.setParent_seq(rs.getInt("parent_seq"));
//					list.add(dto);
//					}
//					return list;
//				}
//		}

	public List<FilesDTO> selectBySeq(int seq) throws Exception {
		String sql = "select * from files where parent_seq= ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, seq);

			try (ResultSet rs = pstat.executeQuery();) {
				List<FilesDTO> list = new ArrayList<>();

				while (rs.next()) {
					FilesDTO dto = new FilesDTO();
					dto.setSeq(rs.getInt("seq"));
					dto.setOriName(rs.getString("oriname"));
					dto.setSysName(rs.getString("sysname"));
					dto.setParent_seq(rs.getInt("parent_seq"));
					list.add(dto);
				}

				return list;
			}
		}
	}

	public int deleteFilesByParentSeq(int parent_seq) throws Exception {

		String sql = "delete from files where parent_seq= ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, parent_seq);

			int result = pstat.executeUpdate();
			con.commit();

			return result;
		}
	}

	
	
}
