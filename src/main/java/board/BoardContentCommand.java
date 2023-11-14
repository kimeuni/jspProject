package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardContentCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")== null ? 0 : Integer.parseInt(request.getParameter("idx"));
		int pageSu = request.getParameter("pageSu")== null ? 1 : Integer.parseInt(request.getParameter("pageSu"));
		int pageSize = request.getParameter("pageSize")== null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
		
		BoardDAO dao = new BoardDAO();
		
		// 게시글 가져오기전에 조회수 1증가시키기 (update)  (미리 update를 시키고 밑에서 읽어야 들어가자마자 조회수가 올라간다)
		// dao.setBoardReadNumPlus(idx); ==> 이것만 적으면, 
		dao.setBoardReadNumPlus(idx);
		
		
		BoardVO vo =dao.getBoardContent(idx);
		
		request.setAttribute("vo", vo);
		request.setAttribute("pageSu", pageSu);
		request.setAttribute("pageSize", pageSize);
	}

}
