package guest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/guest/adminLogout")
public class AdminLogout extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// 전체삭제 (session.invalidate();)하면 안됨! (나중에 회원가입 만들건데 전체 세션 삭제하면 그 자료들도 전부 삭제되기 때문에)
		
		session.removeAttribute("sAdmin");
		
		/* response.sendRedirect(request.getContextPath()+"/GuestList"); */
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('관리자 로그아웃 되었습니다.')");
		out.println("location.href='"+request.getContextPath()+"/GuestList';");
		out.println("</script>");
	}
}
