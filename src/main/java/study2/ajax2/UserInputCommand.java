package study2.ajax2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInputCommand implements UserInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		String name = request.getParameter("name")== null ? "" : request.getParameter("name");
		int age = request.getParameter("age")== null ? 0 : Integer.parseInt(request.getParameter("age"));
		String address = request.getParameter("address")== null ? "" : request.getParameter("address");
	
		UserVO vo = new UserVO();
		vo.setMid(mid);
		vo.setName(name);
		vo.setAge(age);
		vo.setAddress(address);
		
		UserDAO dao = new UserDAO();
		
		// 아이디 중복체크
		String res = dao.getMidSearch(mid);

		if(res.equals("0")) {
			response.getWriter().write("아이디가 중복되었습니다. 다시 가입하세요.");
		}
		else { // "1"이 넘어오면 값이 없기 때문에, 중복되는 아이디가 없어서 가입 가능하다.
			res = dao.setUserInputOk(vo);
			if(res.equals("0")) response.getWriter().write("회원가입 실패. 다시 가입하세요.");
			else response.getWriter().write(res);
		}
	}

}
