package guest;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GuestList")
public class GuestList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GuestDAO dao = new GuestDAO();
		GuestVO vo = new GuestVO();
		
		//page만들기
		// 1. 현재 페이지 번호를 구한다. (처음에는 null값이 넘어오는데 게시판 페이지는 무조건 1페이지가 나와야하기 때문에 null이 넘어오면 1로 바꾼다)
		int pag = request.getParameter("pag")== null ? 1 : Integer.parseInt(request.getParameter("pag"));
		
		// 2. 한 페이지의 분량을 결정한다. (한 화면에 5개로 고정했기 때문에  request.setAttribute 로 값을 넘기지 않았음.. 만약 입력 받았다면 값을 넘겨야함)
		int pageSize = request.getParameter("pageSize")== null ? 2 : Integer.parseInt(request.getParameter("pageSize"));
//		int pageSize= 5;
		
		// 3. 총 레코드 건수를 구한다. (sql문 명령어 중, count함수 사용)
		int totRecCnt =  dao.getTotRecCnt();
		
		// 4. 총 페이지 건수를 구한다.
		int totPage = (totRecCnt % pageSize)==0 ? (totRecCnt / pageSize) : (totRecCnt / pageSize) + 1;
		
		// 5. 현재 페이지에 출력할 시작 인덱스번호를 구한다. (인덱스 번호 = 현재페이지 -1 * 페이지 사이즈 (인덱스 번호는 0부터 나옴))  
		// (ex: 5번째 페이지인데 페이지 사이즈가 5일때 ... => (5-1) * 5 = 20     .. 5번째 페이지의 시작 인덱스 번호는 20이다
		int startIndexNo = (pag - 1) * pageSize;
			
		
		// 6. 현재 화면에 표시될 시작번호를 구한다. (전체 레코드번호 - 인덱스번호)
		int curScrStartNo = totRecCnt - startIndexNo;
		
		//--------------------------------------------------------------------
		
		// 블록페이징 처리 (시작블록의 번호를 0번으로 처리했다.)
		// 1. 블록의 크기 (여기선 3으로 결정했다.)
		int blockSize = 3;
		
		// 2. 현재페이지가 속한 블록 번호를 구한다. (예: 총레코드갯수 38개일경우(총8페이지) 1/2/3 페이지는 0블록, 4/5/6페이지는 1블록 ,7/8 페이지는 2블록)
		int curBlock = (pag-1)/blockSize;
		
		// 3. 마지막 블럭을 구한다. (블록의 크기)
		int lastBlock = (totPage-1)/blockSize;
		
		// 지정된 페이지의 자료를 요청한 페이지의 블량만큼 가져온디.
		ArrayList<GuestVO> vos = dao.getGuestList(startIndexNo,pageSize);
		
		request.setAttribute("vos", vos);
		request.setAttribute("pag", pag);
		request.setAttribute("totPage", totPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("curScrStartNo", curScrStartNo);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("curBlock", curBlock);
		request.setAttribute("lastBlock", lastBlock);
		
		String viewPage = "/guest/guestList.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
