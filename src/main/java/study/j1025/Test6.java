package study.j1025;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Test6")
public class Test6 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");   // setContentType : 브라우저의 출력되는 방식은   "text/html: text형식과 html형식 / charset=UTF-8 : UTF-8 형식이다.  
		
		PrintWriter out = response.getWriter();
		out.println("이곳은 서블릿에서 보냅니다.<br/>");
		out.println("<h3>Welcome to Servlet!!</h3>");
	}
}
