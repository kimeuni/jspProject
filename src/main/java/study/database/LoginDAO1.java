package study.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LoginDAO1 {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private DataSource dataFactory;
	
	String sql = "";
	
	private LoginVO vo = null;
	
	// DAO객체의 생성과 동시에 DB 접속처리 한다.
	/*
	public LoginDAO() {
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
	*/
	
	// DBCP 사용...
	public LoginDAO1() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("dbcp_mysql");
		} catch (Exception e) {
			e.printStackTrace();
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

	// 로그인 처리(loginOk.java)  //비밀번호 맞는지 체크(updateOK.java)
	public LoginVO getLoginChaeck(String mid, String pwd) {
		vo = new LoginVO();
		try {
			
			conn = dataFactory.getConnection();
			
			sql="select * from login where mid=? and pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setPoint(rs.getInt("point"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setTodayCount(rs.getInt("todayCount"));
			}
		} catch (SQLException e) {
			System.out.println("sql구문 오류(로그인처리) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	//전체 조회 처리
	public ArrayList<LoginVO> getLoginList() {
		ArrayList<LoginVO> vos = new ArrayList<LoginVO>();
		try {
			
			sql="select * from login order by name";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new LoginVO(); // vo를 생성해야 덮어씌우지 않고 담는다.
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setPoint(rs.getInt("point"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setTodayCount(rs.getInt("todayCount"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("sql구문 오류(전체조회) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	//개별조회
	public LoginVO getLoginSearch(String sid) {
		vo = new LoginVO();
		try {
			sql="select * from login where mid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setPoint(rs.getInt("point"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setTodayCount(rs.getInt("todayCount"));
			}
		} catch (SQLException e) {
			System.out.println("sql구문 오류(전체조회) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 변경된 내용을 DB에 Update처리
	public void setLoginUpdate(LoginVO vo) {
		try {
			sql = "update login set point=?, lastDate=now(), todayCount=?  where mid = ?";  //lastDate=now()를해서 최종 접속일을 로그인한 시점으로 업데이트를한다.
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPoint());
			pstmt.setInt(2, vo.getTodayCount());
			pstmt.setString(3, vo.getMid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql구문 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}

	// 회원가입 처리
	public int setJoinOk(LoginVO vo) {
		int res = 0;
		try {
			sql="insert into login values (default,?,?,?,default,default,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql구문 오류(전체조회) : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		
		return res;
	}

	// 회원 탈퇴 처리
	public int setDeleteOk(String mid) {
		int res = 0;
		try {
			sql = "delete from login where mid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql구문 오류(전체조회) : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	public int setUpdateOk(String mid, String name) {
		int res = 0;
		try {
			sql="update login set name=? where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, mid);
			res =pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql구문 오류(전체조회) : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
}
