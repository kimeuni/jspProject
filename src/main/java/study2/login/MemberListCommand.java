package study2.login;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//전체 출력 처리
public class MemberListCommand implements LoginInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDAO dao = new LoginDAO();
		
		ArrayList<LoginVO> vos = dao.getLoginList();
		
		if(vos != null) {
			request.setAttribute("vos", vos);
		}
		
	}

}
