package com.vsms.util;

import java.sql.*;

public class DBUtil {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/vsms", "root", "password");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}