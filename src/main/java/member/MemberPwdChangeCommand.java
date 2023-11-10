package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.SecurityUtil;

public class MemberPwdChangeCommand implements memberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");
		HttpSession session = request.getSession();
		String mid = (String)session.getAttribute("sMid");
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberMidCheck(mid);
		
		SecurityUtil security = new SecurityUtil();
		pwd =security.encryptSHA256(pwd);
		
		String res = "0";
		if(vo.getMid() != null && vo.getPwd().equals(pwd)) {
			res= "1";
			response.getWriter().write(res);
//			System.out.println("통과1");
		}
		else {
			response.getWriter().write(res);
//			System.out.println("통과0");
		}
		
	}

}
