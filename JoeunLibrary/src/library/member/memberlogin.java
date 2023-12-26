package library.member;

import java.util.Scanner;

import library.books.books;
import library.books.rentalreturn;
import library.connect.OracleConnect;

public class memberlogin extends OracleConnect {

	books books = new books();
	rentalreturn book = new rentalreturn();

	Scanner scan = new Scanner(System.in);

	public memberlogin() {
		super("library", "1234");
	}

	public void excute() {
			System.out.print("ID : ");
			String id = scan.nextLine();
			System.out.print("Password : ");
			String pw = scan.nextLine();

			try {
			String sql = "select * from member where id = ? and pw = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();
			int nResult = 0;
			while (rs.next()) {
				nResult++;
				rs.getString("id");
				rs.getString("pw");
				System.out.println(rs.getString("name") + "님의 방문을 환영합니다.");
				if (id.equals("admin") && pw.equals("1234")) {

					books.libraryMode();
				} else {

					

				}
				System.out.println("----------------------------------------");
			}
			if (nResult == 0) {
				System.out.println("아이디 또는 패스워드가 다르거나");
				System.out.println("등록되어 있지 않은 계정입니다.");
				System.out.println("----------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("입력에 실패했습니다.(#3)");
		}

	}
}
