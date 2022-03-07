package com.company.Utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class UtilityClass {
    public static Connection getConnection(){
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/testDB";
            String userName = "root";
            String password = "Tekion@123";
            con = DriverManager.getConnection(url,userName,password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
