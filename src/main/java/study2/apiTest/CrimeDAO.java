package study2.apiTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.ComplaintVO;
import admin.review.ReviewVO;
import common.GetConn;

public class CrimeDAO {
	private Connection conn = GetConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	CrimeVO vo = null;
	
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

	// 범죄 데이터 저장
	public void saveCrimeData(CrimeVO vo) {
		try {
			sql="insert into crime values(default,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getYear());
			pstmt.setString(2, vo.getPolice());
			pstmt.setInt(3, vo.getRobbery());
			pstmt.setInt(4, vo.getMurder());
			pstmt.setInt(5, vo.getTheft());
			pstmt.setInt(6, vo.getViolence());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql문법 오류(범죄 데이터 저장) : "+ e.getMessage());
		} finally {
			pstmtClose();
		}
	}

	// 범죄 년도 검색
	public int getSearchYear(int year) {
		int res = 0;
		try {
			sql = "select * from crime where year = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, year);
			rs = pstmt.executeQuery();
			if(rs.next()) res=1;
		} catch (SQLException e) {
			System.out.println("sql문법 오류(범죄 년도 검색) : "+ e.getMessage());
		} finally {
			rsClose();
		}
		return res;
	}

	// 범죄 데이터 삭제
	public String deleteCrimeData(int year) {
		String str ="삭제 실패";
		try {
			sql ="delete from crime where year=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, year);
			pstmt.executeUpdate();
			str = "1";
		} catch (SQLException e) {
			System.out.println("sql문법 오류(범죄 데이터 삭제) : "+ e.getMessage());
		} finally {
			pstmtClose();
		}
		return str;
	}

	// 년도별 범죄 건수 구하기 (전체)
	public ArrayList<CrimeVO> getAllListCrimeData(int year) {
		ArrayList<CrimeVO> vos = new ArrayList<CrimeVO>();
		try {
			sql="select * from crime where year =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, year);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new CrimeVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setYear(rs.getInt("year"));
				vo.setPolice(rs.getString("police"));
				vo.setRobbery(rs.getInt("robbery"));
				vo.setMurder(rs.getInt("murder"));
				vo.setTheft(rs.getInt("theft"));
				vo.setViolence(rs.getInt("violence"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("sql문법 오류(년도별 범죄 건수 구하기 (전체)) : "+ e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// DB에 있는 모든 년도에 대한 범죄 건수 합계 및 평균 구하기
	public CrimeVO getAllAvgCrimeData(int year) {
		vo = new CrimeVO();
		try {
			//sql문은 api.sql에도 존재하니 확인해볼 것.
			sql ="select year,sum(robbery)as totRobbery,sum(murder)as totMurder,sum(theft)as totTheft,sum(violence)as totViolence,"
					+ "	avg(robbery)as avgRobbery,avg(murder)as avgMurder,avg(theft)as avgTheft,avg(violence)as avgViolence"
					+ "	 from crime where year =?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, year);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setYear(rs.getInt("year"));
				vo.setTotRobbery(rs.getInt("totRobbery"));
				vo.setTotMurder(rs.getInt("totMurder"));
				vo.setTotTheft(rs.getInt("totTheft"));
				vo.setTotViolence(rs.getInt("totViolence"));
				vo.setAvgRobbery(rs.getInt("avgRobbery"));
				vo.setAvgMurder(rs.getInt("avgMurder"));
				vo.setAvgTheft(rs.getInt("avgTheft"));
				vo.setAvgViolence(rs.getInt("avgViolence"));
			}
		} catch (SQLException e) {
			System.out.println("sql문법 오류(DB에 있는 모든 년도에 대한 범죄 건수 합계 및 평균 구하기) : "+ e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// DB에 있는 경찰서별에 대한 범죄 건수 합계 및 평균 구하기
	public CrimeVO getPoliceAvgCrimeData(String police) {
		vo = new CrimeVO();
		try {
			//sql문은 api.sql에도 존재하니 확인해볼 것.
			sql ="select year,sum(robbery)as totRobbery,sum(murder)as totMurder,sum(theft)as totTheft,sum(violence)as totViolence,"
					+ "	avg(robbery)as avgRobbery,avg(murder)as avgMurder,avg(theft)as avgTheft,avg(violence)as avgViolence"
					+ "	 from crime where police like ?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, "%"+police+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setYear(rs.getInt("year"));
				vo.setTotRobbery(rs.getInt("totRobbery"));
				vo.setTotMurder(rs.getInt("totMurder"));
				vo.setTotTheft(rs.getInt("totTheft"));
				vo.setTotViolence(rs.getInt("totViolence"));
				vo.setAvgRobbery(rs.getInt("avgRobbery"));
				vo.setAvgMurder(rs.getInt("avgMurder"));
				vo.setAvgTheft(rs.getInt("avgTheft"));
				vo.setAvgViolence(rs.getInt("avgViolence"));
			}
		} catch (SQLException e) {
			System.out.println("sql문법 오류(DB에 있는 모든 년도에 대한 범죄 건수 합계 및 평균 구하기) : "+ e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 년도별 범죄 건수 구하기 (경찰서별)
	public ArrayList<CrimeVO> getPoliceListCrimeData(String police) {
		ArrayList<CrimeVO> vos = new ArrayList<CrimeVO>();
		try {
			sql="select * from crime where police like ?  ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+police+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new CrimeVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setYear(rs.getInt("year"));
				vo.setPolice(rs.getString("police"));
				vo.setRobbery(rs.getInt("robbery"));
				vo.setMurder(rs.getInt("murder"));
				vo.setTheft(rs.getInt("theft"));
				vo.setViolence(rs.getInt("violence"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("sql문법 오류(년도별 범죄 건수 구하기 (경찰서별)) : "+ e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// DB에 있는 경찰서별+년도별에 대한 범죄 건수 합계 및 평균 구하기
	public CrimeVO getPoliceYearAvgCrimeData(String police, int year) {
		vo = new CrimeVO();
		try {
			//sql문은 api.sql에도 존재하니 확인해볼 것.
			sql ="select year,sum(robbery)as totRobbery,sum(murder)as totMurder,sum(theft)as totTheft,sum(violence)as totViolence,"
					+ "	avg(robbery)as avgRobbery,avg(murder)as avgMurder,avg(theft)as avgTheft,avg(violence)as avgViolence"
					+ "	 from crime where police like ? and year =?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, "%"+police+"%");
			pstmt.setInt(2, year);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setYear(rs.getInt("year"));
				vo.setTotRobbery(rs.getInt("totRobbery"));
				vo.setTotMurder(rs.getInt("totMurder"));
				vo.setTotTheft(rs.getInt("totTheft"));
				vo.setTotViolence(rs.getInt("totViolence"));
				vo.setAvgRobbery(rs.getInt("avgRobbery"));
				vo.setAvgMurder(rs.getInt("avgMurder"));
				vo.setAvgTheft(rs.getInt("avgTheft"));
				vo.setAvgViolence(rs.getInt("avgViolence"));
			}
		} catch (SQLException e) {
			System.out.println("sql문법 오류(DB에 있는 경찰서별+년도별에 대한 범죄 건수 합계 및 평균 구하기) : "+ e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 년도별 범죄 건수 구하기 (경찰서별+년도)
	public ArrayList<CrimeVO> getPoliceYearListCrimeData(String police, int year) {
		ArrayList<CrimeVO> vos = new ArrayList<CrimeVO>();
		try {
			sql="select * from crime where police like ? and year=?  ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+police+"%");
			pstmt.setInt(2, year);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new CrimeVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setYear(rs.getInt("year"));
				vo.setPolice(rs.getString("police"));
				vo.setRobbery(rs.getInt("robbery"));
				vo.setMurder(rs.getInt("murder"));
				vo.setTheft(rs.getInt("theft"));
				vo.setViolence(rs.getInt("violence"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("sql문법 오류(년도별 범죄 건수 구하기 (경찰서별)) : "+ e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}
	
	
}
