
package edu.com;
import edu.db.com.SLQController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet{
SLQController controller=new SLQController();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         String taid=request.getParameter("tagid");
        
        int frm=Integer.parseInt(request.getParameter("frm"));
        int to=Integer.parseInt(request.getParameter("to"));
       
        int nofstop=0; 
        try{
        if(controller.expCardDate(taid)){
         //   out.println("Your Card is Expired ");
       controller.setEmpyBal(taid);
       HttpSession session=request.getSession();
       session.setAttribute("uname",controller.getUserName(taid));
       session.setAttribute("tagid",taid);
          response.sendRedirect("card_valid.jsp");
        }
        }catch(Exception e){}
    int amnt=0;
    
        try{
    double b=controller.getUser(taid);
    if(b>20)
    {
       nofstop=frm-to;
       if (nofstop<0)
           nofstop*=-1;
       if (nofstop>0&&nofstop<3)
       amnt=5;
           if (nofstop>2&&nofstop<5)
       amnt=10;
       
               if (nofstop>4&&nofstop<10)
       amnt=15;
       
         if (nofstop>9)
       amnt=20;
       
       
       controller.balUpadate(amnt,taid);
       controller.getUserDetail(taid);
       HttpSession session=request.getSession();
       session.setAttribute("uname",controller.getUserName(taid));
       session.setAttribute("tagid",taid);
       session.setAttribute("user_profile",controller.getUserDetail(taid));
       response.sendRedirect("success.jsp");
    }else
        out.println("Insufficient Balance..."+b);
    }
    catch(ClassNotFoundException | SQLException e)
    {
        out.print("wrong.."+e);}
    }
  }

