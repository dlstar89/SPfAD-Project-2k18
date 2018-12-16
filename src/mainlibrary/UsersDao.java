package mainlibrary;

import utils.UtilLibLogger;

import java.io.IOException;
import java.sql.*;
import utils.UtilHashing;

/**
 * @author bikash
 */
public class UsersDao {
    private UsersDao() {
    }

    public static boolean validate(String name, String password) {
        boolean status = false;

        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select * from Users where UserName=?")) {
                ps.setString(1, name);
                try (ResultSet rs = ps.executeQuery()) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int colnum = rsmd.getColumnCount();
                    String[] row;
                    row = new String[colnum];
                    while (rs.next()) {
                        for (int i = 1; i <= colnum; i++) {
                            row[i - 1] = rs.getString(i);
                        }
                        String passHash = row[1];
                        String passSalt = row[2];
                        
                        status = utils.UtilHashing.identicalHash(password, passHash, passSalt);
                    }
                } catch (SQLException e) {
                    UtilLibLogger.logMessageSEVERE(UsersDao.class, e.toString());
                }
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(UsersDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(UsersDao.class, e.toString());
        }

        return status;
    }
    
    public static boolean validate_OLD(String name, String password) {
        boolean status = false;

        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select * from Users where UserName=? and UserPass=?")) {
                ps.setString(1, name);
                ps.setString(2, password);
                try (ResultSet rs = ps.executeQuery()) {
                    status = rs.next();
                } catch (SQLException e) {
                    UtilLibLogger.logMessageSEVERE(UsersDao.class, e.toString());
                }
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(UsersDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(UsersDao.class, e.toString());
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
                    UtilLibLogger.logMessageSEVERE(UsersDao.class, e.toString());
                }
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(UsersDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(UsersDao.class, e.toString());
        }
        return status;
    }

    public static int addUser(String user, String userPass, String userEmail, String date) {
        int status = 0;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("insert into Users(UserPassHash, userPassSalt,RegDate,UserName,Email) values(?,?,?,?,?)")) {
                String passSalt = UtilHashing.getNextSalt();
                String passHash = UtilHashing.saltedHash(userPass, passSalt);         
                ps.setString(1, passHash);
                ps.setString(2, passSalt);
                ps.setString(3, date);
                ps.setString(4, user);
                ps.setString(5, userEmail);
                status = ps.executeUpdate();
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(UsersDao.class, e.toString());
            }
        } catch (Exception e) {
            UtilLibLogger.logMessageSEVERE(UsersDao.class, e.toString());
        }
        return status;
    }
}
