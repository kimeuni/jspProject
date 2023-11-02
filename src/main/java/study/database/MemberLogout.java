package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/database/memberLogout")
public class MemberLogout extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mid = (String)session.getAttribute("sMid"); // 출력을 위해 세션에 저장된 sMid값 저장.
		
		//세션값 삭제
		session.invalidate();
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('"+mid+"님 로그아웃 되셨습니다.')");
		out.println("location.href='"+request.getContextPath()+"/study/database/login.jsp'");
		out.println("</script>");
	}
}
