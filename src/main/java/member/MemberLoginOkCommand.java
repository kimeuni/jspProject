package member;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.SecurityUtil;

public class MemberLoginOkCommand implements memberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		String idCheck = request.getParameter("idCheck")==null ? "NO" : request.getParameter("idCheck");
		System.out.println("mid : " + mid);
		
		MemberDAO dao = new MemberDAO();

		MemberVO vo = dao.getMemberMidCheck(mid);
		System.out.println("vo : " + vo);
		
		// userDel이 OK이면 탈퇴신청한 아이디이기 때문에, 막는다.
		// 회원인 아이디가 아닌 것은 vo에 null로 넘어오기 때문에 null값이 넘어오는지도 비교한다.
//		if(!vo.getMid().equals(mid)) { 
		if(vo.getMid() == null || vo.getUserDel().equals("OK") || !vo.getMid().equals(mid)) {
			request.setAttribute("msg", "아이디를 확인하세요.");
			request.setAttribute("url", "memberLogin.mem");
			return;
		}
		
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(pwd);

		if(!vo.getPwd().equals(pwd)) {
			request.setAttribute("msg", "비밀번호를 확인하세요.");
			request.setAttribute("url", "memberLogin.mem");
			return;
		}
		
		// 로그인 성공시 처리할 내용들.... (1. 세션저장, 2. 쿠키저장, 3. 방문횟수(총방문횟수,오늘방문횟수) 4. 포인트저장 등....)
		String strLevel = "";
		if(vo.getLevel() == 0) strLevel = "관리자";
		if(vo.getLevel() == 1) strLevel = "준회원";
		if(vo.getLevel() == 2) strLevel = "정회원";
		if(vo.getLevel() == 3) strLevel = "우수회원";
		
		// 1. 세션저장
		HttpSession session = request.getSession();
		session.setAttribute("sMid", mid);
		session.setAttribute("sNickName", vo.getNickName());
		session.setAttribute("sLevel", vo.getLevel());
		session.setAttribute("strLevel", strLevel);
		
		// 2. 쿠키 저장
		if(idCheck.equals("save")) {
			Cookie cookie = new Cookie("cMid", mid);
			cookie.setMaxAge(60*60*24*5);
			cookie.setPath("/");
			
			response.addCookie(cookie);
		}
		// 아이디 저장 체크 x시 쿠기 삭제
		else if(idCheck.equals("NO")) {
			Cookie[] cookies = request.getCookies();
			for(int i=0; i<cookies.length; i++) {
				if(cookies[i].getName().equals("cMid")) {
					cookies[i].setMaxAge(0);
					cookies[i].setPath("/");
					
					response.addCookie(cookies[i]);
				}
			}
		}
		
		//3. 방문횟수,포인트 처리
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = sdf.format(today);
		
		if(strToday.substring(0,10).equals(vo.getLastDate().substring(0,10))) {
			vo.setTodayCnt(vo.getTodayCnt()+1); // 방문포인트 증가
			if(vo.getTodayCnt() <= 5) vo.setPoint(vo.getPoint()+10);
		}
		else { 
			vo.setTodayCnt(1);
			vo.setPoint(vo.getPoint()+10);
		}
		vo.setVisitCnt(vo.getVisitCnt()+1);
		
		// 자동 등업처리(정회원)
		// 가입후 10일경과후 5회 초과 접속시, 자동 정회원 등업처리 (단, 1일 1회로 한정... 여기선 고려하지 않음)
//		int diffDay = (sdf.format(today)) - sdf.format(vo.getStartDate());
		
		
		// 날짜를 비교하기 위해서 이렇게 작성
		/*
		try {
			// 오늘 날짜에 대해서 받음
			Date today2 = new Date();
			// 이렇게하면 문자형식으로 들어감?
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");  // 날짜를 이런형식("yyyy-MM-dd")으로 바꿔줘 (문자가 됨)...
			String strToday2 = sdf2.format(today2);
			// 연산하기 위해서 date타입으로 바꾸어야 한다.  (파싱이 date타입으로 가기 위해서는 String타입이여야한다(today2가) 
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(strToday2);  //날짜형식으로 파씽시킴.
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(vo.getStartDate()); //날짜형식으로 파씽시킴.
			
			// 제일 정밀한게 getTime()
			long diffDate = (date1.getTime() - date2.getTime()) / (60*60*24*1000);  //(60*60*24*1000)오늘 하루 // 날짜차이가 일수로 계산
//			System.out.println("가입 후 지난 날짜 : "+  diffDate);
			
			if(vo.getLevel()==1 && diffDate <= 10 && vo.getVisitCnt() >= 5) vo.setLevel(2); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		*/
		if(vo.getLevel()==1 && vo.getVisitCnt() >= 5) vo.setLevel(2); 
		
		// DB작업(변경된 내용들을 DB에 저장(갱신))
		dao.setLoginUpdate(vo);
		
		// 처리 완료후 메세지 출력 후 회원 메인창으로 전송한다.
		request.setAttribute("msg", mid+"님 로그인되었습니다.");
		request.setAttribute("url", "memberMain.mem");
	}

}
