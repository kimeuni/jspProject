package schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.ComplaintVO;
import admin.review.ReviewVO;
import common.GetConn;

public class ScheduleDAO {
	private Connection conn = GetConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	ScheduleVO vo = null;
	
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

	public ArrayList<ScheduleVO> getScheduleList(String mid, String ym, int sw) {
		ArrayList<ScheduleVO> vos = new ArrayList<ScheduleVO>();
		try {
			if(sw == 0) {
//				sql ="select * from schedule where mid=? and date_format(sDate, '%Y-%m') = ? order by sDate, part";
				sql ="select *,count(*) as partCnt from schedule where mid=? and date_format(sDate, '%Y-%m')=? group by sDate,part order by sDate, part;";
			}
			else if (sw == 1) {
				sql ="select * from schedule where mid=? and date_format(sDate, '%Y-%m-%d') = ? order by sDate";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, ym);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new ScheduleVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPart(rs.getString("part"));
				vo.setsDate(rs.getString("sDate"));
				vo.setContent(rs.getString("content"));
				
				//전체 달력을 볼때 꺼내야하기 때문에 sw 0일경우만 vo에 저장해서 사용
				if(sw==0) vo.setPartCnt(rs.getString("partCnt"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 일정 등록처리
	public int setScheduleInputOk(ScheduleVO vo) {
		int res = 0;
		try {
			sql ="insert into schedule values (default,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getsDate());
			pstmt.setString(3, vo.getPart());
			pstmt.setString(4, vo.getContent());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류(일정 등록처리) : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 일정 삭제 처리
	public int setScheduleDeleteOk(int idx) {
		int res = 0;
		try {
			sql = "delete from schedule where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류(일정 삭제처리) : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 일정 수정 처리
	public int setScheduleUpdateOk(ScheduleVO vo) {
		int res = 0;
		try {
			sql="update schedule set part=?,content=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPart());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getIdx());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류(일정 수정처리) : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

}
