package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardInputOkCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mid = session.getAttribute("sMid")== null ? "" : (String)session.getAttribute("sMid");  //접속한 id 가져오기
		String nickName = session.getAttribute("sNickName")== null ? "" : (String)session.getAttribute("sNickName");  //접속한 닉네임 가져오기
		
		String title = request.getParameter("title")==null? "" : request.getParameter("title");
		String email = request.getParameter("email")==null? "" : request.getParameter("email");
		String homePage = request.getParameter("homePage")==null? "" : request.getParameter("homePage");
		String content = request.getParameter("content")==null? "" : request.getParameter("content");
		String openSw = request.getParameter("openSw")==null? "OK" : request.getParameter("openSw");
		String hostIp = request.getParameter("hostIp")==null? "" : request.getParameter("hostIp");
		
		BoardVO vo = new BoardVO();
		
		vo.setMid(mid);
		vo.setNickName(nickName);
		vo.setTitle(title);
		vo.setEmail(email);
		vo.setHomePage(homePage);
		vo.setContent(content);
		vo.setOpenSw(openSw);
		vo.setHostIp(hostIp);
		
		
		BoardDAO dao = new BoardDAO();
		
		// DB에 저장 처리
		int res = dao.setBoardInputOk(vo);
		
		if(res != 0) {
			request.setAttribute("msg", "게시글이 등록되었습니다");
			request.setAttribute("url", "boardList.bo");
		}
		else {
			request.setAttribute("msg", "게시글 등록에 실패하였습니다.");
			request.setAttribute("url", "boardInput.bo");
		}
	}

}
