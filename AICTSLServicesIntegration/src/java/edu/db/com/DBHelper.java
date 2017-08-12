package edu.db.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBHelper {
    public Connection getMyConn() throws ClassNotFoundException, SQLException {
Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost:3306/gsits";
 Connection con=DriverManager.getConnection(url, "root", "");
    return  con;
    }
    public static Connection getMyStaticConn() throws ClassNotFoundException, SQLException {
Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost:3306/gsits";
 Connection con=DriverManager.getConnection(url, "root", "");
    return  con;
    }
}
