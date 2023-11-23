package study2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.apiTest.CrimeDAO;
import study2.apiTest.CrimeVO;

public class PoliceCheckCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String police = request.getParameter("police")==null ? "" : request.getParameter("police");
		int year = request.getParameter("year")==null ? 0 : Integer.parseInt(request.getParameter("year"));
		
		CrimeDAO dao = new CrimeDAO();
		CrimeVO analyzeVo = new CrimeVO();
		
		// 합계,평균 구하기  
		analyzeVo = dao.getPoliceAvgCrimeData(police);
		
		// 년도별 범죄 건수 구하기 
		ArrayList<CrimeVO> vos = dao.getPoliceListCrimeData(police);
		
		request.setAttribute("vos", vos);
		request.setAttribute("analyzeVo", analyzeVo);
		request.setAttribute("police", police);
		request.setAttribute("year", year);
	}

}
