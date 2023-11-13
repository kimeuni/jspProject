package admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberVO;

public class AdminContentCommand implements adminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 가입자 모든 정보 가져오기 관리자 메인 화면에
		MemberDAO dao = new MemberDAO();
		
		ArrayList<MemberVO> vos = dao.getMemberList();
		
		// 준회원만 cnt
		int junHoiwonCnt = 0;
		for(int i=0; i<vos.size(); i++) {
			if(vos.get(i).getLevel() == 1) {
				junHoiwonCnt++;
			}
		}
		System.out.println(junHoiwonCnt);
		
		if(vos != null) {
			request.setAttribute("vos", vos);
			request.setAttribute("junHoiwonCnt", junHoiwonCnt);
		}
		
	}

}
