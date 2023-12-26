package library;

import java.util.Scanner;

import library.books.books;
import library.books.rentalreturn;
import library.connect.OracleConnect;
import library.member.memberjoin;


public class libraryProgram extends OracleConnect{

	public static void menuShow() {
		System.out.println(" Joeun 도서관 ");
		System.out.println("	1. 회원등록");
		System.out.println("	2. 책 등록");
		System.out.println("	3. 책 조회 ");
		System.out.println("	4. 전체 책 조회 ");
		System.out.println("	5. 낡은 책 삭제 ");
		System.out.println("	6. 책 대여");
		System.out.println("	7. 책 반납");
		System.out.println("	8. 대여 연장");
		System.out.println("   0.프로그램종료");
		

	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		memberjoin join = new memberjoin();
		rentalreturn rr = new rentalreturn();
		books book = new books(); 
		OracleConnect oc = new OracleConnect();
		
		while (true) {
			menuShow();

			int choice = scan.nextInt();

			switch (choice) {
			
			case 1:
				join.excute();
				break;
			case 2:
				book.addbook();
				break;
			case 3:
				book.bookSelect();
				break;
			case 4:
				book.bookAll();
				break;
			case 5:
				book.bookDelete();
				break;
			case 6:
				rr.rentalbook();
				break;
			case 7:
				rr.returnbook();
				break;
			case 8:
				rr.renew();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				
				return;
			default :
				System.out.println("잘못입력하셨습니다.");
				System.out.println("다시 입력해주세요~");
				
			}
		}

	}
}
