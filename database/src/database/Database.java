package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://127.0.0.1:3307/databasetest?serverTimezone=UTC";
		try {
		Class.forName(jdbc_driver).newInstance();		// DB 로드
		Connection con = DriverManager.getConnection(jdbc_url, "root", "qhdlsrh6");		// DB와 연결
		
		Statement st = con.createStatement();
		String db = "CREATE TABLE addressbook("
				+ "id INT NOT NULL PRIMARY KEY, name VARCHAR(45) NOT NULL, "
				+ "tel VARCHAR(45) NOT NULL, email VARCHAR(45) NOT NULL, address VARCHAR(60) NOT NULL)";
		
		st.executeUpdate(db);
		
		PreparedStatement st1 = con.prepareStatement("INSERT INTO databasetest.addressbook VALUES(?,?,?,?,?)");

		st1.setInt(1, 201455035);
		st1.setString(2, "이유신");
		st1.setString(3, "010-5561-3908");
		st1.setString(4, "fjrvldi6@naver.com");
		st1.setString(5, "서울특별시 송파구");
		st1.executeUpdate();
		
		st1.setInt(1, 201555001);
		st1.setString(2, "양찬석");
		st1.setString(3, "010-0000-0000");
		st1.setString(4, "didckstjr@hs.ac.kr");
		st1.setString(5, "분당");
		st1.executeUpdate();
		
		st1.setInt(1, 201555002);
		st1.setString(2, "임길주");
		st1.setString(3, "010-0000-1111");
		st1.setString(4, "dlarlfwn@hs.ac.kr");
		st1.setString(5, "일산");
		st1.executeUpdate();
		
		String sql = "SELECT * FROM databasetest.addressbook";
		ResultSet rs = st.executeQuery(sql);
		
		while( rs.next() ) {	// 데이터 입력 후 출력
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String tel = rs.getString("tel");
			String email = rs.getString("email");
			String address = rs.getString("address");
			
			System.out.printf("id: %d, name: %s, tel: %s, email: %s, address: %s\n", id, name, tel, email, address);
		}
		
		String sql_email = "UPDATE databasetest.addressbook SET email = CONCAT(SUBSTRING_INDEX(email , '@', 1),'@naver.com'); ";	// 도메인을 네이버로 수정
		st.executeUpdate(sql_email);	// 데이터베이스에 적용
		
		rs = st.executeQuery(sql);
		
		System.out.println();
		System.out.println();

		
		while( rs.next() ) {	// 도메인 변경 후 출력
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String tel = rs.getString("tel");
			String email = rs.getString("email");
			String address = rs.getString("address");
			
			System.out.printf("id: %d, name: %s, tel: %s, email: %s, address: %s\n", id, name, tel, email, address);
		}
		
		String sql_count = "SELECT count(id) FROM databasetest.addressbook;";	// 데이터 수 확인
		rs = st.executeQuery(sql_count);
		
		rs.next();
		int count = rs.getInt("count(id)");

		rs = st.executeQuery(sql);

		for( int i=0; i<=count-2; i++) {
			rs.next();
		}

		int del1 = rs.getInt("id");
		rs.next();
		int del2 = rs.getInt("id");

		String sql_delete = "DELETE FROM databasetest.addressbook WHERE id = " + del1 + " OR id =" + del2 + ";";
		st.executeUpdate(sql_delete);
		
		System.out.println();
		System.out.println();
		
		rs = st.executeQuery(sql);
		
		while( rs.next() ) {	// 도메인 변경 후 출력
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String tel = rs.getString("tel");
			String email = rs.getString("email");
			String address = rs.getString("address");
			
			System.out.printf("id: %d, name: %s, tel: %s, email: %s, address: %s\n", id, name, tel, email, address);
		}
		
		
		rs.close();
		st.close();
		st1.close();
		con.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		} 
	}

}
