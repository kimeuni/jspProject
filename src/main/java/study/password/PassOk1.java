package study.password;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.SecurityUtil;

@WebServlet("/password/passOk1")
public class PassOk1 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")== null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")== null ? "" : request.getParameter("pwd").toUpperCase();
		int idx = request.getParameter("idx")== null ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		System.out.println("= 원본 자료 =");
		System.out.println("mid : " + mid);
		System.out.println("pwd : " + pwd);
		System.out.println("idx : " + idx);
		
		if(idx == 1) {
			// 숫자만을 암호화 하는 경우
			// 자바에서 16진수 저장하는 명령어 : 0x...
			// 암호화를 위한 암호화키(0x1234ABCD)
			int key = 0x1234ABCD;
			
			int encPwd, decPwd;  //enc : 암호화  / dec : 복호화
			
			// 암호화...(EOR(배타적OR) 암호화) => 이진수 EOR : ^
			encPwd = Integer.parseInt(pwd) ^ key;
			//10자리 이내 가능..
			System.out.println("= 암호화된 비밀번호 =");
			System.out.println("mid : " + mid);
			System.out.println("pwd : " + encPwd);
			System.out.println("idx : " + idx);
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('원본 비번 : "+pwd+"\\n 암호화된 비번 : "+encPwd+"')");
			out.println("location.href='"+request.getContextPath()+"/study/password/passForm.jsp';");
			out.println("</script>");
			// 복호화...
			decPwd = encPwd ^ key;
			
			System.out.println("= 복호화된 비밀번호 =");
			System.out.println("mid : " + mid);
			System.out.println("pwd : " + decPwd);
			System.out.println("idx : " + idx);
		}
		else if (idx == 2) {
			System.out.println();
			// 숫자와 영문 대/소문자의 혼합(아스키코드 2자리로 변환후 암호화 처리하도록 한다. : 영문소문자로 입력시는 대문자로 변경해서 처리하도록 하겠음)
			long intPwd;
			String strPwd = "";
			System.out.println("1.원본 비밀번호(예:ABCD) : " + pwd);
			for(int i=0; i<pwd.length(); i++) {
				intPwd = pwd.charAt(i); // 한글자 거내서 넣음
				strPwd += intPwd;
			}
			System.out.println("2.ASCII코드문자로 변환된 비밀번호(예:65666768) : " + strPwd);
			
			intPwd = Long.parseLong(strPwd); //숫자로 변환해야 연산이 되기 때문에 바꿈 //레퍼클레스
			
			long encPwd, key = 0x1234ABCD;
			
			encPwd = intPwd ^ key;
			strPwd = String.valueOf(encPwd); // 문자로 바꿈
			System.out.println("3.암호화된 비밀번호(예:____) : " + strPwd);
			// 암호화된 비번과 암호화키를 db에 저장시켜놓는다.
			
			//복호화(복호화 되는 비번은 숫자이기에 2개씩 분리시켜서 처리한다.)
			long decPwd;
			
			intPwd = Long.parseLong(strPwd);  //숫자로 바꿈
			decPwd = intPwd ^ key;

			// 복호화된 비번
			System.out.println("복호화된 비번 : " + decPwd);
			
			// 복호화(복호화되는 비번은 숫자이기에 2개씩 분리시켜서 처리한다.)
			strPwd = String.valueOf(decPwd); // 문자로 바꿈
			
			char ch;
			String result = "";
			for(int i=0; i<strPwd.length(); i+=2) {  //2개씩 건너띄려고
				ch =(char) Integer.parseInt(strPwd.substring(i,i+2)); //2글자씩 잘라서 가져오기
				result += ch;
			}
			System.out.println("최종적으로 복호화된 비밀번호 : "+ result);
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('원본 비번 : "+pwd+"\\n 암호화된 비번 : "+encPwd+"')");
			out.println("location.href='"+request.getContextPath()+"/study/password/passForm.jsp';");
			out.println("</script>");
		}
		else if (idx == 3) {
			//잘 알아둘 것 JSP에서 계속 이 암호화 방식을 사용할거라고 함.
			SecurityUtil security = new SecurityUtil();  // common패키지 안에 있는 java파일
			String shaPwd = security.encryptSHA256(pwd);
			
			System.out.println("원본 pwd : " + pwd);
			System.out.println("암호화 pwd " + shaPwd);
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('원본 비번 : "+pwd+"\\n 암호화된 비번 : "+shaPwd+"')");
			out.println("location.href='"+request.getContextPath()+"/study/password/passForm.jsp';");
			out.println("</script>");
		}
	}
}
