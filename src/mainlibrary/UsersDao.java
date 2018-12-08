package mainlibrary;

import liblogger.LibLogger;

import java.io.IOException;
import java.sql.*;

/**
 * @author bikash
 */
public class UsersDao {
    private UsersDao() { }

    public static boolean validate(String name, String password) {
        boolean status = false;

        try (Connection con = DB.getConnection()) {
            ResultSet rs = null;
            try (PreparedStatement ps = con.prepareStatement("select * from Users where UserName=? and UserPass=?")) {
                ps.setString(1, name);
                ps.setString(2, password);
                rs = ps.executeQuery();
                status = rs.next();
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }
        } catch (IOException | SQLException e) {
            LibLogger.logMessage(e.toString());
        }
        return status;
    }

    public static boolean checkIfAlready(String userName) {
        boolean status = false;

        try (Connection con = DB.getConnection()) {
            ResultSet rs = null;
            try (PreparedStatement ps = con.prepareStatement("select * from Users where UserName=?")) {
                ps.setString(1, userName);
                rs = ps.executeQuery();
                status = rs.next();
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }
        } catch (IOException | SQLException e) {
            LibLogger.logMessage(e.toString());
        }
        return status;
    }

    public static int addUser(String user, String userPass, String userEmail, String date) {
        int status = 0;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("insert into Users(UserPass,RegDate,UserName,Email) values(?,?,?,?)")) {
                ps.setString(1, userPass);
                ps.setString(2, date);
                ps.setString(3, user);
                ps.setString(4, userEmail);
                status = ps.executeUpdate();
            }
        } catch (Exception e) {
            LibLogger.logMessage(e.toString());
        }
        return status;

    }

}
