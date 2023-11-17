package study2.pdstest;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;

public class TempFileDeleteCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("fileName")== null ? "" : request.getParameter("fileName");
		
		String realPath = request.getServletContext().getRealPath("/images/pdstest/");
		
		// realPath 경로에 fileName이 들어 있기에 .. 이렇게 적음 / 객체생성 / detete() : 해당 파일 지움
//		new File(realPath + fileName).delete();
		
		File file = new File(realPath + fileName);
		
		// 파일이 있는지 확인 후 삭제한다.
		String res = "0";
		if(file.exists()) {
			file.delete();
			res = "1";
		}
		
		response.getWriter().write(res);
		
	}

}
