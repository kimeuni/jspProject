package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("*.bo")  
public class BoardController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardInterface command = null;
		String viewPage = "/WEB-INF/board";
		
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		HttpSession session = request.getSession();
		int level = session.getAttribute("sLevel")== null ? 99 : (int)session.getAttribute("sLevel");
		
		// 회원이 아니면 Home화면으로 보내기
		if(level > 4) {
			request.getRequestDispatcher("/").forward(request, response);
		}
		// board 화면 처리 후 들어가기(게시판에 들어가면 적힌 글 목록들이 보여야 하기 때문에)
		else if(com.equals("/boardList")) {
			command = new BoardListCommand();
			command.execute(request, response);
			viewPage += "/boardList.jsp";
		}
		// 게시판에 <글쓰기> 누를 시, 들어가는 View 화면
		else if(com.equals("/boardInput")) {
			
			viewPage += "/boardInput.jsp";
		}
		// 게시판 <글쓰기>에서 작성한 글 DB에 저장 처리
		else if(com.equals("/boardInputOk")) {
			command = new BoardInputOkCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		// 게시판 "제목(title)"을 눌렀을 시, 게시글 상세보기로 내용 가지고 View화면으로 이동
		else if(com.equals("/boardContent")) {
			command = new BoardContentCommand();
			command.execute(request, response);
			
			viewPage += "/boardContent.jsp";
		}
		// 게시판 좋아요 눌렀을 시 처리 내용 (중복불허)
		else if(com.equals("/boardGoodCheck")) {
			command = new BoardGoodCheckCommand();
			command.execute(request, response);
			
			return;
		}
		// 게시판 좋아요 눌렀을 시 증가 처리 내용 (중복허용)
		else if(com.equals("/boardGoodCheckPlus")) {
			command = new BoardGoodCheckPlusCommand();
			command.execute(request, response);
			return;
		}
		// 게시판 좋아요 눌렀을 시 감소 처리 내용 (중복허용)
		else if(com.equals("/boardGoodCheckMinus")) {
			command = new BoardGoodCheckMinusCommand();
			command.execute(request, response);
			return;
		}
		// 작성한 게시글 수정 View 화면으로 가기
		else if(com.equals("/boardUpdate")) {
			command = new BoardUpdateCommand();
			command.execute(request, response);
			
			viewPage += "/boardUpdate.jsp";
		}
		// 작성한 글 수정 처리
		else if(com.equals("/boardUpdateOk")) {
			command = new BoardUpdateOkCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		// 작성한 글 삭제 처리
		else if(com.equals("/baordDelete")) {
			command = new BaordDeleteCommand();
			command.execute(request, response);
			
			viewPage = "/include/msg.jsp";
		}
		// 게시판 검색 기능
		else if(com.equals("/boardSearch")) {
			command = new BoardSearchCommand();
			command.execute(request, response);
			
			viewPage += "/boardSearchList.jsp";
		}
		// 댓글 글 작성 DB저장 처리
		else if(com.equals("/boardReplyInput")) {
			command = new BoardReplyInputCommand();
			command.execute(request, response);
			
			return;
		}
		// 작성된 댓글 삭제처리
		else if(com.equals("/boardReplyDelete")) {
			command = new BoardReplyDeleteCommand();
			command.execute(request, response);
			
			return;
		}
		// 신고 게시글 저장 (숙제 2023-11-16)
		else if(com.equals("/complaint")) {
			command = new ComplaintCommand();  
			command.execute(request, response);
			return;
		}
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
