package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.mem")
public class memberController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberInterface command =  null;
		String viewPage = "/WEB-INF/member";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/"),com.lastIndexOf("."));
		
		if(com.equals("/memberJoin")) {
			viewPage += "/memberJoin.jsp";
		}
		else if(com.equals("/memberIdCheck")) {
			command = new MemberIdCheckCommand();
			command.execute(request, response);
			
			viewPage += "/memberIdCheck.jsp";
		}
		else if(com.equals("/memberNickCheck")) {
			command = new MemberNickCheckCommand();
			command.execute(request, response);
			
			viewPage += "/memberNickCheck.jsp";
		}
		else if(com.equals("/memberJoinOk")) {
			command = new MemberJoinOkCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		else if(com.equals("/memberLogin")) {
			viewPage += "/memberLogin.jsp";
		}
		else if(com.equals("/memberLoginOk")) {
			command = new MemberLoginOkCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		else if(com.equals("/memberMain")) {
			//command = new MemberMainCommand();
			//command.execute(request, response);
			viewPage += "/memberMain.jsp";
		}
		else if(com.equals("/memberLogoutOk")) {
			command = new MemberLogoutOkCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		else if(com.equals("/memberFindMid")) {
			viewPage += "/memberFindMid.jsp";
		}
		else if(com.equals("/memberFindMidOk")) {
			command = new MemberFindMidOkCommand();
			command.execute(request, response);
			
			return;
		}
		else if(com.equals("/memberFindPwd")) {
			viewPage += "/memberFindPwd.jsp";
		}
		else if(com.equals("/memberFindPwdOk")) {
			command = new MemberFindPwdOkCommand();
			command.execute(request, response);
			
			return;
		}
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
