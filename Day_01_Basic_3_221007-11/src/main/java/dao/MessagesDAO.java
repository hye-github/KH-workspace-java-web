package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MessagesDTO;

public class MessagesDAO {

	private static MessagesDAO instance;
	synchronized public static MessagesDAO getInstance() throws Exception{
		if(instance == null) { // 동시에 여러명 들어오면 뚫릴 수 있어서 synchronized 키워드 붙여야함. 
			instance = new MessagesDAO(); // 인스턴스를 하나만 만들어서 빌려주려고하기 때문에 개수 제한 없어도 된다.
		}
		return instance;
	}
	
	// 생성자
		private MessagesDAO() {}

		private Connection getConnection() throws Exception {
			Context ctx = new InitialContext(); // tomcat 환경 찾아서 요구하는 코드
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle"); // java:comp/env/ 고정값
			
			return ds.getConnection();
		}
	
//	private MessagesDAO() throws Exception {
//		Class.forName("oracle.jdbc.driver.OracleDriver"); // 낮은 버전의 ojbc를 사용하면 넣어줘야한다.
//	}
//
//	public Connection getConnection() throws Exception {
//		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
//		String dbID = "kh";
//		String dbPW = "kh";
//		return DriverManager.getConnection(dbURL, dbID, dbPW);
//	}

	public int insert(String writer, String msg) throws Exception {

		String sql = "insert into message (seq,writer,message) values(message_seq.nextval,?,?)";

		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setString(1, writer);
			pstat.setString(2, msg);

			int result = pstat.executeUpdate();

			con.commit(); // 높은 버전의 ojbc 사용할 때 commit 하면 error 난다. 조심
			con.close();

			return result;
		}
	}

	
	public List<MessagesDTO> selectAll() throws Exception {
		String sql = "select * from message order by 1";

		try (Connection con = this.getConnection(); 
			PreparedStatement pstat = con.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();) { // ResultSet 도 close 해줘야한다.

			List<MessagesDTO> list = new ArrayList<>();

			while (rs.next()) {

				MessagesDTO dto = new MessagesDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setMessage(rs.getString("message"));

				list.add(dto);
			}
			
			return list;
		}
	}
	
	
	public int delete(int seq) throws Exception {

		String sql = "delete from message where seq = ?";

		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, seq);

			int result = pstat.executeUpdate();
			con.commit();

			return result;
		}
	}
	
	
	public int update(MessagesDTO dto) throws Exception {

		String sql = "update message set writer=?, message=? where seq = ?";

		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getMessage());
			pstat.setInt(3, dto.getSeq());

			int result = pstat.executeUpdate();
			con.commit();

			return result;
		}
	}
	
}