package study2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.apiTest.CrimeDAO;

public class DeleteCrimeDataCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int year = request.getParameter("year")== null ? 0 : Integer.parseInt(request.getParameter("year"));
		
		CrimeDAO dao = new CrimeDAO();
		
		int res = dao.getSearchYear(year);
		
		String str = "";
		if(res == 0) str="찾고자 하는 자료가 없습니다.";
		else str = dao.deleteCrimeData(year);
		
		
		
		response.getWriter().write(str);
	}

}
