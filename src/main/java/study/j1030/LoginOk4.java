package study.j1030;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/j1030/loginOk4")
public class LoginOk4 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		String nick = request.getParameter("nick")==null ? "" : request.getParameter("nick");
		String mSw = "";
		String viewPage = "";
		
		// DB에 가서 회원인증처리한다. 이곳에서는 아이디=admin, 비밀번호=1234 로 체크한다.
		if(mid.equals("admin") && pwd.equals("1234") || mid.equals("hkd1234") && pwd.equals("1234")) {
			mSw = "Ok";
		}
		else {
			mSw = "No";
		}
		
		response.sendRedirect(request.getContextPath()+"/study/1030/main4.jsp?mid="+mid+"&mSw="+mSw+"&nick="+nick);
		
	}
}
