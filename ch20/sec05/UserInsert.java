package ch20.sec05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserInsert {

	public static void main(String[] args) {
		
		Connection conn = null;
		try {
			//jdbc 드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"Kang",
					"12345"
					);
			String sql = "" + 
					"INSERT INTO users " + "VALUES (?,?,?,?,?)";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "winter");
			pstmt.setString(2, "한겨울");
			pstmt.setString(3, "12345");
			pstmt.setInt(4, 25);
			pstmt.setString(5,"winter@mycompany.com");
			
			int rows = pstmt.executeUpdate();
			System.out.println("저장된 행 수 : " + rows);
			
			pstmt.close();
			
			System.out.println("연결성공");
		} catch  ( ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					//연결 끊기
					conn.close();
					System.out.println("연결 끊기");
				} catch (SQLException e ) {}
			}
		}
	}

}
