package pds;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PdsListCommand implements PdsInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String part = request.getParameter("part")== null ? "전체" : request.getParameter("part");
		
		PdsDAO dao = new PdsDAO();
		
		// 페이징 처리
		int pageSu = request.getParameter("pageSu")== null ? 1 :Integer.parseInt(request.getParameter("pageSu"));
		int pageSize = request.getParameter("pageSize")== null ? 5 :Integer.parseInt(request.getParameter("pageSize"));
		int totRecCnt = dao.getTotRecCnt(part);
		int totPage = (totRecCnt%pageSize)== 0 ? (totRecCnt/pageSize) : (totRecCnt/pageSize)+1;
		int startIndexNo = (pageSu - 1 ) *pageSize;
		int startNo = totRecCnt - startIndexNo;
		
		int blockSize = 3;
		int curBlock = (pageSu-1)/blockSize;
		int lastBlock = (totPage-1)/blockSize;
		
		ArrayList<PdsVO> vos = dao.getPdsList(part,pageSize,startIndexNo);
		
		request.setAttribute("vos", vos);
		request.setAttribute("part", part);
	}

}
