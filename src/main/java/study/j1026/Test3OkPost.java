package study.j1026;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Test3OkPost")
public class Test3OkPost extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); /* 요청 한글처리 */
		response.setContentType("text/html; charset=utf-8"); /* 응답 한글처리 */
		
		String name = request.getParameter("name"); 
		int age = Integer.parseInt(request.getParameter("age"));
		String flag = request.getParameter("flag");
		
		System.out.println("성명 : " +name);
		System.out.println("나이 : " +age);
		System.out.println("flag : " +flag);
		
		PrintWriter out = response.getWriter();
		
		out.println("<p>성명 : " +name + "</p>");
		out.println("<p>나이 : " +age + "</p>");
		out.println("<p>flag : " +flag + "</p>");
		out.println("<p><a href='/javaProject/study/1026/test3.jsp'>돌아가기</a></p>");
		/* 주소 반드시 잘 찾아갈 것! / /javaProject/study/1026/test3.jsp : 절대주소 지정한 것.. */
		out.println("<p><a href='"+request.getContextPath()+"/study/1026/test3.jsp'>돌아가기</a></p>");
		/* request.getContextPath() : 현재 사용하고 있는 ContextPath의 Root명(javaProject)을 불러온다. */
		out.println("<p><a href='"+request.getContextPath()+"/Test2'>Test2Servlet</a></p>");
		/* servlet은 경로명(파일명은 관계 없음(?))을 절대 같은 것으로 사용할 수 없다!!(다른파일에 있어도 < ex) j1025파일 혹은 j1026파일 > 불가능 */
	}

}
