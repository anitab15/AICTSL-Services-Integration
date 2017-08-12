
package edu.com;
import edu.db.com.SLQController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {
    
    
SLQController controller=new SLQController();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name=request.getParameter("name");
        int bal=Integer.parseInt(request.getParameter("amnt"));
        try{
    boolean b=controller.addUser(name, bal);
    if(!b)
          response.sendRedirect("success.html");       
    else
        out.println("Wrong....");
    }
    catch(Exception e){out.print("wrong.."+e);}
   }
  }

