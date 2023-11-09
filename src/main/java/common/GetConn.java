package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 싱글톤
public class GetConn {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/javaProject";
	private String user ="root";
	private String password ="1234";
	
	// 메소드 영역에 올림.
	private static GetConn instance = new GetConn();
	
	private GetConn() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 오류" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("데이터베이스 오류" + e.getMessage());
		}
	}
	
	// GetConn객체를 외부에서 연결해서 사용해주기 위한 메소드를 public 선언자로 만들어준다.
	public Connection getConn() {
		return conn;
	}
	
	public static GetConn getInstance() {
		return instance;
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
}
