package admin.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.adminInterface;
import member.MemberDAO;
import member.MemberVO;

public class MemberLevelSearchCommand implements adminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int level = request.getParameter("level")==null ? 99 : Integer.parseInt(request.getParameter("level"));
		
		MemberDAO dao = new MemberDAO();
		
		// 1. 현재 페이지를 구한다
		int pageSu = request.getParameter("pageSu")== null ? 1 : Integer.parseInt(request.getParameter("pageSu"));
		
		// 2. 작성된 글이 총 몇개인지 구하기
		int totRecode = dao.getMemberLevelTotRecode(level);
		
		// 3. 한 페이지에 몇개를 보이게 할 것인지
//		int pageSize = 10;
		int pageSize = request.getParameter("pageSize")==null ? 10 : Integer.parseInt(request.getParameter("pageSize"));
		
		// 4. 총 page 수 구하기
		int totPage = (totRecode%pageSize)== 0 ? (totRecode/pageSize) : (totRecode/pageSize)+1;
		
		// 5. 현재 페이지를 출력할 시작 인덱스 번호 구하기
		int startIndexNo = (pageSu - 1 ) *pageSize;
		
		// 6. 현재 화면에 표시될 시작번호 구하기
		int startNo = totRecode - startIndexNo;
		
		
		// 블록 페이징 처리
		// 1. 블록의 크기
		int blockSize = 3;
		
		// 2. 현재 페이지가 속한 블록 번호를 구한다.
		int curBlock = (pageSu-1)/blockSize;
		
		// 3. 마지막 블럭을 구한다
		int lastBlock = (totPage-1)/blockSize;
		
		ArrayList<MemberVO> vos = dao.getMemberLevelSearch(level,startIndexNo,pageSize);
		
		// 리스트 화면에 보이는 거.. level 선택할 때마다 다르게 표시하기.
		String strLevel = "";
		if(level == 0) strLevel = "관리자";
		else if(level == 1) strLevel = "준회원";
		else if(level == 2) strLevel = "정회원";
		else if(level == 3) strLevel = "우수회원";
		else strLevel = "전체회원";
		
		request.setAttribute("vos", vos);
		request.setAttribute("level", level);
		request.setAttribute("pageSu", pageSu);
		request.setAttribute("totPage", totPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("startNo", startNo);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("curBlock", curBlock);
		request.setAttribute("lastBlock", lastBlock);
		request.setAttribute("strLevel", strLevel);
	}

}
