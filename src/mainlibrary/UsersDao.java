package mainlibrary;

import liblogger.LibLogger;

import java.io.IOException;
import java.sql.*;

/**
 * @author bikash
 */
public class UsersDao {
    private UsersDao() {
    }

    public static boolean validate(String name, String password) {
        boolean status = false;

        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select * from Users where UserName=? and UserPass=?")) {
                ps.setString(1, name);
                ps.setString(2, password);
                try (ResultSet rs = ps.executeQuery()) {
                    status = rs.next();
                } catch (SQLException e) {
                    LibLogger.logMessageSEVERE(UsersDao.class, e.toString());
                }
            } catch (SQLException e) {
                LibLogger.logMessageSEVERE(UsersDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            LibLogger.logMessageSEVERE(UsersDao.class, e.toString());
        }

        return status;
    }

    public static boolean checkIfAlready(String userName) {
        boolean status = false;

        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select * from Users where UserName=?")) {
                ps.setString(1, userName);
                try (ResultSet rs = ps.executeQuery()) {
                    status = rs.next();
                } catch (SQLException e) {
                    LibLogger.logMessageSEVERE(UsersDao.class, e.toString());
                }
            } catch (SQLException e) {
                LibLogger.logMessageSEVERE(UsersDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            LibLogger.logMessageSEVERE(UsersDao.class, e.toString());
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
            } catch (SQLException e) {
                LibLogger.logMessageSEVERE(UsersDao.class, e.toString());
            }
        } catch (Exception e) {
            LibLogger.logMessageSEVERE(UsersDao.class, e.toString());
        }
        return status;
    }
}
