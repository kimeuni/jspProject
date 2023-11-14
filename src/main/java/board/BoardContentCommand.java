package board;

import java.io.IOException;

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
		
		BoardDAO dao = new BoardDAO();
		
		// 게시글 가져오기전에 조회수 1증가시키기 (update)  (미리 update를 시키고 밑에서 읽어야 들어가자마자 조회수가 올라간다)
		// dao.setBoardReadNumPlus(idx); ==> 이것만 적으면, 새로고침을 했을 시에도 조회수가 올라가기 때문에 문제가 발생한다.
		
		
		HttpSession session = request.getSession();
		String mid = session.getAttribute("sMid")== null ? "" : (String)session.getAttribute("sMid");
		
		int res = 0;
		String[] sReadCheckMid1;
		String checkMid ="";
		int checkIdx = 0;
		System.out.println("idx :" + idx);
		System.out.println("mid :" + mid);
		
		// 조회수 증가 처리
		String sReadCheckMid = session.getAttribute("sReadCheckMid")==null ? "" : (String)session.getAttribute("sReadCheckMid");
		System.out.println("sReadCheckMid " + sReadCheckMid);
		if(!sReadCheckMid.equals("") && sReadCheckMid != null) {
			sReadCheckMid1 = sReadCheckMid.split("/");
			checkMid = sReadCheckMid1[0];
			checkIdx = Integer.parseInt(sReadCheckMid1[1]);
		}
		
		if(sReadCheckMid.equals("") || sReadCheckMid == null || checkIdx != idx) {
			dao.setBoardReadNumPlus(idx);
			session.setAttribute("sReadCheckMid", mid + "/"+ idx);
			System.out.println(sReadCheckMid);
			res = 1;
		}
		else {
			if(checkMid.equals(mid) && checkIdx == idx) {
				res = 0;
			}
		}
		
		BoardVO vo =dao.getBoardContent(idx);
		
		request.setAttribute("vo", vo);
		request.setAttribute("pageSu", pageSu);
		request.setAttribute("pageSize", pageSize);
	}

}
