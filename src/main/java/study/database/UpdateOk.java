package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/database/updateOk")
public class UpdateOk extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");
		String name = request.getParameter("name")== null ? "" : request.getParameter("name");
		
		LoginDAO dao = new LoginDAO();
		PrintWriter out = response.getWriter();
		LoginVO vo = null;
		
		// 비밀번호 체크하기...
		vo = dao.getLoginChaeck(mid, pwd);
		
		// 비밀번호가 맞으면 성명만 수정이 가능함...
		if(vo.getMid() == null) {
			out.println("<script>");
			out.println("alert('비밀번호를 확인하세요.')");
			out.println("location.href='"+request.getContextPath()+"/study/database/update.jsp'");
			out.println("</script>");
			return;
		}
		vo = new LoginVO();
		vo.setMid(mid);
//		vo.setPwd(pwd);
		vo.setName(name);
		
		int res = dao.setUpdateOk(mid,name);
		
		if(res != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("sName", name);
			out.println("<script>");
			out.println("alert('"+mid+"님 정보수정처리 되었습니다.')");
			out.println("location.href='"+request.getContextPath()+"/study/database/memberMain.jsp'");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('정보수정처리에 실패하였습니다.')");
			out.println("location.href='"+request.getContextPath()+"/study/database/memberMain.jsp'");
			out.println("</script>");
		}
	}
}
