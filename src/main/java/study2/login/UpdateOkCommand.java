package study2.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// 수정화면
public class UpdateOkCommand implements LoginInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");
		String name = request.getParameter("name")== null ? "" : request.getParameter("name");
		
		LoginDAO dao = new LoginDAO();
		LoginVO vo = new LoginVO();
		
		// mid는 값 고정,pwd는 입력  ---   값을 넘긴 pwd와 mid로 DB에서 확인했을 때, 자료가 있는지 확인
		vo = dao.getLoginChaeck(mid, pwd);
		
		// 존재하지 않는 회원일 경우
		if(vo.getMid() == null) {
			request.setAttribute("msg", "비밀번호를 다시 확인하세요.");
			request.setAttribute("url", "update.lo");
		}
		
		
		
	}

}
