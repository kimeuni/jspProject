package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberUpdateFormCommand implements memberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberMidCheck(mid);
		
		// 전화번호 분리(-)
		String[] tel = vo.getTel().split("-");
		// 공백이 있으면 지워서 보내야 한다.
		if(tel[1].equals(" ")) tel[1] = "";
		if(tel[2].equals(" ")) tel[2] = "";
		request.setAttribute("tel1", tel[0]);
		request.setAttribute("tel2", tel[1]);
		request.setAttribute("tel3", tel[2]);
		
		// 주소분리(/)
		//데이터베이스를 확인해보면 값을 넣지 않으면  (공백)/(공백)/ 이런식으로 되어있어서 공백을 지우고 넘겨주어야 한다. (위에 전화번호도 동일)
		String[] address = vo.getAddress().split("/");
		if(address[0].equals(" ")) address[0] = "";
		if(address[1].equals(" ")) address[1] = "";
		if(address[2].equals(" ")) address[2] = "";
		if(address[3].equals(" ")) address[3] = "";
		request.setAttribute("postcode", address[0]);
		request.setAttribute("roadAddress", address[1]);
		request.setAttribute("detailAddress", address[2]);
		request.setAttribute("extraAddress", address[3]);
		
		// 취미는 통째로 넘겨서 JSTL에서 처리하기로 한다.
		request.setAttribute("hobby", vo.getHobby()); // jsp에서 사용할 변수 이름 조금이라도 짧게 적기 위해 이렇게 넘겼다..
		
		// 이미 로그인한 회원이기 때문에 정보가 없을리 없다.. if문으로 확인 안하고 넘겨도 된다.
		request.setAttribute("vo", vo);
	}

}
