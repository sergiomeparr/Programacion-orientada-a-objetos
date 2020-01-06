import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class prueba extends HttpServlet {

public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
	
	res.setContentType("text/html");
    PrintWriter out = res.getWriter();
	
	int a=Integer.parseInt(req.getParameter("numero1"));
	int b=Integer.parseInt(req.getParameter("numero2"));
	out.println("<HTML>");
    out.println("<BODY>");
	for(int i=a; i<=b; i++)
	{	
		out.println(i + " " + i*i+"<p>");
		
	}
    out.println("</BODY></HTML>");
	}
	
	public String getServletInfo() {
    return "A servlet that knows the name of the person to whom it's" + 
           "saying hello";
  }
}