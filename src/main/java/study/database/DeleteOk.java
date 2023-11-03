package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/database/deleteOk")
public class DeleteOk extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mid = (String)session.getAttribute("sMid");  //세션값 읽어오기
		
		LoginDAO dao = new LoginDAO();
		
		int res = dao.setDeleteOk(mid);
		
		PrintWriter out = response.getWriter();
		if(res != 0) {
			session.invalidate(); //세션 데이터 삭제
			dao.connClose(); // dao연결 끊기
			out.println("<script>");
			out.println("alert('"+mid+"님 탈퇴처리 되었습니다.')");
			out.println("location.href='"+request.getContextPath()+"/study/database/login.jsp'");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('탈퇴처리에 실패하였습니다.')");
			out.println("location.href='"+request.getContextPath()+"/study/database/memberMain.jsp'");
			out.println("</script>");
		}
	}
}
