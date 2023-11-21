package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberMassageInputCommand implements memberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nickName = (String)session.getAttribute("sNickName");
		
		String chat = request.getParameter("chat")== null ? "" : request.getParameter("chat");
		chat.replace("<", "&lt;");  //태그 사용 못하도록 막기 위해 변환
		chat.replace(">", "&gt;");  //태그 사용 못하도록 막기 위해 변환
		
		MemberChatVO vo = new MemberChatVO();
		vo.setNickName(nickName);
		vo.setChat(chat);
		
		MemberDAO dao = new MemberDAO();
		
		dao.setMemberMessageInputOk(vo);
	}

}
