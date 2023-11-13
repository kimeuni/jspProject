package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MInforCommand implements memberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")== null ? 10 : Integer.parseInt(request.getParameter("idx"));
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberidxInforCheck(idx);
		
		String strLevel = "";
		if(vo.getLevel() == 0) strLevel = "관리자";
		if(vo.getLevel() == 1) strLevel = "준회원";
		if(vo.getLevel() == 2) strLevel = "정회원";
		if(vo.getLevel() == 3) strLevel = "우수회원";
		
		request.setAttribute("level", vo.getLevel());
		request.setAttribute("vo", vo);
		request.setAttribute("strLevel", strLevel);

	}

}
