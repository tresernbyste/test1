package library.member;

import java.util.Scanner;

import library.connect.OracleConnect;

public class memberjoin extends OracleConnect {

	public memberjoin() {

		super("library", "1234");
	}
	
	
	// 회원 등록
	public void excute() {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.print("ID : ");
			String id = scan.nextLine();

			String sql = "select * from member where id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();
			int nResult = 0;

			while (rs.next()) {
				nResult++;
				rs.getString("id");
				System.out.println(rs.getString("id") + "는 이미 사용중인 ID입니다.");
				System.out.println("");
			}

			if (nResult == 0) {
				System.out.println(id + "는 사용 가능한 ID입니다.");
				System.out.print("Password : ");
				String pw = scan.nextLine();
				System.out.print("성함 : ");
				String name = scan.nextLine();
				
				
			
				String sql1 = "insert into member values(seq_num.nextval, ?, ?, ?)";
				psmt = con.prepareStatement(sql1);
				psmt.setString(1, id);
				psmt.setString(2, pw);
				psmt.setString(3, name);

				int updateCount = psmt.executeUpdate();
				if (updateCount == 1) {
					System.out.println("회원가입이 정상적으로 처리 되었습니다.");
				} else {
					System.out.println("데이터 입력에 실패했습니다.(#가입오류)");

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("입력에 실패했습니다.(#3)");
		}
	}
}
