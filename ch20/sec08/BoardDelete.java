package ch20.sec08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardDelete {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			// JDBC Driver 둥록
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "Kang", "12345");

			String sql = "DELETE FROM boards WHERE bwriter = ? ";
					
			// PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			System.out.println("whywhywhy");
			pstmt.setString(1, "winter");
	
			
			
			// SQL 운 실행
			int rows = pstmt.executeUpdate();
			System.out.println("삭제된  행  수:  " + rows);

			// PreparedStatement 닫기
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					// 연결 끊기
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}