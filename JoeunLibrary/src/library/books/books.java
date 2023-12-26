package library.books;

import java.util.Scanner;

import library.connect.OracleConnect;

public class books extends OracleConnect {
	Scanner scan = new Scanner(System.in);

	public books() {
		super("library", "1234");
	}

	public void addbook() {
		
		System.out.println("책 제목 : ");
		String bookName = scan.nextLine();


		System.out.println("책 수량 : ");
		int bookAmount = scan.nextInt();
		scan.nextLine();
		
		
		

		try {
			String sql = "insert into books values(seq_bookno.nextval, ?, ?)";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, bookName);
			psmt.setInt(2, bookAmount);
			int updateCount = psmt.executeUpdate();
			if (updateCount == 1) {
				System.out.println("데이터가 정상적으로 추가되었습니다.");
			} else {
				System.out.println("데이터 입력에 실패했습니다.(#추가오류)");
			}
//			System.out.println("insertCount : " + updateCount);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("데이터 입력오류");
		}

	}

	public void bookSelect() {
		try {
			
			System.out.println("검색할 책 제목: ");
			String name = scan.nextLine();

			String sql1 = "select * from books where bookName = ?";
			psmt = con.prepareStatement(sql1);
			psmt.setString(1, name);
			rs = psmt.executeQuery();
			System.out.println("----------------------------------------");
			int nResult = 0;
			while (rs.next()) {
				nResult++;
				System.out.println("책번호 : " + rs.getString("bookNo"));
				System.out.println("제  목 : " + rs.getString("bookName"));
				System.out.println("수  량 : " + rs.getInt("bookAmount"));
				System.out.println("----------------------------------------");
			}
			if (nResult == 0) {
				System.out.println("조회할 데이터가 없습니다.");
				System.out.println("----------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("데이터 입력에 실패했습니다.(#3)");
		}

	}

	public void bookAll() {
		try {
			System.out.println("전체 책을 조회합니다.");
			String sql = "select * from books order by bookno";
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			System.out.println("----------------------------------------");
			while (rs.next()) {
				System.out.println("책번호 : " + rs.getString("bookno"));
				System.out.println("제  목 : " + rs.getString("bookName"));
				System.out.println("수  량 : " + rs.getInt("bookAmount"));
				System.out.println("----------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void bookDelete() {
		try {
			System.out.println("삭제할 책 제목 : ");
			String bookName = scan.nextLine();
			String sql = "delete from books where bookName = ?";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, bookName);
			rs = psmt.executeQuery();
			
			System.out.println(rs.getString("bookName") + " -책이 삭제되었습니다.");
			int updateCount = psmt.executeUpdate();
			System.out.println("DropCount : " + updateCount);
		} catch (Exception e) {
			System.out.println("데이터베이스 삭제 에러입니다.");
		}
	}

	public void libraryMenu() {
		System.out.println("---사서모드-----");
		System.out.println("1. 책 등록 ");
		System.out.println("2. 책 조회");
		System.out.println("3. 전체 책 조회");
		System.out.println("4. 책 삭제");

	}

	public void libraryMode() {
		int choice;
		while (true) {
			libraryMenu();
			choice = scan.nextInt();
			scan.nextLine();

			switch (choice) {
			case 1:
				System.out.println("책등록");
				System.out.println();
				addbook();
				break;
			case 2:
				System.out.println("책조회");
				System.out.println();
				bookSelect();
				break;
			case 3:
				System.out.println("전체 책 조회");
				bookAll();
				break;
			case 4:
				System.out.println("책 삭제");
				bookDelete();
				break;

			default:
				System.out.println("잘못 입력하셨습니다.");
				break;

			}
		}
	}
}
