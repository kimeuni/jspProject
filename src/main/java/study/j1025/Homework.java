package study.j1025;
 
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Homework1025")
public class Homework extends HttpServlet {
    private String String;
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        //1~100까지의 합
        int tot = 0;
        for(int i=1; i<=100; i++) {
            tot += i;
        }
        // 1~3단 구구단
        String str = "";
        int cnt = 0;
        for(int i=1; i<=9; i++) {
            // cnt가 4가 될때마다 줄 내리기
            if(cnt == 4) {
                str += "<br/>\n";
                cnt=0;
            }
            cnt++;
            str += "<div style=\"display:inline-block; width:200px; border:1px solid gray;\">\n";
            str += "** "+i+"단 **<br/>\n";
            for(int j=0; j<10; j++) {
                str += +i+"*"+j+ "=" +i*j+"<br/>\n";
            }
            str+="</div>\n";
        }
        
                
        out.println("서블릿 숙제합니다.<br/><p/>\n");
        out.println("<input type='button' value='출력' onclick='demo1.innerHTML="+tot+"'/>\n");
        out.println("<input type='button' value='구구단' onclick='demo2.innerHTML=`"+str+"`'/>\n");
        out.println("<hr/>\n");
        out.println("<div id='demo1'>버튼을 누를 시, 1~100까지의 합이 나옵니다.</div>\n");
        out.println("<div id='demo2'>버튼을 누를 시, 1~100까지의 합이 나옵니다.</div>\n");
        out.println("<hr/>");
        out.println("<b>** 1~3단 ** 구구단을 출력합니다. </b><p/>");
//        out.println(str);
    }
}