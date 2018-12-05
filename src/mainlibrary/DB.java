/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainlibrary;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import envproperties.EnvProperties;

/**
 *
 * @author bikash
 */
public class DB {

    private static String user;
    private static String password;
    private final static String connection = "jdbc:mysql://localhost:3306/library";
    private final static String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws IOException {
        Connection con = null;
        try {
            user = EnvProperties.getEnvProperty("SQL_USERNAME");
            password = EnvProperties.getEnvProperty("SQLPASS");

            Properties props = new Properties();
            props.put("user", user);
            props.put("password", password);
            props.put("useUnicode", "true");
            props.put("useServerPrepStmts", "false"); // use client-side prepared statement
            props.put("characterEncoding", "UTF-8"); // ensure charset is utf8 here

            Class.forName(driver);
            con = DriverManager.getConnection(connection, props);
        } catch (SQLException sql_e) {
            System.out.println(sql_e);
        } catch (Exception e) {
            System.out.println(e);
        }

        return con;
    }

}
