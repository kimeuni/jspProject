package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.ComplaintVO;
import admin.adminInterface;

public class ComplaintCommand implements adminInterface, BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String part = request.getParameter("part")== null ? "" : request.getParameter("part");
		int partIdx = request.getParameter("partIdx")== null ? 0 : Integer.parseInt(request.getParameter("partIdx"));
		String cpMid = request.getParameter("cpMid")== null ? "" : request.getParameter("cpMid");
		String cpContent = request.getParameter("cpContent")== null ? "" : request.getParameter("cpContent");
		String cpContOther = request.getParameter("cpContOther")== null ? "" : request.getParameter("cpContOther");
		
		ComplaintVO vo = new ComplaintVO();
		vo.setPart(part);
		vo.setPartIdx(partIdx);
		vo.setCpMid(cpMid);
		if(cpContent.equals("기타")) {
			vo.setCpContent(cpContOther);
		}
		else {
			vo.setCpContent(cpContent);
		}
		
		BoardDAO dao = new BoardDAO();
		
		int res = dao.setComplaintInsert(vo);
		
		response.getWriter().write(res + "");
	}

}
