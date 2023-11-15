package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardGoodCheckCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")== null ? 0 : Integer.parseInt(request.getParameter("idx"));
		HttpSession session = request.getSession();
		String mid = session.getAttribute("sMid")==null ? "" : (String)session.getAttribute("sMid");
		
		BoardDAO dao = new BoardDAO();
		
		// 좋아요 수 증가처리 (중복허용x / 숙제 / 집에서 한거 / 23-11-14)
		int res = 0;
		/*
		String[] sGoodCheckMid1;
		String checkMid = "";
		int checkIdx = 0;
		
		String sGoodCheckMid = session.getAttribute("sGoodCheckMid")==null ? "" : (String)session.getAttribute("sGoodCheckMid");
		if(!sGoodCheckMid.equals("") && sGoodCheckMid != null) {
			sGoodCheckMid1 = sGoodCheckMid.split("/");
			checkMid = sGoodCheckMid1[0];
			checkIdx = Integer.parseInt(sGoodCheckMid1[1]);
		}
		
		if(sGoodCheckMid.equals("") || sGoodCheckMid == null || checkIdx != idx || !checkMid.equals(mid)) {
			res = dao.setBoardGoodCheck(idx);
			session.setAttribute("sGoodCheckMid", mid + "/"+ idx);
//			System.out.println(sGoodCheckMid);
		}
		else {
			if(checkMid.equals(mid) && checkIdx == idx) {
				res = 0;
			}
			
		}
		*/
		
		//좋아요 수 증가처리 (중복불허 / 학원 / 23-11-15)  (자세한 설명은 BoardContentCommand.java 참고)
		ArrayList<String> goodIdxObj = (ArrayList)session.getAttribute("sGoodIdxObj");
		if(goodIdxObj == null) {
			goodIdxObj = new ArrayList<String>();
		}
		String imsiGoodIdxObj = "boardGood" + idx;
		if(!goodIdxObj.contains(imsiGoodIdxObj)) {
			res = dao.setBoardGoodCheck(idx);
			goodIdxObj.add(imsiGoodIdxObj);
		}
		session.setAttribute("sGoodIdxObj", goodIdxObj);
		
		
		response.getWriter().write(res+"");
		
	}

}
