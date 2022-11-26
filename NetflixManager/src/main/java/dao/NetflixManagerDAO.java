package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.NetflixManagerDTO;

public class NetflixManagerDAO {

	private static NetflixManagerDAO instance;
	synchronized public static NetflixManagerDAO getInstance() throws Exception{
		if(instance == null) { // 동시에 여러명 들어오면 뚫릴 수 있어서 synchronized 키워드 붙여야함. 
			instance = new NetflixManagerDAO(); // 인스턴스를 하나만 만들어서 빌려주려고하기 때문에 개수 제한 없어도 된다.
		}
		return instance;
	}
	
// 생성자
	private NetflixManagerDAO() {}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext(); // tomcat 환경 찾아서 요구하는 코드
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle"); // java:comp/env/ 고정값
		
		return ds.getConnection();
	}
	

//	DBCP 하기 위해서 주석처리
//	public Connection getConnection() throws Exception {
//		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
//		String dbID = "kh";
//		String dbPW = "kh";
//		return DriverManager.getConnection(dbURL, dbID, dbPW);
//	}

	
	
	
	public int insert(String title, String genre) throws Exception {

		String sql = "insert into movies (id,title,genre) values(movies_id.nextval,?,?)";

		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setString(1, title);
			pstat.setString(2, genre);

			int result = pstat.executeUpdate();

			con.commit(); // 높은 버전의 ojbc 사용할 때 commit 하면 error 난다. 조심
			con.close();

			return result;
		}
	}

	
	public List<NetflixManagerDTO> selectAll() throws Exception {
		String sql = "select * from movies order by 1";

		try (Connection con = this.getConnection(); 
			PreparedStatement pstat = con.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();) { // ResultSet 도 close 해줘야한다.

			List<NetflixManagerDTO> list = new ArrayList<>();

			while (rs.next()) {
				NetflixManagerDTO dto = new NetflixManagerDTO();
				
				dto.setId(rs.getInt("id"));
				dto.setTitle(rs.getString("title"));
				dto.setGenre(rs.getString("genre"));
				dto.setLaunch_date(rs.getTimestamp("launch_date"));
				
				list.add(dto);
			}
			
			return list;
		}
	}
	
	
	public int delete(int id) throws Exception {

		String sql = "delete from movies where id = ?";

		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, id);

			int result = pstat.executeUpdate();
			con.commit();

			return result;
		}
	}
	
	
	public int update(NetflixManagerDTO dto) throws Exception {

		String sql = "update movies set title=?, genre=? where id = ?";

		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getGenre());
			pstat.setInt(3, dto.getId());

			int result = pstat.executeUpdate();
			con.commit();

			return result;
		}
	}
	
	
	public List<NetflixManagerDTO> search(String titlesearch) throws Exception {
		String sql = "select * from movies where title like '%"+ titlesearch +"%' order by 1";

		try (Connection con = this.getConnection(); 
			PreparedStatement pstat = con.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();) { // ResultSet 도 close 해줘야한다.
			
			List<NetflixManagerDTO> list = new ArrayList<>();

			while (rs.next()) {
				NetflixManagerDTO dto = new NetflixManagerDTO();
				
				dto.setId(rs.getInt("id"));
				dto.setTitle(rs.getString("title"));
				dto.setGenre(rs.getString("genre"));
				dto.setLaunch_date(rs.getTimestamp("launch_date"));
				
				list.add(dto);
			}
			
			return list;
		}
	}
	
	
	
	
}
