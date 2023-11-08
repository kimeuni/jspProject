package study2.ajax2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	String sql = "";
	
	private UserVO vo = null;
	
	// DAO객체의 생성과 동시에 DB 접속처리 한다.
	public UserDAO() {
		String url= "jdbc:mysql://localhost:3306/javaProject";
		String user= "root";
		String password ="1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패~");
		}
	}
	
	// 사용한 객체의 반납
	public void connClose() {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {}
	}
	
	// pstmt 객체 반납
	public void pstmtClose() {
		try {
			if(pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {}
	}
	
	// rs 객체 반납
	public void rsClose() {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
			finally {
				pstmtClose();
			}
		}
	}

	//전체 조회 처리
	public ArrayList<UserVO> getUserList() {
		ArrayList<UserVO> vos = new ArrayList<UserVO>();
		try {
			sql="select * from user order by idx desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new UserVO(); // vo를 생성해야 덮어씌우지 않고 담는다.
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setAddress(rs.getString("address"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("sql구문 오류(전체조회) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 아이디 중복확인
	public String getMidSearch(String mid) {
		String res = "0";
		try {
			sql = "select * from user where mid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if(!rs.next()) {  // 값이 없으면...
				res = "1";
			}
		} catch (SQLException e) {
			System.out.println("sql구문 오류(아이디 중복확인) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return res;
	}

	// 회원가입 처리
	public String setUserInputOk(UserVO vo) {
		String res = "0";
		try {
			sql = "insert into user values (default,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getName());
			pstmt.setInt(3, vo.getAge());
			pstmt.setString(4, vo.getAddress());
			pstmt.executeUpdate();
			res ="1"; // 모든 수행이 완료된다면 res에 "1"을 보낸다 (회원가입 처리 완료됨)
		} catch (SQLException e) {
			System.out.println("sql구문 오류(회원가입 처리) : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// idx로 검색 (개별검색)
	public UserVO getIdxSearch(int idx) {
		UserVO vo = new UserVO();
		try {
			sql = "select * from user where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println("sql구문 오류(개별검색) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 유저 정보 수정
	public String setUserUpdate(UserVO vo) {
		String res = "0";
		try {
			sql ="update user set name=?, age=?, address=? where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getAge());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getMid());
			pstmt.executeUpdate();
			res ="1";
		} catch (SQLException e) {
			System.out.println("sql구문 오류(정보수정) : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 유저 삭제
	public String setUserDel(int idx) {
		String res ="0";
		try {
			sql="delete from user where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			res = "1";
		} catch (SQLException e) {
			System.out.println("sql구문 오류(삭제처리) : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
}
