package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.ComplaintVO;
import common.GetConn;

public class BoardDAO {
	private Connection conn = GetConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	BoardVO vo = null;
	
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

	// 게시판 리스트 전체 조회
	public ArrayList<BoardVO> getBoardList(int startIndexNo, int pageSize) {
		ArrayList<BoardVO> vos = new ArrayList<BoardVO>();
		try {
			// hour_diff 는 timeStampDiff를 담기 위한 변수명..  /  hour를 사용하면 현재시간(now()) - wDate를 뺀 시간이 나온다.. / timeStampDiff를 적은 이유는 게시판에 24시간 안에 적은 글은 new 이미지를 띄우기 위해서이다.)
			sql = "select *,timeStampDiff(hour,wDate,now()) as hour_diff, datediff(wDate,now()) as date_diff, "
					+ "(select count(*) from boardReply where boardIdx=board.idx) as replyCnt "
					+ "from board order by idx desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startIndexNo);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new BoardVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setNickName(rs.getString("nickName"));
				vo.setTitle(rs.getString("title"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setContent(rs.getString("content"));
				vo.setReadNum(rs.getInt("readNum"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setOpenSw(rs.getString("openSw"));
				vo.setwDate(rs.getString("wDate"));
				vo.setGood(rs.getInt("good"));
				
				vo.setHour_diff(rs.getString("hour_diff"));
				vo.setDate_diff(rs.getString("date_diff"));
				vo.setReplyCnt(rs.getInt("replyCnt"));;
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("sql구문 오류(게시판 리스트)" + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 게시판 글쓰기 작성 글 DB에 저장
	public int setBoardInputOk(BoardVO vo) {
		int res = 0;
		try {
			sql = "insert into board values (default,?,?,?,?,?,?,default,?,?,default,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getNickName());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getHomePage());
			pstmt.setString(6, vo.getContent());
			pstmt.setString(7, vo.getHostIp());
			pstmt.setString(8, vo.getOpenSw());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql구문 오류(글쓰기 작성 글 DB에 저장)" + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 전체 게시글의 건 수(레코드) 구해오기
	public int getTotRecCnt() {
		int totRecCnt =0;
		try {
			sql = "select count(*) as cnt from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			
			totRecCnt = rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println("sql구문 오류(전체 게시글의 건 수(레코드) 구해오기)" + e.getMessage());
		} finally {
			rsClose();
		}
		return totRecCnt;
	}

	// 게시물 1건 상세보기
	public BoardVO getBoardContent(int idx) {
		vo = new BoardVO();
		try {
			sql = "select * from board where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setNickName(rs.getString("nickName"));
				vo.setTitle(rs.getString("title"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setContent(rs.getString("content"));
				vo.setReadNum(rs.getInt("readNum"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setOpenSw(rs.getString("openSw"));
				vo.setwDate(rs.getString("wDate"));
				vo.setGood(rs.getInt("good"));
			}
		} catch (SQLException e) {
			System.out.println("sql구문 오류(전체 게시글의 건 수(레코드))" + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 게시글 조회수 1씩 증가시키기
	public void setBoardReadNumPlus(int idx) {
		try {
			sql="update board set readNum= readNum+1 where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql구문 오류(게시글 조회수 1씩 증가)" + e.getMessage());
		} finally {
			pstmtClose();
		}
		
	}

	// 좋아요 수 1씩 증가 (중복 불허)
	public int setBoardGoodCheck(int idx) {
		int res = 0;
		try {
			sql="update board set good = good + 1 where idx=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res =pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql구문 오류(좋아요 수 1씩 증가)" + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
	
	// 좋아요수 1씩 증가시키기..(중복허용)
	public void setBoardGoodCheckPlus(int idx) {
		try {
			sql = "update board set good = good + 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}
	
	// 좋아요수 1씩 감소시키기..(중복허용)
	public void setBoardGoodCheckMinus(int idx) {
		try {
			sql = "update board set good = good - 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}

	// 게시글 수정 처리
	public int setBoardUpdateOk(BoardVO vo) {
		int res = 0;
		try {
			sql = "update board set title=?,email=?,homePage=?,content=?,openSw=?, hostIp=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getHomePage());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getOpenSw());
			pstmt.setString(6, vo.getHostIp());
			pstmt.setInt(7, vo.getIdx());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql구문 오류(게시글 수정 처리)" + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 게시물 삭제처리
	public int setBoardDelete(int idx) {
		int res = 0;
		try {
			sql = "delete from board where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql구문 오류(게시물 삭제처리)" + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 게시판 검색기를 활용한 검색자료 추출처리
	public ArrayList<BoardVO> getBoardContentSearch(String search, String searchString) {
		ArrayList<BoardVO> vos = new ArrayList<BoardVO>();
		try {
			sql ="select *,timeStampDiff(hour,wDate,now()) as hour_diff from board where "+search+" like ? order by idx desc"; // ?는 값으로 보기 때문에, where ? = ? 로 적을 수 없다.. 그래서 "+ search +" 이런식으로 적어줘서 직접 들어온 변수값을 읽을 수 있도록 한다.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+ searchString + "%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new BoardVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setNickName(rs.getString("nickName"));
				vo.setTitle(rs.getString("title"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setContent(rs.getString("content"));
				vo.setReadNum(rs.getInt("readNum"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setOpenSw(rs.getString("openSw"));
				vo.setwDate(rs.getString("wDate"));
				vo.setGood(rs.getInt("good"));
				
				vo.setHour_diff(rs.getString("hour_diff"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("sql구문 오류(게시판 검색기)" + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 이전글, 다음글 처리 
	public BoardVO getPreNextSearch(int idx, String str) {
		vo = new BoardVO();
		try {
			//이전글
			if(str.equals("preVo")) { 
				sql="select idx,title from board where idx < ? order by idx desc limit 1";
			}
			//다음글
			else { 
				sql="select idx,title from board where idx > ? order by idx limit 1";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setTitle(rs.getString("title"));
			}
		} catch (SQLException e) {
			System.out.println("sql구문 오류(게시판 검색기)" + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 댓글 읽어오기 (원본글 idx에 해당하는 댓글을 읽어온다.)
	public ArrayList<BoardReplyVO> getBoardReply(int idx) {
		ArrayList<BoardReplyVO> replyVOS = new ArrayList<BoardReplyVO>();
		try {
			// BoardListCommand.java에서 board의 idx를 넘겼는데.. board의 idx는 boardReply의 boardIdx와 같기 때문에 해당 해당 게시글의 댓글을 읽어올 수 있음
			sql = "select * from boardReply where boardIdx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardReplyVO replyVO = new BoardReplyVO();
				replyVO.setIdx(rs.getInt("idx"));
				replyVO.setBoardIdx(rs.getInt("boardIdx"));
				replyVO.setMid(rs.getString("mid"));
				replyVO.setNickName(rs.getString("nickName"));
				replyVO.setwDate(rs.getString("wDate"));
				replyVO.setHostIp(rs.getString("hostIp"));
				replyVO.setContent(rs.getString("content"));
				replyVOS.add(replyVO);
			}
		} catch (SQLException e) {
			System.out.println("sql구문 오류(댓글 읽어오기)" + e.getMessage());
		} finally {
			rsClose();
		}
		return replyVOS;
	}

	// 댓글 작성 후 DB 저장처리
	public int setReplyInputOk(BoardReplyVO replyVO) {
		int res = 0;
		try {
			sql = "insert into boardReply values(default,?,?,?,default,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyVO.getBoardIdx());
			pstmt.setString(2, replyVO.getMid());
			pstmt.setString(3, replyVO.getNickName());
			pstmt.setString(4, replyVO.getHostIp());
			pstmt.setString(5, replyVO.getContent());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql구문 오류(댓글 작성 후 DB 저장처리)" + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 댓글 삭제 처리
	public int getBoardReplyDeleteOk(int idx) {
		int res = 0;
		try {
			sql = "delete from boardReply where idx=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql구문 오류( 댓글 삭제 처리)" + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 신고된 게시판 저장(숙제)
	public int setComplaintInsert(ComplaintVO vo) {
		int res = 0;
		try {
			sql = "insert into complaint values(default,?,?,?,?,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPart());
			pstmt.setInt(2, vo.getPartIdx());
			pstmt.setString(3, vo.getCpMid());
			pstmt.setString(4, vo.getCpContent());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql구문 오류(신고된 게시판 저장)" + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 신고된 리스트 출력(숙제)
	public ArrayList<ComplaintVO> getComplaintList() {
		ArrayList<ComplaintVO> vos = new ArrayList<ComplaintVO>();
		try {
			sql="select *,timeStampDiff(hour,cpDate,now()) as hour_diff, datediff(cpDate,now()) as date_diff from complaint order by idx desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ComplaintVO cVO = new ComplaintVO();
				cVO.setIdx(rs.getInt("idx"));
				cVO.setPart(rs.getString("part"));
				cVO.setPartIdx(rs.getInt("partIdx"));
				cVO.setCpMid(rs.getString("cpMid"));
				cVO.setCpContent(rs.getString("cpContent"));
				cVO.setCpDate(rs.getString("cpDate"));
				
				cVO.setHour_diff(rs.getString("hour_diff"));
				cVO.setDate_diff(rs.getString("date_diff"));
				
				vos.add(cVO);
			}
		} catch (SQLException e) {
			System.out.println("sql구문 오류(신고된 리스트 출력)" + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}
	
}
