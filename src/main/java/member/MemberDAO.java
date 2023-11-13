package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.GetConn;

// common 패키지에 들어있는 Getconn을 이용한 싱글톤 사용
public class MemberDAO {
//	GetConn getConn = GetConn.getInstance(); // 이렇게 적어놓으면 싱글톤 객체 호출해서 사용 가능...
//	private Connection conn = getConn.getConn();
	
	private Connection conn = GetConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql ="";
	
	MemberVO vo = null;
	
	// pstmt 객체 반납
	public void pstmtClose() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {}
		}
	}
	// rs 객체 반납
	public void rsClose() {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {}
		}
		pstmtClose();
	}

	// member 아이디 체크
	public MemberVO getMemberMidCheck(String mid) {
		vo = new MemberVO();
		try {
			sql = "select * from member where mid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setNickName(rs.getString("nickName"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirthday(rs.getString("birthday"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setJob(rs.getString("job"));
				vo.setHobby(rs.getString("hobby"));
				vo.setPhoto(rs.getString("photo"));
				vo.setContent(rs.getString("content"));
				vo.setUserInfor(rs.getString("userInfor"));
				vo.setUserDel(rs.getString("userDel"));
				vo.setPoint(rs.getInt("point"));
				vo.setLevel(rs.getInt("level"));
				vo.setVisitCnt(rs.getInt("visitCnt"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setTodayCnt(rs.getInt("todayCnt"));
			}
		} catch (SQLException e) {
			System.out.println("SQL문 오류(아이디 중복 체크) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}
	// 닉네임 중복체크
	public MemberVO getMemberNickCheck(String nickName) {
		vo = new MemberVO();
		try {
			sql = "select * from member where nickName=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setNickName(rs.getString("nickName"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirthday(rs.getString("birthday"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setJob(rs.getString("job"));
				vo.setHobby(rs.getString("hobby"));
				vo.setPhoto(rs.getString("photo"));
				vo.setContent(rs.getString("content"));
				vo.setUserInfor(rs.getString("userInfor"));
				vo.setUserDel(rs.getString("userDel"));
				vo.setPoint(rs.getInt("point"));
				vo.setLevel(rs.getInt("level"));
				vo.setVisitCnt(rs.getInt("visitCnt"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setTodayCnt(rs.getInt("todayCnt"));
			}
		} catch (SQLException e) {
			System.out.println("SQL문 오류(닉네임 중복 체크) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}
	// 회원가입 처리
	public int setMemberJoinOk(MemberVO vo) {
		int res = 0;
		try {
			sql ="insert into member values (default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,default,default,default,default,default,default,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getNickName());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getBirthday());
			pstmt.setString(7, vo.getTel());
			pstmt.setString(8, vo.getAddress());
			pstmt.setString(9, vo.getEmail());
			pstmt.setString(10, vo.getHomePage());
			pstmt.setString(11, vo.getJob());
			pstmt.setString(12, vo.getHobby());
			pstmt.setString(13, vo.getPhoto());
			pstmt.setString(14, vo.getContent());
			pstmt.setString(15, vo.getUserInfor());
			res =pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL문 오류(회원가입 처리) : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
	
	// 이메일 검색(아이디찾기)
	public String getMemberEmailSearch(String email) {
		String res = "";
        
        try {
            sql = "select mid from member where email = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                res += rs.getString("mid") + "/";
            }
            if(res != "") res = res.substring(0,res.length()-1);
            if(res == "") res = "0";
            System.out.println("res " +res);
        } catch (SQLException e) {
            System.out.println("SQL 구문 오류(아이디 찾기) : " + e.getMessage());
        } finally {
            rsClose();
        }
        
        return res;
	}
	
	// 아이디 이메일 확인(비밀번호 찾기)
	public String getmemberMidEmailCheck(String mid, String email) {
		String res = "0";
		try {
			sql = "select * from member where mid=? and email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if(rs.next()) res = "1"; // 만약ㄱ 값이 있으면 res를 "1"로 보낸다.
			System.out.println(res);
		} catch (SQLException e) {
			System.out.println("SQL문 오류(비밀번호 찾기) : " + e.getMessage());
			e.getStackTrace();
		} finally {
			rsClose();
		}
		return res;
	}
	
	// 임시비밀번호 데이터베이스에 저장
	public int setImsiMemberPwd(String mid, String email, String pwd) {
		int re = 0;
		try {
			sql="update member set pwd=? where mid=? and email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, mid);
			pstmt.setString(3, email);
			re = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL문 오류(비밀번호 찾기) : " + e.getMessage());
			e.getStackTrace();
		} finally {
			pstmtClose();
		}
		return re;
	}
	
	//회원 정보 수정
	public int setMemberUpdateOk(MemberVO vo) {
		int res = 0;
		try {
			sql = "update member set nickName=?, name=?, gender=?, birthday=?,"
					+ "tel=?, address=?, email=?, homePage=?, job=?, hobby=?,"
					+ "photo=?, content=?,userInfor=? where mid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getNickName());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getBirthday());
			pstmt.setString(5, vo.getTel());
			pstmt.setString(6, vo.getAddress());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getHomePage());
			pstmt.setString(9, vo.getJob());
			pstmt.setString(10, vo.getHobby());
			pstmt.setString(11, vo.getPhoto());
			pstmt.setString(12, vo.getContent());
			pstmt.setString(13, vo.getUserInfor());
			pstmt.setString(14, vo.getMid());
			res =pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL문 오류(회원정보 수정) : " + e.getMessage());
			e.getStackTrace();
		} finally {
			pstmtClose();
		}
		return res;
	}
	
	// 비밀번호 수정
	public String setMemberPwdChange(String pwd, String mid) {
		String res = "0";
		try {
			sql="update member set pwd=? where mid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, mid);
			pstmt.executeUpdate();
			res="1";
		} catch (SQLException e) {
			System.out.println("SQL문 오류(비밀번호 수정) : " + e.getMessage());
			e.getStackTrace();
		} finally {
			pstmtClose();
		}
		return res;
	}
	
	// 전체 멤버 리스트 (페이징 처리 o)
	public ArrayList<MemberVO> getMemberListPageing(int startIndexNo, int pageSize) {
		ArrayList<MemberVO> vos = new ArrayList<MemberVO>();
		try {
			sql = "select * from member order by idx desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startIndexNo);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new MemberVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setNickName(rs.getString("nickName"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirthday(rs.getString("birthday"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setJob(rs.getString("job"));
				vo.setHobby(rs.getString("hobby"));
				vo.setPhoto(rs.getString("photo"));
				vo.setContent(rs.getString("content"));
				vo.setUserInfor(rs.getString("userInfor"));
				vo.setUserDel(rs.getString("userDel"));
				vo.setPoint(rs.getInt("point"));
				vo.setLevel(rs.getInt("level"));
				vo.setVisitCnt(rs.getInt("visitCnt"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setTodayCnt(rs.getInt("todayCnt"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL문 오류(전체 멤버 리스트) : " + e.getMessage());
			e.getStackTrace();
		} finally {
			rsClose();
		}
		return vos;
	}
	
	// 회원 등급 변경
	public int setMemberLevelChange(int idx, int level) {
		int res = 0;
		try {
			sql="update member set level = ? where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, level);
			pstmt.setInt(2, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL문 오류(회원 등급 변경) : " + e.getMessage());
			e.getStackTrace();
		} finally {
			rsClose();
		}
		return res;
	}
	
	// 전체 레코드 수 구하기
	public int getMemberTotRecode() {
		int totRecode = 0;
		try {
			sql="select count(*) as cnt from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();

			totRecode = rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println("SQL문 오류(전체 레코드 수) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return totRecode;
	}
	
	// 회원 전체리스트 (페이징처리 x)
	public ArrayList<MemberVO> getMemberList() {
		ArrayList<MemberVO> vos = new ArrayList<MemberVO>();
		try {
			sql = "select * from member order by idx desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new MemberVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setNickName(rs.getString("nickName"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirthday(rs.getString("birthday"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setJob(rs.getString("job"));
				vo.setHobby(rs.getString("hobby"));
				vo.setPhoto(rs.getString("photo"));
				vo.setContent(rs.getString("content"));
				vo.setUserInfor(rs.getString("userInfor"));
				vo.setUserDel(rs.getString("userDel"));
				vo.setPoint(rs.getInt("point"));
				vo.setLevel(rs.getInt("level"));
				vo.setVisitCnt(rs.getInt("visitCnt"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setTodayCnt(rs.getInt("todayCnt"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL문 오류(전체 멤버 리스트) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}
	
	// 등급별 리스트 출력 (+ 전체화면 출력도 포함)
	public ArrayList<MemberVO> getMemberLevelSearch(int level, int startIndexNo, int pageSize) {
		ArrayList<MemberVO> vos = new ArrayList<MemberVO>();
		try {
			if(level > 4) {  // 레벨이 4를 초과하면 전체 회원 리스트를 불러온다.
				sql = "select *, timestampdiff(day,lastDate,now()) as deleteDiff from member order by idx desc limit ?,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startIndexNo);
				pstmt.setInt(2, pageSize);
			}
			else {  //그게 아니라면 level에 있는 것을 불러온다..
				sql = "select *, timestampdiff(day,lastDate,now()) as deleteDiff from member where level = ? order by idx desc limit ?,? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, level);
				pstmt.setInt(2, startIndexNo);
				pstmt.setInt(3, pageSize);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setNickName(rs.getString("nickName"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirthday(rs.getString("birthday"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setJob(rs.getString("job"));
				vo.setHobby(rs.getString("hobby"));
				vo.setPhoto(rs.getString("photo"));
				vo.setContent(rs.getString("content"));
				vo.setUserInfor(rs.getString("userInfor"));
				vo.setUserDel(rs.getString("userDel"));
				vo.setPoint(rs.getInt("point"));
				vo.setLevel(rs.getInt("level"));
				vo.setVisitCnt(rs.getInt("visitCnt"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setTodayCnt(rs.getInt("todayCnt"));
				
				vo.setDeleteDiff(rs.getInt("deleteDiff"));
				
				vos.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL문 오류(등급별 리스트 출력+전체 출력) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}
	
	// 등급별 레코드 수 (전체 레코드 수)
	public int getMemberLevelTotRecode(int level) {
		int totRecode = 0;
		try {
			if(level > 4) {  //레벨이 4를 초과하면 전체 레코드 수를 불러온다.
				sql="select count(*) as cnt from member";
				pstmt = conn.prepareStatement(sql);
			}
			else {
				sql="select count(*) as cnt from member where level=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, level);
			}
			rs = pstmt.executeQuery();
			rs.next();

			totRecode = rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println("SQL문 오류(등급별 레코드 수(+전체 레코드 수)) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return totRecode;
	}
	
	// 총 방문일, 오늘 방문일, 포인트 정보 업데이트
	public void setLoginUpdate(MemberVO vo) {
		try {
			sql="update member set todayCnt=?,visitCnt=?, point=?, lastDate=now(), level=? where mid=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getTodayCnt());
			pstmt.setInt(2, vo.getVisitCnt());
			pstmt.setInt(3, vo.getPoint());
			pstmt.setInt(4, vo.getLevel());
			pstmt.setString(5, vo.getMid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL문 오류(총 방문일, 오늘 방문일, 포인트 정보 업데이트) : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}
	
	// 회원 탈퇴 신청 (userDel필드의 값을 NO -> Ok로 변경처리)
	public int setMemberDeleteCheck(String mid) {
		int res = 0;
		try {
			sql ="update member set userDel='OK' where mid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL문 오류(회원 탈퇴 신청) : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		
		return res;
	}
	
	// 탈퇴신청 30일 경과후 정보 삭제
	public void setMemberDeleteOk(int idx) {
		try {
			sql ="delete from member where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL문 오류(DB 회원 탈퇴 처리) : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		
	}
	
	// 개인 회원 infor 가져오기
	public MemberVO getMemberidxInforCheck(int idx) {
		MemberVO vo = new MemberVO();
		try {
			sql = "select * from member where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setNickName(rs.getString("nickName"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirthday(rs.getString("birthday"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setJob(rs.getString("job"));
				vo.setHobby(rs.getString("hobby"));
				vo.setPhoto(rs.getString("photo"));
				vo.setContent(rs.getString("content"));
				vo.setUserInfor(rs.getString("userInfor"));
				vo.setUserDel(rs.getString("userDel"));
				vo.setPoint(rs.getInt("point"));
				vo.setLevel(rs.getInt("level"));
				vo.setVisitCnt(rs.getInt("visitCnt"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setTodayCnt(rs.getInt("todayCnt"));
			}
		} catch (SQLException e) {
			System.out.println("SQL문 오류(개인 회원 infor) : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}
	
	
}
