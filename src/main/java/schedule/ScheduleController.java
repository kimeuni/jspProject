package schedule;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("*.sc")
public class ScheduleController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ScheduleInterface command = null;
		String viewPage = "/WEB-INF/schedule";
		
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		HttpSession session = request.getSession();
		int level = session.getAttribute("sLevel")== null ? 99 : (int)session.getAttribute("sLevel");
		
		// 회원이 아니면 Home화면으로 보내기 (23-11-22학원)
		if(level > 4) {
			request.getRequestDispatcher("/").forward(request, response);
		}
		// 스캐줄 화면 (23-11-22학원)
		else if(com.equals("/schedule")) {
			command = new ScheduleCommand();
			command.execute(request, response);
			viewPage += "/schedule.jsp";
		}
		// 날짜별 스케줄 등록 (23-11-22학원)
		else if(com.equals("/scheduleMenu")) {
			command = new ScheduleMenuCommand();
			command.execute(request, response);
			viewPage += "/scheduleMenu.jsp";
		}
		// 일정 등록 처리 (23-11-22학원)
		else if(com.equals("/scheduleInputOk")) {
			command = new ScheduleInputOkCommand();
			command.execute(request, response);
			return;
		}
		// 일정 삭제 처리 (23-11-22학원)
		else if(com.equals("/scheduleDeleteOk")) {
			command = new ScheduleDeleteOkCommand();
			command.execute(request, response);
			return;
		}
		// 일정 수정 처리 (23-11-22학원)
		else if(com.equals("/scheduleUpdateOk")) {
			command = new ScheduleUpdateOkCommand();
			command.execute(request, response);
			return;
		}
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
