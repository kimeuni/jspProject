package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.SecurityUtil;

public class MemberPwdCheckOkCommand implements memberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");
		HttpSession session = request.getSession();
		String mid = (String)session.getAttribute("sMid");
		
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(pwd);
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberMidCheck(mid);
		
		// 아이디가 null이 아니고 vo.getPwd와 입력받은 pwd가 같으면....
		if(vo.getMid() != null && vo.getPwd().equals(pwd)) {
			request.setAttribute("msg", "NO");
			request.setAttribute("url", "memberUpdateForm.mem?mid="+mid);
		}
		else {
			request.setAttribute("msg", "비밀번호가 틀립니다. 확인하세요.");
			request.setAttribute("url", "memberMain.mem");
		}
	}

}
