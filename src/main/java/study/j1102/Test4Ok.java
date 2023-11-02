package study.j1102;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/j1102/test4Ok", initParams= {@WebInitParam(name="logoName",value = "그린 자바 주식회사"),@WebInitParam(name="homeAddress",value = "http://192.168.50.61:9090/javaProject/study/1102_web_xml/test1.jsp")})  //어노테이션을 이용한 초기값 설정
public class Test4Ok  extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이곳은 Test3Ok servlet 입니다.");

		String logoName = getInitParameter("logoName");
		String homeAddress = getInitParameter("homeAddress");
		
		
		HttpSession session = request.getSession();

		session.setAttribute("sLogoName", logoName);
		session.setAttribute("sHomePage", homeAddress);
	
		String viewPage = "/study/1102_web_xml/test4_init.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
