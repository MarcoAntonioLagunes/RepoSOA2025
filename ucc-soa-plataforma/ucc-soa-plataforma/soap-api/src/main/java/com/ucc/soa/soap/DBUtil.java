package com.ucc.soa.soap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/soa_universidad?useSSL=false&serverTimezone=UTC";
    private static final String USER = "TU_USUARIO";
    private static final String PASSWORD = "TU_PASSWORD";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
