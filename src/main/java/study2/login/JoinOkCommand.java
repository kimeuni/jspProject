package study2.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JoinOkCommand implements LoginInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");
		String name = request.getParameter("name")== null ? "" : request.getParameter("name");
		
		LoginDAO dao = new LoginDAO();
		LoginVO vo = new LoginVO();
		
		vo.setMid(mid);
		vo.setPwd(pwd);
		vo.setName(name);
		
		int res = dao.setJoinOk(vo);
		
		if(res != 0) {
			request.setAttribute("msg", "회원가입 되었습니다.");
			request.setAttribute("url", "login.lo");
		}
		else {
			request.setAttribute("msg", "회원가입에 실패하였습니다.");
			request.setAttribute("url", "join.lo");
		}
		
	}

}
