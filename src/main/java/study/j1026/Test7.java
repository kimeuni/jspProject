package study.j1026;

import java.awt.font.ImageGraphicAttribute;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.DefaultBoundedRangeModel;
import javax.tools.DocumentationTool.Location;

@WebServlet("/1026/test7")
public class Test7 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest reqeuset, HttpServletResponse response) throws ServletException, IOException {
		String imgChoi= reqeuset.getParameter("name");
		String imgChan = "";
		
		if(imgChoi.equals("그림1")) {
			imgChan= "1.png";
		}
		else if(imgChoi.equals("그림2")){
			imgChan= "2.jpg";
		}
		else if(imgChoi.equals("그림3")){
			imgChan= "3.jpg";
		}
		else if(imgChoi.equals("그림4")){
			imgChan= "4.jpg";
		}
		
		response.sendRedirect(reqeuset.getContextPath()+"/study/1026/test7.jsp?imgName="+imgChan);
	}
}
	