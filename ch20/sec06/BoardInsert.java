package ch20.sec06;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardInsert {

	public static void main(String[] args) {

		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "Kang", "12345");
			String sql = "" + "INSERT INTO boards" + " VALUES (SEQ_BNO.NEXTVAL,?,?,?,SYSDATE,?,?)";

			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "bno" });

			pstmt.setString(1, "눈오는날");
			pstmt.setString(2, "함박눈이 내려요");
			pstmt.setString(3, "winter");
			pstmt.setString(4, "snow.jpg");
			pstmt.setBlob(5, new FileInputStream("src/ch20/sec06/snow.jpg"));

			int rows = pstmt.executeUpdate();
			System.out.println("저장된 행 수 : " + rows);

			if (rows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int bno = rs.getInt(1);
					System.out.println("저장된 bno : " + bno);
				}
				rs.close();
			}

			pstmt.close();

			
			
			
			
			System.out.println("연결성공");
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
					System.out.println("연결 끊기");
				} catch (SQLException e) {
				}
			}
		}
	}

}
