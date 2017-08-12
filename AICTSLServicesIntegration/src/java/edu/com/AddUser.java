package edu.com;
import edu.db.com.*;
import edu.db.com.SLQController;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177215)

@WebServlet(name = "AddUser", urlPatterns = {"/AddUser"})
// upload file's size up to 16MB
public class AddUser extends HttpServlet {
    private static final int BUFFER_SIZE = 4096;
    
private Connection con;
   private SLQController sqlController;
   private ResultSet rs;
   private PreparedStatement ps;
 SLQController controller=new SLQController();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //get values of text fields

        PrintWriter out = response.getWriter();
        String tagid = request.getParameter("tagid");
        String uname = request.getParameter("uname");
        String dofValid = request.getParameter("dov");
          String message = null;
        double amnt = Double.parseDouble(request.getParameter("amount"));
        InputStream inputStream = null; // input stream of the upload file
         out.print(tagid);
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
       out.print(filePart);
        
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        }

               // message will be sent back to client
            sqlController=new SLQController();
            out.println("Tagid id"+tagid);
            out.println("User Name "+uname);
            out.println("amount"+amnt);
            out.println("Photo "+inputStream);
            int i=0;
            try{
            i=sqlController.addUser(tagid, uname, amnt,dofValid,inputStream);
            if(i>0){
               HttpSession session=request.getSession();  
            session.setAttribute("total_user",controller.getTotalUser());  
            
                getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
                
            }else
                getServletContext().getRequestDispatcher("/c_panal.jsp").forward(request, response);
            }catch(Exception e){}
            }
}