package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberListCommand;

@SuppressWarnings("serial")
@WebServlet("*.ad")
public class adminController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		adminInterface command =  null;
		String viewPage = "/WEB-INF/admin";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/"),com.lastIndexOf("."));
		
		HttpSession session = request.getSession();
		int level = session.getAttribute("sLevel")==null ? 99 : (int) session.getAttribute("sLevel");
		
		// 관리자가 아니면 들어오지 못하도록 처리..
		if(level >0) {
			request.getRequestDispatcher("/").forward(request, response);
		}
		else if(com.equals("/adminMain")) {
			viewPage += "/adminMain.jsp";
		}
		else if(com.equals("/adminLeft")) {
			viewPage += "/adminLeft.jsp";
		}
		else if(com.equals("/adminContent")) {
			viewPage += "/adminContent.jsp";
		}
		//멤버 전체 리스트를 어드민에서 만들지, 멤버에서 만들지 잘 생각하고 만들어볼 것.
		else if(com.equals("/adminMemberList")) {
			command = new MemberListCommand();
			command.execute(request, response);
			viewPage += "/member/adminMemberList.jsp";
		}
		else if(com.equals("/adminMemberLevelChange")) {
			command = new MemberLevelChangeCommand();
			command.execute(request, response);
			return;
		}
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
