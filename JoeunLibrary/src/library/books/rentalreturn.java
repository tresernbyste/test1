package library.books;

import java.util.Scanner;

import library.connect.OracleConnect;

public class rentalreturn extends OracleConnect {
	Scanner scan = new Scanner(System.in);

	public rentalreturn() {
		super("library", "1234");
	}

	// 아이디 확인, 번호 확인
	// 책 수량이 0보다 큰경우에만 대여DB 등록
	// 대여DB 등록이 됐다면 책DB에 수량 적용시키기
	public void rentalbook() {

		System.out.println("대여자 아이디 : ");
		String id = scan.nextLine();

		try {
			String sql = "select id from member where id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			System.out.println("대여 책 번호 : ");
			String num = scan.nextLine();

			String sql1 = "select bookno from books where bookno=? ";
			psmt1 = con.prepareStatement(sql1);
			psmt1.setString(1, num);
			rs1 = psmt1.executeQuery();

			while (rs.next() && rs1.next()) {

//				System.out.println("====" + rs.getString("id"));
//				System.out.println("====" + rs1.getString("bookno"));

				if (rs1.getInt("bookamount") > 0) {

					try {

						String sql2 = " insert into rentalbook (rentalno,bookno,id) values(seq_rentalno.nextval, ?, ?) ";
						psmt2 = con.prepareStatement(sql2);
						psmt2.setString(1, rs1.getString("bookno"));
						psmt2.setString(2, rs.getString("id"));
						rs2 = psmt2.executeQuery();
						System.out.println("대여 확인 완료");
						int result2 = psmt2.executeUpdate();

						if (result2 == 1) {
							String sql3 = " update books set bookamount =  bookamount -1 where bookno = ? ";
							psmt3 = con.prepareStatement(sql3);
							psmt3.setString(1, rs1.getString("bookno"));
							rs3 = psmt3.executeQuery();
							System.out.println("대여 수량 적용");
						} else {
							System.out.println("대여 적용 실패");
						}

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("rentalDB insert 실패");
					}

				} else {
					System.out.println("수량이 부족합니다.");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			close();
		}

	}

	// 반납 아이디번호 확인 후 행 삭제
	public void returnbook() {
		System.out.println("대여자 아이디 : ");
		String id = scan.nextLine();
		System.out.println("대여 책 번호 : ");
		String num = scan.nextLine();
		try {
			String sql = " select * from rentalbook where id = ? and bookno = ? ";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, num);
			rs = psmt.executeQuery();

			while (rs.next()) {

				try {

					String sql2 = " delete from rentalbook where bookNo = ? and id= ? ";

					psmt = con.prepareStatement(sql2);
					psmt.setString(1, rs.getString("bookno"));
					psmt.setString(2, rs.getString("id"));
					rs1 = psmt.executeQuery();

					int rs1Num = psmt.executeUpdate();

					if (rs1Num == 1) {

						String sql3 = " update books set bookamount =  bookamount + 1 where bookno = ? ";
						psmt = con.prepareStatement(sql3);
						psmt.setString(1, rs.getString("bookno"));
						rs = psmt.executeQuery();
						System.out.println("대여 수량 적용");

					}
				} catch (Exception e) {
					System.out.println("rentalDB insert 실패");
					e.printStackTrace();
				}
			}
			System.out.println("반납완료!");
		}

		catch (

		Exception e) {
			e.printStackTrace();
		} finally {
//			close();
		}

	}

	public void renew() {

		System.out.println("대여자 아이디 : ");
		String id = scan.nextLine();
		System.out.println("대여 책 번호 : ");
		String booknum = scan.nextLine();

		try {

			String renew = " update rentalbook set returndate = returndate + 7 where bookno = ? and id = ? ";
			psmt = con.prepareStatement(renew);
			psmt.setString(1, booknum);
			psmt.setString(2, id);
			rs = psmt.executeQuery();

		}

		catch (

		Exception e) {
			System.out.println("연장 실패");
			e.printStackTrace();
		}

	}

}