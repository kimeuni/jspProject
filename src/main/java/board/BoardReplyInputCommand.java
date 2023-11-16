package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardReplyInputCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardIdx = request.getParameter("boardIdx")== null ? 0 : Integer.parseInt(request.getParameter("boardIdx"));
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		String nickName = request.getParameter("nickName")== null ? "" : request.getParameter("nickName");
		String hostIp = request.getParameter("hostIp")== null ? "" : request.getParameter("hostIp");
		String content = request.getParameter("content")== null ? "" : request.getParameter("content");
	
		BoardReplyVO replyVO = new BoardReplyVO();
		
		// vo에 값 저장
		replyVO.setBoardIdx(boardIdx);
		replyVO.setMid(mid);
		replyVO.setNickName(nickName);
		replyVO.setHostIp(hostIp);
		replyVO.setContent(content);
		
		BoardDAO dao = new BoardDAO();
		
		int res = dao.setReplyInputOk(replyVO);
		
		response.getWriter().write(res+"");
		
	}

}
