package study2.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginOkCommand implements LoginInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");
		String idCheck = request.getParameter("idCheck")== null ? "No" : request.getParameter("idCheck");
		
		LoginDAO dao = new LoginDAO();
		LoginVO vo = null;
		
		// 아이디, 비밀번호 확인
		vo = dao.getLoginChaeck(mid, pwd);
		
		if(vo.getName() != null) {
			// 로그인 성공시 처리할 내용들을 모두 기술한다.. 
			//(1.세션담기(id,성명,포인트,최종접속일,총접속일) 2. 쿠키담기(id)
			//세선 담기
			HttpSession session = request.getSession();
			session.setAttribute("sMid", mid);
			session.setAttribute("sName",vo.getName());
			session.setAttribute("sPoint",vo.getPoint());
			session.setAttribute("sLastDate",vo.getLastDate());
			session.setAttribute("sTodayCount",vo.getTodayCount());
			
			//쿠기 담기
			if(idCheck.equals("Save")) {
				Cookie cookie = new Cookie("cMid", mid);
				cookie.setMaxAge(60*60*24*5);
				cookie.setPath("/");
				
				response.addCookie(cookie);
			}
			else if(idCheck.equals("No")) {
//				System.out.println(idCheck);
				Cookie[] cookies = request.getCookies();
				if(cookies.length != 1) {
					for(int i=0; i<cookies.length; i++) {
						if(cookies[i].getName().equals("cMid")) {
							cookies[i].setMaxAge(0);
							cookies[i].setPath("/");  // 이걸 설정 안해 놓으면 쿠키가 제대로 적용 안됨.
							
							response.addCookie(cookies[i]);
						}
					}
				}
			}
			
			
			
			// 메세지 처리
			request.setAttribute("msg", "로그인 성공");
			request.setAttribute("url", request.getContextPath()+"/memberMain.lo");
		}
		else {
			request.setAttribute("msg", "로그인 실패");
			request.setAttribute("url", request.getContextPath()+"/login.lo");
		}
		
	}

}
