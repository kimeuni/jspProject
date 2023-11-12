package member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import guest.GuestDAO;
import guest.GuestVO;

public class MemberMainCommand implements memberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String mid = (String) session.getAttribute("sMid");
		
		MemberDAO mDAO = new MemberDAO();
		MemberVO mVO = mDAO.getMemberMidCheck(mid);
		
	
		GuestDAO gDAO = new GuestDAO();
		
		ArrayList<GuestVO> gVOS = gDAO.getGuestLists();
		
		request.setAttribute("mVO", mVO);
		request.setAttribute("gVOS", gVOS);
	}

}
