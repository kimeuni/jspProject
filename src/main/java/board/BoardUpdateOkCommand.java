package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardUpdateOkCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")== null ? 0 : Integer.parseInt(request.getParameter("idx"));

		HttpSession session = request.getSession();
		String nickName = session.getAttribute("sNickName")== null ? "" : (String)session.getAttribute("sNickName");  //접속한 닉네임 가져오기
		
		String title = request.getParameter("title")==null? "" : request.getParameter("title");
		String email = request.getParameter("email")==null? "" : request.getParameter("email");
		String homePage = request.getParameter("homePage")==null? "" : request.getParameter("homePage");
		String content = request.getParameter("content")==null? "" : request.getParameter("content");
		String openSw = request.getParameter("openSw")==null? "OK" : request.getParameter("openSw");
		String hostIp = request.getParameter("hostIp")==null? "" : request.getParameter("hostIp");
		
		int pageSu = request.getParameter("pageSu")== null ? 1 : Integer.parseInt(request.getParameter("pageSu"));
		int pageSize = request.getParameter("pageSize")== null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
		
		BoardVO vo = new BoardVO();
		
		vo.setIdx(idx);
		vo.setNickName(nickName);  
		vo.setTitle(title);
		vo.setEmail(email);
		vo.setHomePage(homePage);
		vo.setContent(content);
		vo.setOpenSw(openSw);
		vo.setHostIp(hostIp);
		
		request.setAttribute("pageSu", pageSu);
		request.setAttribute("pageSize", pageSize);
		
		
		BoardDAO dao = new BoardDAO();
		
		// DB에 저장 처리
		int res = dao.setBoardUpdateOk(vo);
		
		if(res != 0) {
			request.setAttribute("msg", "게시글이 수정되었습니다");
		}
		else {
			request.setAttribute("msg", "게시글 수정에 실패하였습니다.");
		}
		request.setAttribute("url", "boardContent.bo?idx="+idx + "&pageSu="+pageSu+"&pageSize="+pageSize);
	}

}
