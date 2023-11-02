package study.j1102;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/j1102/SessionOut")
public class SessionOut extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sw = request.getParameter("sw")== null ? "" : request.getParameter("sw");
		
		
		//세션 끊기
		session.removeAttribute("sLogoName");
		session.removeAttribute("sHomePage");
		
		if(!sw.equals("test4_init")) {
			response.sendRedirect(request.getContextPath()+"/study/1102_web_xml/test3_init.jsp");
		}
		else {
			response.sendRedirect(request.getContextPath()+"/study/1102_web_xml/test4_init.jsp");
		}
	}
}
