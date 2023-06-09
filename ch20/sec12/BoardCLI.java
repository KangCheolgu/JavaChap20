package ch20.sec12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BoardCLI {

	private Scanner scan = new Scanner(System.in);
	private Connection conn;

	public BoardCLI() {
		try {

			Class.forName("oracle.jdbc.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "Kang", "12345");

		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
	}

	public void list() {
		System.out.println();
		System.out.println("--------------------------------------------------------");
		System.out.println("[게시물 목록]");
		System.out.println("--------------------------------------------------------");
		System.out.printf("%-6s\t%-12s\t%-8s\t%-40s\n", "no", "writer", "date", "title");
		System.out.println("--------------------------------------------------------");
		try {
			String sql = "" + "SELECT bno, btitle, bcontent, bwriter, bdate " + " FROM boards " + " ORDER BY bno DESC ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));

				System.out.printf("%-6s\t%-12s\t%-8s\t%-40s\n", board.getBno(), board.getBwriter(), board.getBdate(),
						board.getBtitle());
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			exit();
		}
		mainMenu();
	}

	public void mainMenu() {
		System.out.println();
		System.out.println("--------------------------------------------------------");
		System.out.println("메인 메뉴 : 1.Create | 2.Read | 3.Clear | 4.Exit");
		System.out.print("메뉴 선택 : ");
		String menuNo = scan.nextLine();
		System.out.println();

		switch (menuNo) {
		case "1" -> create();
		case "2" -> read();
		case "3" -> clear();
		case "4" -> exit();
		}
	}

	public void create() {

		Board board = new Board();

		System.out.println("[새 게시물 입력]");
		System.out.print("제목 : ");
		board.setBtitle(scan.nextLine());
		System.out.print("내용 : ");
		board.setBcontent(scan.nextLine());
		System.out.print("작성자 : ");
		board.setBwriter(scan.nextLine());

		// 보조 메뉴 출력

		System.out.println("--------------------------------------------------------");
		System.out.println("보조 메뉴 : 1.Ok | 2. Cancel");
		System.out.print("메뉴 선택 : ");
		String menuNo = scan.nextLine();
		if (menuNo.equals("1")) {
			try {
				String sql = "" + "INSERT INTO boards (bno, btitle, bcontent, bwriter, bdate) "
						+ "VALUES (SEQ_BNO.NEXTVAL, ?,?,?,SYSDATE)";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, board.getBtitle());
				pstmt.setString(2, board.getBcontent());
				pstmt.setString(3, board.getBwriter());
				pstmt.executeUpdate();
				pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
				exit();
			}
		}
		list();
	}

	public void read() {

		System.out.println("게시물 읽기");
		System.out.print("bno : ");
		int bno = Integer.parseInt(scan.nextLine());

		try {
			String sql = "" + "select bno, btitle, bcontent, bwriter, bdate from boards " + "where bno = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				
				Board board = new Board();

				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				System.out.println("#######################");
				System.out.println("번호\t:\t" + board.getBno());
				System.out.println("제목\t:\t" + board.getBtitle());
				System.out.println("내용\t:\t" + board.getBcontent());
				System.out.println("작성자\t:\t" + board.getBwriter());
				System.out.println("날짜\t:\t" + board.getBdate());
				System.out.println("-----------------------");
				System.out.println("보조 메뉴 : 1.Update | 2.Delete | 3.List");
				System.out.print("메뉴 선택 : ");
				String menuNo = scan.nextLine();
				
				if(menuNo.equals("1")) {
					update(board);
				} else if (menuNo.equals("2")){
					delete(board);
				} 
				
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
		list();
	}

	public void update (Board board) {
		
		System.out.println("[수정 내용 입력]");
		System.out.print("제목: ");
		board.setBtitle(scan.nextLine());
		System.out.print("내용: ");
		board.setBcontent(scan.nextLine());
		System.out.print("작성자: ");
		board.setBwriter(scan.nextLine());

		System.out.println("--------------------------------------------------------");
		System.out.println("보조 메뉴 : 1.Ok | 2. Cancel");
		System.out.print("메뉴 선택 : ");
		String menuNo = scan.nextLine();
		
		if (menuNo.equals("1")) {
			try {
				String sql = "" + "update boards set btitle = ?, bcontent = ?, bwriter = ? " + 
						"where bno  = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, board.getBtitle());
				pstmt.setString(2, board.getBcontent());
				pstmt.setString(3, board.getBwriter());
				pstmt.setInt(4, board.getBno());
				pstmt.executeUpdate();
				pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
				exit();
			}
		}
		list();;
	}

	private void delete(Board board) {
		
		try {

			String sql = "DELETE FROM boards Where bno = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBno());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
		list();
		
	}

	public void clear() {
		System.out.println("[게시물 전체 삭제]");
		System.out.println("[삭제시 되돌릴수 없습니다]");
		System.out.println("[정말 실행 하시겠습니까?]");
		System.out.println("--------------------------------------------------------");
		System.out.println("보조 메뉴 : 1.Ok | 2. Cancel");
		System.out.print("메뉴 선택 : ");
		String menuNo = scan.nextLine();
	
		if (menuNo.equals("1")) {
			try {
				
				// 테이블 초기화
				String sql = "" + "TRUNCATE TABLE boards";

				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.executeUpdate();
				pstmt.close();
				
				
				// 시퀀스 초기화
				String sql2 = "" + "drop SEQUENCE SEQ_BNO";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				
				String sql3 = "" + "create SEQUENCE SEQ_BNO nocache";
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				
				pstmt2.executeUpdate();
				pstmt3.executeUpdate();
				
				pstmt2.close();
				pstmt3.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				exit();
			}
		}
		
		list();
	}

	public void exit() {
		if(conn != null) {
			if(conn != null) 
				try {
					conn.close();
				} catch (SQLException e ) {
				}
		}
		System.out.println(" ** 게시판 종료 ** ");
		System.exit(0);
	}

	public static void main(String[] args) {

		BoardCLI bE = new BoardCLI();
		bE.list();
	}
}
