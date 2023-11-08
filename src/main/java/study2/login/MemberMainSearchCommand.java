package study2.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberMainSearchCommand implements LoginInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("sid")== null ? "" : request.getParameter("sid");
		
		LoginDAO dao = new LoginDAO();
		
		LoginVO vo = dao.getLoginSearch(sid);
		
		if(vo.getName() != null) {
			request.setAttribute("vo", vo);
		}
	}

}
