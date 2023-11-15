package board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardSearchCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search")== null ? "" : request.getParameter("search");
		String searchString = request.getParameter("searchString")== null ? "" : request.getParameter("searchString");
		
		int pageSu = request.getParameter("pageSu")== null ? 1 : Integer.parseInt(request.getParameter("pageSu"));
		int pageSize = request.getParameter("pageSize")==null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
		
		BoardDAO dao = new BoardDAO();
		
		ArrayList<BoardVO> vos = dao.getBoardContentSearch(search,searchString);
		
		// search에는 값이 title,nickName 등으로 들어가기 때문에.. 
		String searchTitle = "";
		if(search.equals("title")) searchTitle = "글제목";
		else if(search.equals("nickName")) searchTitle = "글쓴이";
		else searchTitle = "글내용";
		
		// 오늘 날짜 확인을 위해서 strToday 변수를 하나 더 넘긴다. (boardList, boardSearchList 글쓴 날짜에서 사용 예정)
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = sdf.format(today);
		
		request.setAttribute("vos", vos);
		request.setAttribute("search", search);
		request.setAttribute("searchString", searchString);
		request.setAttribute("pageSu", pageSu);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("searchTitle", searchTitle);
		request.setAttribute("searchCount", vos.size());  // jsp에서도 처리 가능하지만, 서블릿을 넘길 때 총 몇건이 넘어갔는지 넘긴다.
		request.setAttribute("strToday", strToday);
	}
}
