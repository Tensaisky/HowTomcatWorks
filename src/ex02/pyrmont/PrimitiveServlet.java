package ex02.pyrmont;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimitiveServlet implements Servlet {
    public void init(ServletConfig config) throws ServletException{
        System.out.println("init");
    }

    public void service(ServletRequest request , ServletResponse response) throws ServletException, IOException{
        System.out.println("from service");
        PrintWriter out = response.getWriter();
        out.println("Violets are blue.");
        out.println("Hello. Roses are red.");
    }

    public void destroy(){
        System.out.println("destroy");
    }

    public String getServletInfo(){
        return null;
    }

    public ServletConfig getServletConfig(){
        return null;
    }
}
