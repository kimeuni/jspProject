package study2.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ex_memberSerCommand implements LoginInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("sid")==null ? "" : request.getParameter("sid");
		
		LoginDAO dao = new LoginDAO();
		
		LoginVO vo = dao.getLoginSearch(sid);
		
		if(vo.getName() != null) {
			//request.setAttribute("vo", vo);		// 찾고자 하는 회원이 존재하면 메세지 출력없이 내용을 화면에 뿌려줘야하기에 메세지창에서 다시 아이디 검색창으로 보내어 다시 아이디 검색하게 했다.
			request.setAttribute("msg", "NO");	// 메세지 없을때 msg변수에 'NO'값을 넣어서 전송했다.
			request.setAttribute("url", "memberMainSearch.lo?sid="+sid);
		}
		else {
			request.setAttribute("msg", "검색하신 회원이 없습니다.");
			request.setAttribute("url", "memberMain.lo");
		}
	}

}
