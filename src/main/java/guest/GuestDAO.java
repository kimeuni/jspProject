package guest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuestDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	String sql="";
	GuestVO vo = null;
	
	public GuestDAO() {
		String url="jdbc:mysql://localhost:3306/javaProject";
		String user="root";
		String password="1234";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패" + e.getMessage());
		}
	}
	
	// 사용된 객체를 반납(conn, pstmt, rs)
	public void connClose() {
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {}
		}
	}
	public void pstmtClose() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {}
		}
	}
	public void rsClose() {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {}
		}
		pstmtClose();
	}

	// 방명록 리스트
	public ArrayList<GuestVO> getGuestList() {
		ArrayList<GuestVO> vos = new ArrayList<GuestVO>();
		try {
			sql="select * from guest order by idx desc"; //내림차순
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new GuestVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setVisitDate(rs.getString("visitDate").substring(0, 16));
				vo.setHostIp(rs.getString("hostIp"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류(getGuestList) : " +e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 방문 글작성
	public int setContentW(String name, String email, String homeP, String cont, String hIp) {
		int res =0;
		try {
			sql="insert into guest (name,content,email,homePage,hostIp) value (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, cont);
			pstmt.setString(3, email);
			pstmt.setString(4, homeP);
			pstmt.setString(5, hIp);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류(getGuestList) : " +e.getMessage());
		} finally {
			pstmtClose();
		}
		
		return res;
	}

	// 작성한 글 리스트 삭제
	public int setListDelete(String idx) {
		int res = 0;
		try {
			sql="delete from guest where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류(getGuestList) : " +e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
}
