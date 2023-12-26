package library.connect;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class OracleConnect implements Lconnect {
	public Connection con = null;
	public PreparedStatement psmt = null;   
	public PreparedStatement psmt1 = null;  
	public PreparedStatement psmt2 = null;   
	public PreparedStatement psmt3 = null;   
	public Statement stmt = null;
	public ResultSet rs = null;
	public ResultSet rs1 = null;
	public ResultSet rs2 = null;
	public ResultSet rs3 = null;

	public OracleConnect() {
	}

	// 아이디, 패스워드를 매개 변수로
	public OracleConnect(String user, String pass) {

		try {
			Class.forName(ORACLE_DRIVER);
			connect(user, pass);

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}

	public OracleConnect(String driver, String user, String pass) {
		try {
			Class.forName(driver);
			connect(user, pass);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}

	public void connect(String user, String pass) {
		try {
			// 인터페이스에 선언된 멤버 상수를 그대로 사용함.
			con = DriverManager.getConnection(ORACLE_URL, user, pass);
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 오류");
			e.printStackTrace();
		}
	}

	public void execute() {
	}

	public void close() {
		try {
			if (con != null)con.close();
				
			if (psmt != null)psmt.close();
			if (psmt1 != null)psmt.close();
				
			if (rs != null)rs.close();
			if (rs1 != null)rs.close();
			if (rs2 != null)rs.close();
			if (rs3 != null)rs.close();
		
			if (stmt != null)stmt.close();
				
		
				
			System.out.println("자원 반납 완료");
		} catch (Exception e) {
			System.out.println("자원 반납시 오류발생");
			e.printStackTrace();
		}
	}

	public String scanValue(String title) {
		Scanner scan = new Scanner(System.in);
		System.out.print(title + "을(를) 입력(exit->종료):");
		String inputStr = scan.nextLine();

		if ("EXIT".equalsIgnoreCase(inputStr)) {
			System.out.println("프로그램을 종료합니다.");

			close();
			System.exit(0);
		}
		return inputStr;
	}

}
