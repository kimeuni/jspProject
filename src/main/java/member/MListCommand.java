package member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.adminInterface;
import member.MemberDAO;
import member.MemberVO;

public class MListCommand implements memberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		
		// 페이징처리
		int pageSu = request.getParameter("pageSu")== null ? 1 : Integer.parseInt(request.getParameter("pageSu"));
		int totRecode = dao.getMemberLevelTotRecode(99);
		int pageSize = request.getParameter("pageSize")==null ? 10 : Integer.parseInt(request.getParameter("pageSize"));
		int totPage = (totRecode%pageSize)== 0 ? (totRecode/pageSize) : (totRecode/pageSize)+1;
		int startIndexNo = (pageSu - 1 ) *pageSize;
		int startNo = totRecode - startIndexNo;

		//블록 페이징처리
		int blockSize = 3;
		int curBlock = (pageSu-1)/blockSize;
		int lastBlock = (totPage-1)/blockSize;
		
		ArrayList<MemberVO> vos = dao.getMemberLevelSearch(99,startIndexNo,pageSize);
		
		// 리스트 화면에 보이는 거.. level 선택할 때마다 다르게 표시하기.
//		String strLevel = "";
//		if(level == 0) strLevel = "관리자";
//		else if(level == 1) strLevel = "준회원";
//		else if(level == 2) strLevel = "정회원";
//		else if(level == 3) strLevel = "우수회원";
//		else strLevel = "전체회원";
		
		request.setAttribute("vos", vos);
		request.setAttribute("pageSu", pageSu);
		request.setAttribute("totPage", totPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("startNo", startNo);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("curBlock", curBlock);
		request.setAttribute("lastBlock", lastBlock);
	}

}
