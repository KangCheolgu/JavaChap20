package ch20.sec10;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class ProcedureCallExample {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "Kang", "12345");

			String sql = "{call user_create(?,?,?,?,?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(1, "summer");
			cstmt.setString(2,  "한여름");
			cstmt.setString(3,  "12345");
			cstmt.setInt(4,     26);
			cstmt.setString(5,  "summeremycompany.com");
			cstmt.registerOutParameter(6, Types.INTEGER);
			
			// SQL 운 실행
			cstmt.execute();
			int rows = cstmt.getInt(6);
			System.out.println("수정된  행  수:  " + rows);

			// PreparedStatement 닫기
			cstmt.close();
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
