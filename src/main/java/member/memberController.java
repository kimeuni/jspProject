package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.mem")
public class memberController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberInterface command =  null;
		String viewPage = "/WEB-INF/member";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/"),com.lastIndexOf("."));
		
		HttpSession session = request.getSession();
		int level = session.getAttribute("sLevel")==null ? 99 : (int) session.getAttribute("sLevel");
		
		
		// 회원가입(Join)화면 view
		if(com.equals("/memberJoin")) {
			viewPage += "/memberJoin.jsp";
		}
		// 회원가입시, 아이디 중복 체크
		else if(com.equals("/memberIdCheck")) {
			command = new MemberIdCheckCommand();
			command.execute(request, response);
			
			viewPage += "/memberIdCheck.jsp";
		}
		// 회원가입시, 닉네임 중복체크
		else if(com.equals("/memberNickCheck")) {
			command = new MemberNickCheckCommand();
			command.execute(request, response);
			
			viewPage += "/memberNickCheck.jsp";
		}
		// 회원가입 가입 처리
		else if(com.equals("/memberJoinOk")) {
			command = new MemberJoinOkCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		// 로그인 View 화면
		else if(com.equals("/memberLogin")) {
			viewPage += "/memberLogin.jsp";
		}
		// 로그인 확인처리
		else if(com.equals("/memberLoginOk")) {
			command = new MemberLoginOkCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		// 로그인 화면에서 <아이디찾기>를 눌렀을 시 나오는 View화면
		else if(com.equals("/memberFindMid")) {
			viewPage += "/memberFindMid.jsp";
		}
		// <아이디 찾기> 내용 처리 (ajax)
		else if(com.equals("/memberFindMidOk")) {
			command = new MemberFindMidOkCommand();
			command.execute(request, response);
			
			return;
		}
		// 로그인 화면에서 <비밀번호 찾기>를 눌렀을 시 나오는 View화면
		else if(com.equals("/memberFindPwd")) {
			viewPage += "/memberFindPwd.jsp";
		}
		// <비밀번호 찾기> 내용 처리 (ajax)
		else if(com.equals("/memberFindPwdOk")) {
			command = new MemberFindPwdOkCommand();
			command.execute(request, response);
			
			return;
		}
		//비회원인 경우 (세션이 끊어진 경우) 홈으로 보낸다. 
		// (맨 위에 안 적은 이유는, 맨 위는 session 연결이 안되어 있어도 들어갈 수 있는 화면들이기 때문이다.)
		else if(level >4) {  
			request.getRequestDispatcher("/").forward(request, response);
		}
		// 로그아웃 내용 처리하기
		else if(com.equals("/memberLogoutOk")) {
			command = new MemberLogoutOkCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		// 로그인 후, 회원전용 메인화면 View 및 보낼 내용 처리하기
		else if(com.equals("/memberMain")) {
			command = new MemberMainCommand();
			command.execute(request, response);
			viewPage += "/memberMain.jsp";
		}
		// 
		else if(com.equals("/memberPwdCheck")) {
			viewPage += "/memberPwdCheck.jsp";
		}
		else if(com.equals("/memberPwdCheckOk")) {
			command = new MemberPwdCheckOkCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		else if(com.equals("/memberUpdateForm")) {
			command = new MemberUpdateFormCommand();
			command.execute(request, response);
			viewPage += "/memberUpdateForm.jsp";
		}
		else if(com.equals("/memberUpdateOk")) {
			command = new MemberUpdateOkCommand();
			command.execute(request, response);
			viewPage = "/include/msg.jsp";
		}
		else if(com.equals("/memberPwdChange")) {
			command = new MemberPwdChangeCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/memberPwdChangeOk")) {
			command = new MemberPwdChangeOkCommand();
			command.execute(request, response);
			return;
		}
		// 회원 탈퇴 신청 처리
		else if(com.equals("/memberDeleteCheck")) {
			command = new MemberDeleteCheckCommand();
			command.execute(request, response);
			
			return;
		}
		// 회원탈퇴신청 후 30일경과하여 데이터베이스에서 삭제처리
		else if(com.equals("/memberDeleteOk")) {
			command = new MemberDeleteOkCommand();
			command.execute(request, response);
			
			return;
		}
		// 회원이 볼 수 있는 멤버 전체 리스트
		else if(com.equals("/mList")) {
			command = new MListCommand();
			command.execute(request, response);
			
			viewPage += "/mList.jsp";
		}
		// 회원이 볼 수 있는 멤버 개인 정보
		else if(com.equals("/mInfor")) {
			command = new MInforCommand();
			command.execute(request, response);
			
			viewPage += "/mInfor.jsp";
		}
		// DB 실시간 채팅처리 (MemberMain화면에서 / 23-11-21(학원))
		else if(com.equals("/memberMassageInput")) {
			command = new MemberMassageInputCommand();
			command.execute(request, response);
			
			return;
		}
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
