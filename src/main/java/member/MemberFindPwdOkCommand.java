package member;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.SecurityUtil;

public class MemberFindPwdOkCommand implements memberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		String email = request.getParameter("email")== null ? "" : request.getParameter("email");
		
		MemberDAO dao = new MemberDAO();
		
		String res = dao.getmemberMidEmailCheck(mid,email);
		
		// 아이디, 이메일 같은 회원 존재.. 임시 비밀번호 발급 후, 데이터베이스 저장
		if(!res.equals("0")) {
			UUID uid = UUID.randomUUID(); // 임시로 줄 랜덤 16진수 숫자 불러오기
			String uidPwd = uid.toString().substring(0,8); // 너무 길기 때문에 substring으로 잘라줌
			SecurityUtil security = new SecurityUtil();
			String pwd = security.encryptSHA256(uidPwd); //암호화
			System.out.println(pwd);
			
			int re = dao.setImsiMemberPwd(mid,email,pwd);
			if(re != 0) {
				response.getWriter().write(uidPwd);
			}
		}
		else {
			response.getWriter().write(res);
		}
	}
}
