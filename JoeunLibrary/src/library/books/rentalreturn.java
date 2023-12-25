package library.books;

import java.util.Scanner;

import library.connect.OracleConnect;

public class rentalreturn extends OracleConnect {
	Scanner scan = new Scanner(System.in);

	public rentalreturn() {
		super("library", "1234");
	}

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

			String sql1 = "select * from books where bookno=? ";
			psmt = con.prepareStatement(sql1);
			psmt.setString(1, num);
			rs1 = psmt.executeQuery();

			while (rs.next() && rs1.next()) {

				System.out.println("====" + rs.getString("id"));
				System.out.println("====" + rs1.getString("bookno"));

				if (rs1.getInt("bookamount") > 0) {

					try {

						String sql2 = " insert into rentalbook (rentalno,bookno,id) values(seq_rentalno.nextval, ?, ?) ";
						psmt = con.prepareStatement(sql2);
						psmt.setString(1, rs1.getString("bookno"));
						psmt.setString(2, rs.getString("id"));
						rs2 = psmt.executeQuery();
						System.out.println("대여 확인 완료");

						while (rs2.next()) {
							String sql3 = " update books set bookamount =  bookamount -1 where bookno = ? ";
							psmt = con.prepareStatement(sql3);
							psmt.setString(1, rs1.getString("bookno"));
							rs3 = psmt.executeQuery();
							System.out.println("대여 수량 적용");
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
		}

	}

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
//
//					String sql2 = " delete from rentalbook where bookNo = ? and id= ? ";
//
//					psmt = con.prepareStatement(sql2);
//					psmt.setString(1, rs.getString("bookno"));
//					psmt.setString(2, rs.getString("id"));
//					rs1 = psmt.executeQuery();
//
//					while (rs1.next()) {
					
						String sql3 = " update books set bookamount =  bookamount + 1 where bookno = ? ";
						psmt = con.prepareStatement(sql3);
						psmt.setString(1, rs.getString("bookno"));
						rs = psmt.executeQuery();
						System.out.println("대여 수량 적용");
				

				} catch (Exception e) {
					System.out.println("rentalDB insert 실패");
					e.printStackTrace();
				}

			}
		}

		catch (

		Exception e) {
			e.printStackTrace();
		}

	}
	
	public void renew() {
		
		// 반납할 책 번호를 입력해주세요
		// update rentalbook set returndate = returndate + 7 where book no =  ? ;
		// int renewNum =  psmt.excuteupdate();  == 1
		// while(renewNum==1){
		//   if ( returndate 
		
		
		
		
		
		
		
	}

}