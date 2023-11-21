package pds;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.review.ReviewVO;

public class pdsContentCommand implements PdsInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PdsDAO dao = new PdsDAO();
		
		int idx = request.getParameter("idx")== null ? 1 :Integer.parseInt(request.getParameter("idx"));
		String part = request.getParameter("part")== null ? "전체" : request.getParameter("part");
		
		int pageSu = request.getParameter("pageSu")== null ? 1 :Integer.parseInt(request.getParameter("pageSu"));
		int pageSize = request.getParameter("pageSize")== null ? 5 :Integer.parseInt(request.getParameter("pageSize"));
		int totRecCnt = dao.getTotRecCnt(part);
		int totPage = (totRecCnt%pageSize)== 0 ? (totRecCnt/pageSize) : (totRecCnt/pageSize)+1;
		int startIndexNo = (pageSu - 1 ) *pageSize;
		int startNo = totRecCnt - startIndexNo;
		
		int blockSize = 3;
		int curBlock = (pageSu-1)/blockSize;
		int lastBlock = (totPage-1)/blockSize;
		
		// 해당글의 상세내역 가져오기
		PdsVO vo =dao.getPdsIdxSearch(idx);
		
		// 해당글의 리뷰내용 가져오기
		ArrayList<ReviewVO> rVOS = dao.getReViewList(idx,"pds");
		
		int reviewTot = 0;
		for(ReviewVO r: rVOS) {
			reviewTot += r.getStar();
		}
		
		// if문 안 적으면 /by zero 에러 떠서 만약 rVOS가 들어있으면 request에 값을 넘기도록 처리함.
		double reviewAvg = 0.0;
		if(rVOS.size() != 0) reviewAvg = (double)reviewTot/rVOS.size();
		
		request.setAttribute("vo", vo);
		request.setAttribute("pageSu", pageSu);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totPage", totPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("curBlock", curBlock);
		request.setAttribute("lastBlock", lastBlock);
		request.setAttribute("part", part);
		request.setAttribute("rVOS", rVOS);
		request.setAttribute("reviewAvg", reviewAvg); //별점 평균값 넘기기
		
	}

}
