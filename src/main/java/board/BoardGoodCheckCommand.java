package board;

import java.io.IOException;

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
		
		// 좋아요 수 증가처리
		int res = dao.setBoardGoodCheck(idx);
		
		
		response.getWriter().write(res+"");
		
	}

}
