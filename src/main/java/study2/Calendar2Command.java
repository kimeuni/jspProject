package study2;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Calendar2Command implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 오늘날짜 처리(저장)
		Calendar calToday = Calendar.getInstance();
		
		//오늘 년도
		int toYear = calToday.get(Calendar.YEAR);
		//오늘 월
		int toMonth = calToday.get(Calendar.MONTH);
		//오늘 날짜
		int toDay = calToday.get(Calendar.DATE);
		
		// 화면에 보여줄 해당 '년/월'을 셋팅
		int yy = request.getParameter("yy")== null ? toYear : Integer.parseInt(request.getParameter("yy"));
		int mm = request.getParameter("mm")== null ? toMonth : Integer.parseInt(request.getParameter("mm"));
		
		// 이전월을 클릭시, 1월(0)은 음수가 넘어오기에 년도를 -1 빼주고, 월은 12월(11)로 세팅한다.
		// 다음월을 클릭시 12월(11)은 13월(12)가 넘어오기에 년도를 +1 빼주고, 월은 1월(0)로 셋팅한다.
		if(mm < 0) {
			yy--;
			mm = 11;
		}
		if(mm > 11) {
			yy++;
			mm = 0;
		}
		
		// 선택한 해당 '년/월'의 1일을 기준으로 날짜를 세팅해준다.(처음에는 오늘 날짜, 즉 '년/월/1'로 셋팅한다.)
		calToday.set(yy, mm, 1);
		
		// 앞에서 셋팅한 해당 '년/월/1'의 요일값을 숫자로 가져온다.(일:1 월:2 화:3 수:4~~~)  : 시작테이블의 1일이 들어갈 첫 열을 찾아주기 위함.
		// DAY_OF_WEEK는 위에 적혀 있는 (일:1 월:2 화:3 수:4~~~)이런식으로 가져온다
		int startWeek = calToday.get(Calendar.DAY_OF_WEEK);
		
		// 해당 '년/월'의 마지막 일자를 가져온다.
		int lastDay = calToday.getActualMaximum(Calendar.DAY_OF_MONTH);  //달에 해당하는 꽉찬 일자(마지막날)을 가져온다.
		
		// 출력된 달력의 '앞쪽/뒷쪽'의 빈공간을 해당월의 '이전달'다음달'의 날짜로 채워보자....
		int prevYear = yy;
		int prevMonth = mm-1;  
		int nextYear = yy;
		int nextMonth = mm +1;
		if(prevMonth == -1) {
			prevYear--;
			prevMonth = 11;
		}
		if(nextMonth == 12) {
			nextYear++;
			nextMonth = 0;
		}
		// 현재 월의 이전월에 해당하는 마지막 날짜를 구한다. (11월이라면 10월의 마지막 일자인 31을 구한다.)
		calToday.set(prevYear, prevMonth, 1);
		int prevLastDay = calToday.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		// 다음월의 1일에 해당하는 요일을 숫자로 구한다.
		calToday.set(nextYear, nextMonth, 1);
		int nextStartWeek = calToday.get(Calendar.DAY_OF_WEEK);
		
		// 현재달의 '전월/다음월'의 변수를 저장..
		request.setAttribute("prevYear",prevYear);
		request.setAttribute("prevMonth",prevMonth);
		request.setAttribute("nextYear",nextYear);
		request.setAttribute("nextMonth",nextMonth);
		request.setAttribute("prevLastDay",prevLastDay);
		request.setAttribute("nextStartWeek",nextStartWeek);
		
		// 화면에 보여줄 달력의 해당 내역(년/월/요일숫자).... 저장소에 저장
		request.setAttribute("yy", yy);
		request.setAttribute("mm", mm);
		request.setAttribute("startWeek", startWeek);
		request.setAttribute("lastDay", lastDay);
		
		// 오늘 날짜를 저장소에 담아서 보낸다.
		request.setAttribute("toYear", toYear);
		request.setAttribute("toMonth", toMonth);
		request.setAttribute("toDay", toDay);
	}

}
