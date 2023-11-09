package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFindMidOkCommand implements memberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email")== null ? "" : request.getParameter("email");
		System.out.println("email :" + email);
		
		MemberDAO dao = new MemberDAO();
		
		String res = dao.getMemberEmailSearch(email);
		
		response.getWriter().write(res);
	}

}
