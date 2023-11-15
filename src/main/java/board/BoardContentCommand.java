package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardContentCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")== null ? 0 : Integer.parseInt(request.getParameter("idx"));
		int pageSu = request.getParameter("pageSu")== null ? 1 : Integer.parseInt(request.getParameter("pageSu"));
		int pageSize = request.getParameter("pageSize")== null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
		
		// 수정페이지에서 돌아가기 버튼을 눌렀을 때 넘길 값
		String flag = request.getParameter("flag")== null ? "" : request.getParameter("flag");
		String search = request.getParameter("search")== null ? "" : request.getParameter("search");
		String searchString = request.getParameter("searchString")== null ? "" : request.getParameter("searchString");
		
		BoardDAO dao = new BoardDAO();
		
		// 게시글 가져오기전에 조회수 1증가시키기 (update)  (미리 update를 시키고 밑에서 읽어야 들어가자마자 조회수가 올라간다)
		// dao.setBoardReadNumPlus(idx); ==> 이것만 적으면, 새로고침을 했을 시에도 조회수가 올라가기 때문에 문제가 발생한다.
		
		
		HttpSession session = request.getSession();
		String mid = session.getAttribute("sMid")== null ? "" : (String)session.getAttribute("sMid");
		
		// 조회수 증가 처리 (숙제 /집에서 한 거/23-11-14)
		/*
		int res = 0;
		String[] sReadCheckMid1;
		String checkMid ="";
		int checkIdx = 0;
		
		String sReadCheckMid = session.getAttribute("sReadCheckMid")==null ? "" : (String)session.getAttribute("sReadCheckMid");
		System.out.println("sReadCheckMid " + sReadCheckMid);
		if(!sReadCheckMid.equals("") && sReadCheckMid != null) {
			sReadCheckMid1 = sReadCheckMid.split("/");
			checkMid = sReadCheckMid1[0];
			checkIdx = Integer.parseInt(sReadCheckMid1[1]);
		}
		
		if(sReadCheckMid.equals("") || sReadCheckMid == null || checkIdx != idx || !checkMid.equals(mid)) {
			dao.setBoardReadNumPlus(idx);
			session.setAttribute("sReadCheckMid", mid + "/"+ idx);
			res = 1;
		}
		else {
			if(checkMid.equals(mid) && checkIdx == idx) {
				res = 0;
			}
		}
		*/
		
		// 게시글 조회수 1 증가시키기(중복불허/23-11-15 학원에서..)
		ArrayList<String> boardContentIdx = (ArrayList)session.getAttribute("sBoardContentIdx");
		//처음 들어가면 세션이 없기 때문에 세션을 만든다.
		if(boardContentIdx == null) {  
			boardContentIdx = new ArrayList<String>();
		}
		String imsiContentIdx = "board" + idx; //만약 자료실을 처리하고 싶으면 "pds" + idx 이런식으로 하면 된다
		// imsiContentIdx의 값이 있는지 없는지 확인하기 위해 contains로 비교(indexOf로 찾아도 상관 없음)
		if(!boardContentIdx.contains(imsiContentIdx)) {
			// 게시글의 값이 안들어가 있으면..(게시글에 처음 들어감)  DB에서 조회수 값을 올린다. 
			dao.setBoardReadNumPlus(idx);
			boardContentIdx.add(imsiContentIdx); // ArrayList에 추가해준다.
		}
		// ArrayList에 추가된 것을 세션에 담는다.
		session.setAttribute("sBoardContentIdx", boardContentIdx);
//		System.out.println("sBoardContentIdx" + boardContentIdx);
		
		BoardVO vo =dao.getBoardContent(idx);
		
		request.setAttribute("vo", vo);
		request.setAttribute("pageSu", pageSu);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("flag", flag);
		request.setAttribute("search", search);
		request.setAttribute("searchString", searchString);
		
		// 이전글, 다음글 처리
		BoardVO preVo = dao.getPreNextSearch(idx, "preVo"); //이전글 //프리비어스 넥스트 서치..
		BoardVO nextVo = dao.getPreNextSearch(idx, "nextVo"); //다음글
		
//		System.out.println("preVo" + preVo);
//		System.out.println("nextVo" + nextVo);
		
		request.setAttribute("preVo", preVo);
		request.setAttribute("nextVo", nextVo);
	}

}
