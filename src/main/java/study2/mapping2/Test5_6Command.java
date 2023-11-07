package study2.mapping2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test5_6Command implements Test5ReInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//각종 서비스 처리 작업을 수행..
		
		// 작업 완료 후 메세지처리...
		
		request.setAttribute("msg", "이곳은 서비스 작업 완료 후 메세지를 담아서 보냅니다.");
		request.setAttribute("url", request.getContextPath()+"/asdkjklsakaskd12398289alk.askja/sad/test5.re");
		//  request.getContextPath()+"/asdkjklsakaskd12398289alk.askja/sad/test5.re" : 어차피 /test5 부분 만 읽어가기 때문에 앞에 적은 건 눈속임이다.. 
		//  request.getContextPath()+"/test5.re" 이렇게 적든 위에 처럼 적든 똑같이 간다.. (Test5Recontrollter 참고..)
	}

}
