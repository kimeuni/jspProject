package study2.apiTest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;

public class SaveCrimeDataCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int year = request.getParameter("year")==null ? 0 : Integer.parseInt(request.getParameter("year"));
		String police = request.getParameter("police")== null ? "" : request.getParameter("police");
		int robbery = request.getParameter("robbery")==null ? 0 : Integer.parseInt(request.getParameter("robbery"));
		int murder = request.getParameter("murder")==null ? 0 : Integer.parseInt(request.getParameter("murder"));
		int theft = request.getParameter("theft")==null ? 0 : Integer.parseInt(request.getParameter("theft"));
		int violence = request.getParameter("violence")==null ? 0 : Integer.parseInt(request.getParameter("violence"));
		
		CrimeVO vo = new CrimeVO();
		vo.setYear(year);
		vo.setPolice(police);
		vo.setRobbery(robbery);
		vo.setMurder(murder);
		vo.setTheft(theft);
		vo.setViolence(violence);

		CrimeDAO dao = new CrimeDAO();
		
		dao.saveCrimeData(vo); 
	}

}
