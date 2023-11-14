package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.member.MemberLevelChangeCommand;
import admin.member.MemberLevelSearchCommand;
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
		
		// 이런식으로하면 메인화면에 데이터베이스를 불러와 꾸밀 수 있다 
		if(com.equals("/main")) {
			command = new MainCommand();
			command.execute(request, response);
			
			viewPage = "/WEB-INF/main/main.jsp";
		}
		// 관리자가 아니면 들어오지 못하도록 처리..
		else if(level >0) {
			request.getRequestDispatcher("/").forward(request, response);
		}
		// 어드민 화면 메인 view에 띄우기
		else if(com.equals("/adminMain")) {
			viewPage += "/adminMain.jsp";
		}
		// 어드민 화면 헤더 view에 띄우기
		else if(com.equals("/adminHeader")) {
			viewPage += "/adminHeader.jsp";
		}
		// 어드민 화면 왼쪽(메뉴) view에 띄우기
		else if(com.equals("/adminLeft")) {
			viewPage += "/adminLeft.jsp";
		}
		// // 어드민 화면 Content 데이터 처리 후 view띄우기
		else if(com.equals("/adminContent")) {
			command = new AdminContentCommand();
			command.execute(request, response);
			
			viewPage += "/adminContent.jsp";
		}
		//멤버 전체 리스트를 어드민에서 만들지, 멤버에서 만들지 잘 생각하고 만들어볼 것.
		else if(com.equals("/adminMemberList")) {
			// 이거 만들필요없이 adminMemberLevelSearch.ad 에서 전체,등급별 전부 처리했기 때문에 
			// adminMemberLevelSearch을 불러서 사용하면 된다.. 일딴 지우지 않고 남겨놓음..  -2023-11-13-
			command = new MemberListCommand();
			command.execute(request, response);
			viewPage += "/member/adminMemberList.jsp";
		}
		// 어드민 화면에서 회원별 등급 수정해주기
		else if(com.equals("/adminMemberLevelChange")) {
			command = new MemberLevelChangeCommand();
			command.execute(request, response);
			return;
		}
		// 어드민 화면에서 멤버 전체출력 및 등급별 출력
		else if(com.equals("/adminMemberLevelSearch")) {
			command = new MemberLevelSearchCommand();
			command.execute(request, response);
			viewPage += "/member/adminMemberList.jsp";
		}
		// 어드민 화면에서 회원 개인별 정보 보기
		else if(com.equals("/adminMemberInfor")) {
			command = new AdminMemberInforCommand();
			command.execute(request, response);
			viewPage += "/member/adminMemberInfor.jsp";
		}
		
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
