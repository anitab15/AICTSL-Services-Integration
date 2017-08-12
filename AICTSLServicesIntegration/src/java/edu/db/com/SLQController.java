package edu.db.com;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class SLQController {
    
    DBHelper bHelper;
     Connection con=null;
     Statement st=null;
     ResultSet rs=null;
     ArrayList al;
     //----ADMIN ADDED....
     //   public int addUser(String tagid,String uname,double amnt,String dofValid,InputStream inputStream) throws ClassNotFoundException, SQLException{
//   //public static void main(String ...a)throws Exception{
//            con=DBHelper.getMyStaticConn();
//            String sql = "INSERT INTO admin (email,password,name,photo) values (?,?,?,?)";
//            PreparedStatement statement = con.prepareStatement(sql);
//            statement.setString(1,tagid);
//            statement.setString(2,uname);
//           /// statement.setDouble(3,amnt);
//           // java.util.Date ss=new java.util.Date();
//            //String da=ss.toString();
//            statement.setString(3,dofValid);
//            if (inputStream != null) {
//                statement.setBlob(4, inputStream);
//            }
//            int row = statement.executeUpdate();
//            if(row>0){
//            System.out.println(""+row);
//          }
//         return row;
//        }
     
     // admin login....
    public boolean getAdminLogin(String email, String pass) throws ClassNotFoundException, SQLException
{
     bHelper=new DBHelper();
    con=bHelper.getMyConn();
    st=con.createStatement();
    String q="select *from admin where email='"+email+"' and password='"+pass+"'";
    rs=st.executeQuery(q);
    //System.out.println(""+rs.next());
   return rs.next();
 }
//---- get Admin Name only 
    public String getAdminName(String email) throws ClassNotFoundException, SQLException
   {bHelper=new DBHelper();
    con=bHelper.getMyConn();
    st=con.createStatement();
    String q="select *from admin where email='"+email+"'";
    rs=st.executeQuery(q);
    if(rs.next())
         return rs.getString(3);
     else
        return "";
    }
    
    //--- get all user in 
    
     public boolean addUser(String name,int bal) throws ClassNotFoundException, SQLException
   {
     bHelper=new DBHelper();
     con=bHelper.getMyConn();
     st=con.createStatement();
     String q="Insert into user(name,amount) values('"+name+"', "+bal+")";
     return st.execute(q);
    }
     //---get Total user 
    public int getTotalUser() throws ClassNotFoundException, SQLException
   {bHelper=new DBHelper();
    con=bHelper.getMyConn();
    st=con.createStatement();
    String q="select *from user";
    rs=st.executeQuery(q);
    int totalUser=0;
    while(rs.next()){
          totalUser++;
    }
    return totalUser;
   }

     
     
//---- get user Name only 
    public String getUserName(String uid) throws ClassNotFoundException, SQLException
   {bHelper=new DBHelper();
    con=bHelper.getMyConn();
    st=con.createStatement();
    String q="select *from user where taid='"+uid+"'";
    rs=st.executeQuery(q);
    if(rs.next())
         return rs.getString(2);
     else
        return "";
    }
    //--get User Profile 
    public ArrayList getUserDetail(String uid) throws ClassNotFoundException, SQLException
   {bHelper=new DBHelper();
    con=bHelper.getMyConn();
    al=new ArrayList();
    st=con.createStatement();
    String q="select *from user where taid='"+uid+"'";
    rs=st.executeQuery(q);
    if(rs.next()){
        al.add(rs.getString(2));
        al.add(rs.getInt(3));
        al.add(rs.getString(4));
      return al;
    }
     else
        return null;
    }
//get All User Detailes
     public ArrayList getAllUserDetail() throws ClassNotFoundException, SQLException
   {bHelper=new DBHelper();
    con=bHelper.getMyConn();
    
    st=con.createStatement();
    String q="select *from user";
    rs=st.executeQuery(q);
    while(rs.next()){
        al=new ArrayList();
        al.add(rs.getString(2));
        al.add(rs.getInt(3));
        al.add(rs.getString(4));
     }
        return al;
    }
    
    
    
//---- get Balance 
   public double getUser(String uid) throws ClassNotFoundException, SQLException
   {bHelper=new DBHelper();
    con=bHelper.getMyConn();
    st=con.createStatement();
    String q="select *from user where taid='"+uid+"'";
    rs=st.executeQuery(q);
    if(rs.next())
         return rs.getDouble(3);
    else
        return 0;
    }
   //---update balance 
        //---- get Balance 
   public void balUpadate(int wid,String taid) throws ClassNotFoundException, SQLException
   {bHelper=new DBHelper();
    con=bHelper.getMyConn();
    st=con.createStatement();
    String q="UPDATE user SET amount = (amount -"+wid+") WHERE taid = '"+taid+"'";
    st.executeUpdate(q);
   }
   //---SET empty bal
   public void setEmpyBal(String taid) throws ClassNotFoundException, SQLException
   {bHelper=new DBHelper();
    con=bHelper.getMyConn();
    st=con.createStatement();
    String q="UPDATE user SET amount=0 WHERE taid = '"+taid+"'";
    st.executeUpdate(q);
   }
   
   public boolean getAllUser() throws ClassNotFoundException, SQLException
   {
     bHelper=new DBHelper();
     con=bHelper.getMyConn();
     st=con.createStatement();
      String q="select *from user";
     return st.execute(q);
    }
   //prepare statmnet 
   public int addUser(String tagid,String uname,double amnt,String dofValid,InputStream inputStream) throws ClassNotFoundException, SQLException{
   //public static void main(String ...a)throws Exception{
            con=DBHelper.getMyStaticConn();
            String sql = "INSERT INTO user (taid,uname,amount,cdate,photo) values (?,?,?, ?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,tagid);
            statement.setString(2,uname);
            statement.setDouble(3,amnt);
           // java.util.Date ss=new java.util.Date();
            //String da=ss.toString();
            statement.setString(4,dofValid);
            if (inputStream != null) {
                statement.setBlob(5, inputStream);
            }
            int row = statement.executeUpdate();
            if(row>0){
            System.out.println(""+row);
          }
         return row;
        }
   public boolean expCardDate(String taid) throws ClassNotFoundException, SQLException, ParseException{
   //public static void main(String ...a)throws Exception{
    con=DBHelper.getMyStaticConn();
    String sql= "SELECT cdate FROM user WHERE taid = '"+taid+"'";
    Statement ps = con.createStatement();
            
            rs = ps.executeQuery(sql);
            String exprid="";
            if(rs.next()){
              exprid=rs.getString("cdate");
            }
            String input=exprid;//exprid; // for example
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
simpleDateFormat.setLenient(false);

java.util.Date expiry = simpleDateFormat.parse(input);
boolean expired;
        //expired = expiry.before(new java.util.Date());
                
        expired = expiry.before(new java.util.Date());
       // System.out.println("Current Day of Year: " + expired);
       return expired;
        }
   
//   public double getPay(int fr,int to) throws ClassNotFoundException, SQLException, ParseException{
//   //public static void main(String ...a)throws Exception{
//    con=DBHelper.getMyStaticConn();
//    String sql="";
//    Statement ps = con.createStatement();
//            
//            int count=0;
//            while(fr<to){
//               sql= "SELECT * FROM dist_tab where id="+fr+" ";
//               rs=ps.executeQuery(sql);
//               if(rs.next())
//                 count+=rs.getInt(4);
//               fr++;
//            }
//       return count;
//        }
}