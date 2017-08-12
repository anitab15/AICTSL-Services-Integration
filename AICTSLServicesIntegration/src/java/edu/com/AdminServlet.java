package edu.com;
import edu.db.com.SLQController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {
    SLQController controller=new SLQController();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            String email=request.getParameter("user");
            String pass=request.getParameter("pass");
            boolean b=controller.getAdminLogin(email, pass);
           
           if(b==true){
            HttpSession session=request.getSession();  
            session.setAttribute("email",email);
            session.setAttribute("ALL_USER",controller.getAllUserDetail());
            
            session.setAttribute("uname",controller.getAdminName(email));  
            session.setAttribute("total_user",controller.getTotalUser());  
            response.sendRedirect("c_panal.jsp");                 
           }
            else
           {
               out.println("Invalid Admin");
               response.sendRedirect("index.jsp");
           }
          }
        catch(Exception e){;}
        }
  }
