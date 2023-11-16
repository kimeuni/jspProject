package admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;

public class AdminComplaintListCommand implements adminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		
		ArrayList<ComplaintVO> vos = dao.getComplaintList();
		
		request.setAttribute("vos", vos);
		
	}

}
