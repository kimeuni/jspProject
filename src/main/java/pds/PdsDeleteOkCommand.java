package pds;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.SecurityUtil;

public class PdsDeleteOkCommand implements PdsInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")== null ? 0 : Integer.parseInt(request.getParameter("idx"));
		// 뒤에 _이 나오면 임시 파일이라는 뜻이다. (실무에서 사용
		String fSName_ = request.getParameter("fSName")== null ? "" : request.getParameter("fSName");
		String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd");
		// fSName에는 여러값이 /로 구분되어 들어가 있기 때문에 처리를 해줘야한다.
		// 여러개의 파일일때 '/'로 분리처리
		String[] fSNames = fSName_.split("/");
		
		// 암호화된 비밀번호와 일치하는지 체크...
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(pwd);
		
		PdsDAO dao = new PdsDAO();
		
		PdsVO vo = dao.getPdsIdxSearch(idx);
		System.out.println("vo : " + vo);
		
		// 비밀번호가 같으면 자료 삭제처리
		String res = "0";
		if(vo.getPwd().equals(pwd)) {
			// 서버에 존재하는 파일을 삭제처리한다.
			String realPath = request.getServletContext().getRealPath("/images/pds/");
			
			// java oi에 있는 File사용
			// 값이 여러개 있으면 향상된 for문 혹은 for문을 이용하여 올라간 파일 모두 삭제한다.
			for(String fSName : fSNames) {
				new File(realPath + fSName).delete();
			}
			
			// 파일 삭제후 삭제된 정보를 DB에서 제거시킨다.
			res = dao.getPdsDeleteOk(idx);
			
		}
		response.getWriter().write(res);
		
	}

}
