package study.database;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	// 회원 여부 판별하기
	LoginVO vo = dao.getLoginChaeck(mid,pwd);
	
	PrintWriter out = response.getWriter();
	if(vo.getName() != null) {
		 //회원 인증 성공...
		 // 1. 자주사용하는 변수(객체) 세션에 저장(아이디,성명,닉네임)...
		 // 2. 쿠키에 아이디 저장 또는 제거
		 // 3. DB에 처리할 내용들(최종방문일, 포인트누적, 오늘 방문일수 누적...)
		 
		// 방문포인트 처리하기? (최종접속일/방문카운트도 함께 업데이트 처리)
		// 하루의 방문포인트는 매번 10포인트씩 주기로 한다. 단, 최대 50포인트까지만 인정한다. (todaycount가 5가 될때까지 포인트를 준다(어제 100번 들어왔다고해도 오늘 들어오면 1로 바뀌어야함 / 들어올때마다 업데이트가 되어야함.) / lastDate와 오늘 날짜 비교후 같을때? / point에 점수 올리기) 
		
		// 날짜 비교
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = sdf.format(today);
//		System.out.println("strToday" + strToday);
		
		if(strToday.substring(0,10).equals(vo.getLastDate().substring(0,10))) {
			// 오늘 다시 방문한 경우
			vo.setTodayCount(vo.getTodayCount()+1); // 방문포인트 증가
			if(vo.getTodayCount() <= 5) vo.setPoint(vo.getPoint()+10);
		}
		else {
			// 오늘 처음 방문한 경우 (바로 전에 들어와있는게 데이터베이스에 들어와있기 때문에(아직 업데이트를 안해서) 어제 마지막으로 로그인했다면 오늘 들어갔을 때 최종날짜는 어제이고 오늘 날짜로 들어아기 때문에 날짜가 같지 않아서 오늘 처음 들어간걸로 친다.)
			vo.setTodayCount(1);
			vo.setPoint(vo.getPoint()+10);
		}
		
		// 3.DB작업(변경된 내용들을 DB에 저장(갱신))
		dao.setLoginUpdate(vo);  //값을 업데이트하고 읽어오진않음.
		// 업데이트에 저장만 하고 읽어오진 않았음. 그래서 세션에 저장된 lastDate는 이전에 저장된 내용이 출려된다. (point와 todayCount가 증가하는 이유는 위에서 vo에 바로 set시켜서 증가되어 보이는 것이다.(데이터베이스에서 꺼내온게 아님)
		// 그리고 다시 로그인하면 업데이트한 이후의 값을 읽고 또 위해서 point와 todayCount는 누적처리되며, 최종접속일은 이전에 접속한 날짜가 된다.)
		
		// 만약 업데이트한 자료를 바로 세션에 저장하고 싶을 경우는
		// dao.getLoginupdate(vo); 등을 만들고 DAO에서 select * from login.. 을 해서 값을 읽어와 가져온 후 저장하면
		// 최종날짜일 또한 지금 로그인한 날짜로 바뀌어있다.
		
		 // 1. 세션처리 (계속 보는 화면에 나오는 것들은 세션에 저장해서 처리하는게 좋다 / 아니면 데이터베이스로 계속 읽어와야하는데 메모리차원에서든 다른면(귀찮음)에서도 좋지않다)
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
