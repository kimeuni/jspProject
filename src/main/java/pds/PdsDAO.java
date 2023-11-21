package pds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.ComplaintVO;
import admin.review.ReviewVO;
import common.GetConn;

public class PdsDAO {
	private Connection conn = GetConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	PdsVO vo = null;
	
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

	// 자료실 전체(part) 리스트 (+ 글 올린지 얼마나 지났는지 "시간" 및 "날짜"체크)
	public ArrayList<PdsVO> getPdsList(String part, int pageSize, int startIndexNo) {
		ArrayList<PdsVO> vos = new ArrayList<PdsVO>();

		try {
			if(part.equals("전체")) {
				sql ="select *,datediff(fDate,now()) as day_diff, timestampdiff(hour,fDate,now()) as hour_diff from pds order by idx desc limit ?,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startIndexNo);
				pstmt.setInt(2, pageSize);
			}
			else {
				sql="select *,datediff(fDate,now()) as day_diff, timestampdiff(hour,fDate,now()) as hour_diff from pds where part=? order by idx desc limit ?,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, part);
				pstmt.setInt(2, startIndexNo);
				pstmt.setInt(3, pageSize);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new PdsVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setNickName(rs.getString("nickName"));
				vo.setfName(rs.getString("fName"));
				vo.setfSName(rs.getString("fSName"));
				vo.setfSize(rs.getInt("fSize"));
				vo.setTitle(rs.getString("title"));
				vo.setPart(rs.getString("part"));
				vo.setPwd(rs.getString("pwd"));
				vo.setfDate(rs.getString("fDate"));
				vo.setDownNum(rs.getInt("downNum"));
				vo.setOpenSw(rs.getString("openSw"));
				vo.setContent(rs.getString("content"));
				vo.setHostIp(rs.getString("hostIp"));
				
//				System.out.println("part : " + part);
				// 타입확인
//				if(part instanceof String) {
//					System.out.println(" String");
//				}

				vo.setDay_diff(rs.getInt("day_diff"));
				vo.setHour_diff(rs.getInt("hour_diff"));
				
				vos.add(vo);
			}
			
			
		} catch (SQLException e) {
			System.out.println("sql오류(자료실 전체 리스트) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 자료실에 전송된 내역을 등록시켜준다.
	public int setPdsInputOk(PdsVO vo) {
		int res = 0;
		try {
			sql="insert into pds values(default,?,?,?,?,?,?,?,?,default,default,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getNickName());
			pstmt.setString(3, vo.getfName());
			pstmt.setString(4, vo.getfSName());
			pstmt.setInt(5, vo.getfSize());
			pstmt.setString(6, vo.getTitle());
			pstmt.setString(7, vo.getPart());
			pstmt.setString(8, vo.getPwd());
			pstmt.setString(9, vo.getOpenSw());
			pstmt.setString(10, vo.getContent());
			pstmt.setString(11, vo.getHostIp());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql오류(자료실에 전송된 내역을 등록) : " + e.getMessage());
			e.printStackTrace();
		} finally {
			pstmtClose();
		}
		return res;
	}

	// idx검색처리... 1건자료 가져오기
	public PdsVO getPdsIdxSearch(int idx) {
		vo = new PdsVO();
		try {
			sql="select * from pds where idx=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setNickName(rs.getString("nickName"));
				vo.setfName(rs.getString("fName"));
				vo.setfSName(rs.getString("fSName"));
				vo.setfSize(rs.getInt("fSize"));
				vo.setTitle(rs.getString("title"));
				vo.setPart(rs.getString("part"));
				vo.setPwd(rs.getString("pwd"));
				vo.setfDate(rs.getString("fDate"));
				vo.setDownNum(rs.getInt("downNum"));
				vo.setOpenSw(rs.getString("openSw"));
				vo.setContent(rs.getString("content"));
				vo.setHostIp(rs.getString("hostIp"));
			}
		} catch (SQLException e) {
			System.out.println("sql오류(1건자료 가져오기) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 자료실의 파일 정보 삭제
	public String getPdsDeleteOk(int idx) {
		int res = 0;
		try {
			sql="delete from pds where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql오류(자료실의 파일 정보 삭제) : " + e.getMessage());
			e.printStackTrace();
		} finally {
			pstmtClose();
		}
		return res+"";
	}

	// 다운로드 수 1회씩 증가시키기
	public void setPdsDownNumCheck(int idx) {
		try {
			sql="update pds set downNum = downNum +1 where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql오류(다운로드 수 1회씩 증가) : " + e.getMessage());
			e.printStackTrace();
		} finally {
			pstmtClose();
		}
	}

	// 총 레코드 건수
	public int getTotRecCnt(String part) {
		int totRecCnt = 0;
		try {
			if(part.equals("전체")) {
				sql="select count(*) as cnt from pds";
				pstmt = conn.prepareStatement(sql);
			}
			else {
				sql="select count(*) as cnt from pds where part=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, part);
			}
			rs = pstmt.executeQuery();
			rs.next();
			
			totRecCnt = rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println("sql오류(총 레코드 건수) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return totRecCnt;
	}

	// 리뷰 내역 리스트 가져오기
	public ArrayList<ReviewVO> getReViewList(int idx, String part) {
		ArrayList<ReviewVO> rVOS = new ArrayList<ReviewVO>();
		try {
			sql ="select * from review where partIdx=? and part=? order by idx desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, part);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewVO rVO = new ReviewVO();
				rVO.setIdx(rs.getInt("idx"));
				rVO.setPart(rs.getString("part"));
				rVO.setPartIdx(rs.getInt("partIdx"));
				rVO.setMid(rs.getString("mid"));
				rVO.setStar(rs.getInt("star"));
				rVO.setContent(rs.getString("content"));
				rVO.setrDate(rs.getString("rDate"));
				
				rVOS.add(rVO);
				
			}
		} catch (SQLException e) {
			System.out.println("sql오류(리뷰 내역 리스트 가져오기) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return rVOS;
	}

	
}
