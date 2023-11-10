package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.SecurityUtil;

public class MemberPwdChangeOkCommand implements memberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pwd1 = request.getParameter("pwd1")== null ? "" : request.getParameter("pwd1");
		String pwd2 = request.getParameter("pwd2")== null ? "" : request.getParameter("pwd2");
		
		HttpSession session = request.getSession();
		String mid =(String)session.getAttribute("sMid");
		
		MemberDAO dao = new MemberDAO();
		
		//비밀번호 암호화
		SecurityUtil security = new SecurityUtil();
		String pwd = security.encryptSHA256(pwd2);
		
		String res = dao.setMemberPwdChange(pwd,mid);
		
		response.getWriter().write(res);
	}

}
