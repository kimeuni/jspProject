package study.j1102;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/j1102/test3Ok")
public class Test3Ok  extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이곳은 Test3Ok servlet 입니다.");
		
		String logoName = getServletContext().getInitParameter("logoName"); /* getServletContext().getInitParameter("logoName")에 적은 "logoName"변수는 web.xml에 적은 변수명이다. */
		String homeAddress = getServletContext().getInitParameter("homeAddress");
		
		HttpSession session = request.getSession();
		session.setAttribute("sLogoName", logoName);
		session.setAttribute("sHomePage", homeAddress);
	
		String viewPage = "/study/1102_web_xml/test3_init.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
