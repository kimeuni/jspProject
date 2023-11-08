package study2.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// 확장자 패턴 .lo 컨트롤러?

@WebServlet("*.lo")
public class LoginController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginInterface command = null;
		String viewPage = "/WEB-INF/study2/login";
		
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		// login  view보내기
		if(com.equals("/login")) {
			viewPage += "/login.jsp";
		}
		// login 처리 하기 (+ 메세지 띄우기)
		else if(com.equals("/loginOk")) {
			//가져갈게 있으면 command객체에서 가져가, 값을 뿌려야 한다.
			command = new LoginOkCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		// join   view보내기
		else if(com.equals("/join")) {
			viewPage += "/join.jsp";
		}
		// join 회원가입 처리하기
		else if(com.equals("/joinOk")) {
			command = new JoinOkCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		// Main화면 view 띄우기
		else if(com.equals("/memberMain")) {
			
			viewPage += "/memberMain.jsp";
		}
		// update 정보수정 view 띄우기
		else if(com.equals("/update")) {
			viewPage += "/update.jsp";
		}
		// update 정보수정 처리하기
		else if(com.equals("/updateOk")) {
			command = new UpdateOkCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		// 로그아웃 처리
		else if(com.equals("/memberLogout")) {
			command = new MemberLogoutCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		// 회원 탈퇴 처리
		else if(com.equals("/deleteOk")) {
			command = new DeleteOkCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		// memberList 전체출력 처리
		else if(com.equals("/memberList")) {
			command = new MemberListCommand();
			command.execute(request, response);
			
			viewPage += "/memberList.jsp";
		}
		else if(com.equals("/ex_memberSer")) {
			command = new Ex_memberSerCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		else if(com.equals("/memberMainSearch")) {
			command = new MemberMainSearchCommand();
			command.execute(request, response);
			
			viewPage += "/memberMain.jsp";
		}
		
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
