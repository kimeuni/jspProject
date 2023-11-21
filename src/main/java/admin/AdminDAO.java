package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.ComplaintVO;
import admin.review.ReviewVO;
import common.GetConn;

public class AdminDAO {
	private Connection conn = GetConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	// pstmt 객체 반납
	public void pstmtClose() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {}
		}
	}
	
	// rs 객체 반납
	public void rsClose() {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}
		pstmtClose();
	}

	// 리뷰 등록
	public int setReviewInputOk(ReviewVO vo) {
		int res = 0;
		try {
			sql ="insert into review values (default,?,?,?,?,?,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPart());
			pstmt.setInt(2, vo.getPartIdx());
			pstmt.setString(3, vo.getMid());
			pstmt.setInt(4, vo.getStar());
			pstmt.setString(5, vo.getContent());
			res =pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql 오류(리뷰 등록)" + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
	
}
