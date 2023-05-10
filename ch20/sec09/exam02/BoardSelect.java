package ch20.sec09.exam02;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

public class BoardSelect {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "Kang", "12345");

			String sql = "" + "SELECT bno, btitle, bcontent, bwriter, bdate, bfilename, bfiledata " + "FROM boards "
					+ "WHERE bwriter=?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "winter");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBfilename(rs.getString("bfilename"));
				board.setBfiledata(rs.getBlob("bfiledata"));

				// 콘솔에 출력
				System.out.println(board);

				// 파일로 저장
				Blob blob = board.getBfiledata();
				if (blob != null) {
					InputStream is = blob.getBinaryStream();
//					OutputStream os = new FileOutputStream("src/ch20/sec09/exam01/" + board.getBfilename());
					OutputStream os = new FileOutputStream("c:/temp/" + board.getBfilename());
					is.transferTo(os);
					os.flush();
					os.close();
					is.close();

				}
			}

			rs.close();

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
