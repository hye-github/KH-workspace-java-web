package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MembersDTO;


public class MembersDAO {
	
	private static MembersDAO instance;
	synchronized public static MembersDAO getInstance() throws Exception{
		if(instance == null) { // 동시에 여러명 들어오면 뚫릴 수 있어서 synchronized 키워드 붙여야함. 
			instance = new MembersDAO(); // 인스턴스를 하나만 만들어서 빌려주려고하기 때문에 개수 제한 없어도 된다.
		}
		return instance;
	}
	
// 생성자 더 생성 안되게 막음
	private MembersDAO() {}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext(); // tomcat 환경 찾아서 요구하는 코드
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle"); // java:comp/env/ 고정값
		
		return ds.getConnection();
	}
	
	
	public boolean isIdCheck(String id) throws Exception {
		String sql = "select * from members where id = ?";

		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) { 
			
			pstat.setString(1, id);
			
			try(ResultSet rs = pstat.executeQuery();){
				return rs.next();
			}
			
		}
	}

	public boolean isIdPwCheck(String id, String pw) throws Exception {
		String sql = "select * from members where id = ? and pw = ?";

		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) { 
			
			pstat.setString(1, id);
			pstat.setString(2, pw);
			
			try(ResultSet rs = pstat.executeQuery();){
				return rs.next();
			}
			
		}
	}
	
	

	public String getSHA512(String input) {
		
		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return toReturn;
	}
	
	
	
	public int insert(String id, String pw, String name, String phone, String email, String zipcode, String address1,
			String address2) throws Exception {

		String sql = "insert into members values(?,?,?,?,?,?,?,?,sysdate)";

		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setString(1, id);
			pstat.setString(2, pw);
			pstat.setString(3, name);
			pstat.setString(4, phone);
			pstat.setString(5, email);
			pstat.setString(6, zipcode);
			pstat.setString(7, address1);
			pstat.setString(8, address2);

			int result = pstat.executeUpdate();

			con.commit(); // 높은 버전의 ojbc 사용할 때 commit 하면 error 난다. 조심
			con.close();

			return result;
		}
	}

	
	public int delete(String id) throws Exception {

		String sql = "delete from members where id= ?";

		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setString(1, id);

			int result = pstat.executeUpdate();

			con.commit();
			con.close();

			return result;
		}
	}
	
	
	public MembersDTO selectById(String id) throws Exception {
		String sql = "select * from members where id= ?";

		try (Connection con = this.getConnection(); 
			PreparedStatement pstat = con.prepareStatement(sql); ) {

			pstat.setString(1, id);
			
			try (ResultSet rs = pstat.executeQuery();) {
				rs.next();
	
					MembersDTO list = new MembersDTO();
					
					list.setId(rs.getString("id"));
					list.setName(rs.getString("name"));
					list.setPhone(rs.getString("phone"));
					list.setEmail(rs.getString("email"));
					list.setZipcode(rs.getString("zipcode"));
					list.setAddress1(rs.getString("address1"));
					list.setAddress2(rs.getString("address2"));
					list.setSignup_date(rs.getTimestamp("signup_date"));
				
				return list;
			}
		}
	}
	
	public int modifyById(String pw, String name, String phone, String email, String zipcode, String address1,
			String address2, String id) throws Exception {
		String sql = "update members set pw=?, name=?, phone=?, email=?, zipcode=?, address1=?, address2=? where id=?";

		System.out.println(pw + name + phone + email);
		try (Connection con = this.getConnection(); 
			PreparedStatement pstat = con.prepareStatement(sql); ) {
			
			pstat.setString(1, pw);
			pstat.setString(2, name);
			pstat.setString(3, phone);
			pstat.setString(4, email);
			pstat.setString(5, zipcode);
			pstat.setString(6, address1);
			pstat.setString(7, address2);
			pstat.setString(8, id);
			
			int result = pstat.executeUpdate();

			con.commit();
			con.close();
			
			return result;
			
			}
		}
	
	
}
