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

import utils.UtilEnvProperties;
import utils.UtilLibLogger;

/**
 *
 * @author bikash
 */
public class DB {
    private DB(){}

    public static Connection getConnection() throws IOException {
        Connection con = null;
        try {
            String user = UtilEnvProperties.getEnvProperty("SQL_USERNAME");
            String password = UtilEnvProperties.getEnvProperty("SQL_PASS");
            String connection = UtilEnvProperties.getEnvProperty("CONNECTION_STRING_LIBRARY");
            String driver = UtilEnvProperties.getEnvProperty("CONNECTION_DRIVER");

            Properties props = new Properties();
            props.put("user", user);
            props.put("password", password);
            props.put("useUnicode", "true");
            props.put("useServerPrepStmts", "false"); // use client-side prepared statement
            props.put("characterEncoding", "UTF-8"); // ensure charset is utf8 here

            Class.forName(driver);
            con = DriverManager.getConnection(connection, props);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            UtilLibLogger.logMessageSEVERE(DB.class, e.toString());
        }

        return con;
    }

}
