package study2.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.lo")
public class LoginController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginInterface command = null;
		String viewPage = "/WEB-INF/study2/login";
		
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		if(com.equals("/login")) {
			viewPage += "/login.jsp";
		}
		else if(com.equals("/join")) {
			viewPage += "/join.jsp";
		}
		else if(com.equals("/loginOk")) {
			//가져갈게 있으면 command객체에서 가져가, 값을 뿌려야 한다.
			command = new LoginOkCommand();
			command.execute(request, response);
			
			
			viewPage = "/include/msg.jsp";
		}
		else if(com.equals("/memberMain")) {
			
			viewPage += "/memberMain.jsp";
		}
		else if(com.equals("/joinOk.lo")) {
			command = new JoinOkCommand();
			command.execute(request, response);
			
			viewPage += "/login.jsp";
		}
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
