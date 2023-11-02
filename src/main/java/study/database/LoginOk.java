package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/database/loginOk")
public class LoginOk extends HttpServlet{
 @Override
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
	String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");
	String idCheck = request.getParameter("idCheck")== null ? "No" : request.getParameter("idCheck");
	 
	// 데이터 베이스 연결 객체 새성
	LoginDAO dao = new LoginDAO();
	 
	LoginVO vo = dao.getLoginChaeck(mid,pwd);
	
	PrintWriter out = response.getWriter();
	if(vo.getName() != null) {
		 //회원 인증 성공...
		 // 1. 자주사용하는 변수(객체) 세션에 저장(아이디,성명,닉네임)...
		 // 2. 쿠키에 아이디 저장 또는 제거
		 // 3. DB에 처리할 내용들(최종방문일, 포인트누적, 오늘 방문일수 누적...)
		 
		// 방문포인트 처리하기? (최종접속일/방문카운트도 함께 업데이트 처리)
		// 하루의 방문포인트는 매번 10포인트씩 주기로 한다. 단, 최대 50포인트까지만 인정한다. (todaycount가 5가 될때까지 포인트를 준다(어제 100번 들어왔다고해도 오늘 들어오면 1로 바뀌어야함 / 들어올때마다 업데이트가 되어야함.) / lastDate와 오늘 날짜 비교후 같을때? / point에 점수 올리기) 
		
		 // 1. 세션처리
		HttpSession session = request.getSession();
		session.setAttribute("sMid",mid);
		session.setAttribute("sName",vo.getName());
		session.setAttribute("sPoint",vo.getPoint());
		session.setAttribute("sLastDate",vo.getLastDate());
		session.setAttribute("sTodayCount",vo.getTodayCount());
	
		// 2. 쿠키저장 (숙제)
		if(idCheck.equals("save")) {
			Cookie cookie = new Cookie("cMid", mid);
			cookie.setMaxAge(60*60*24*7);
			cookie.setPath("/");  //이걸 안 넣으면 servlet에서 jsp로 쿠키를 넘길 수 없음.
			
			response.addCookie(cookie);
		}
		else if(idCheck.equals("No")) {
			Cookie[] cookies = request.getCookies();
			if(cookies.length != 1) {
				for(int i=0; i<cookies.length; i++) {
					if(cookies[i].getName().equals("cMid")) {
						cookies[i].setMaxAge(0);
						cookies[i].setPath("/");
						
						response.addCookie(cookies[i]);
					}
				}
			}
		}
		
		// 3.DB작업
		
		// 정상 로그인 체크이후에 모든 처리가 끝나면 정상처리 메세지 출력 후 membrMain.jsp로 보낸다.
		out.println("<script>");
		out.println("alert('"+mid+"님 로그인 되었습니다.')");
		out.println("location.href='"+request.getContextPath()+"/study/database/memberMain.jsp'");
		out.println("</script>");
	}
	else {
		// 회원 인증 실패시, 다시 로그인 창으로 보낸다.
		out.println("<script>");
		out.println("alert('로그인 실패')");
		out.println("location.href='"+request.getContextPath()+"/study/database/login.jsp'");
		out.println("</script>");
	}
	
	
}	
}
