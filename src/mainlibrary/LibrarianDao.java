package mainlibrary;

import utils.UtilLibLogger;

import java.io.IOException;
import java.sql.*;

public class LibrarianDao {
    private LibrarianDao() { }

    public static int save(String name, String password, String email, String address, String city, String contact) {
        int status = 0;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("insert into librarian(name,password,email,address,city,contact) values(?,?,?,?,?,?)")) {
                ps.setString(1, name);
                ps.setString(2, password);
                ps.setString(3, email);
                ps.setString(4, address);
                ps.setString(5, city);
                ps.setString(6, contact);
                status = ps.executeUpdate();
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(LibrarianDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(LibrarianDao.class, e.toString());
        }

        return status;
    }

    public static int delete(int id) {
        int status = 0;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("delete from Librarian where id=?")) {
                ps.setInt(1, id);
                status = ps.executeUpdate();
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(LibrarianDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(LibrarianDao.class, e.toString());
        }

        return status;
    }

    public static boolean validate(String name, String password) {
        boolean status = false;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select * from Librarian where UserName=?")) {
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
                        String passHash = row[3];
                        String passSalt = row[4];                        
                        status = utils.UtilHashing.identicalHash(password, passHash, passSalt);
                    }
                } catch (SQLException e) {
                    UtilLibLogger.logMessageSEVERE(LibrarianDao.class, e.toString());
                }
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(LibrarianDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(LibrarianDao.class, e.toString());
        }

        return status;
    }
    
    public static boolean validate_OLD(String name, String password) {
        boolean status = false;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select * from Librarian where UserName=? and Password=?")) {
                ps.setString(1, name);
                ps.setString(2, password);
                try (ResultSet rs = ps.executeQuery()) {
                    status = rs.next();
                } catch (SQLException e) {
                    UtilLibLogger.logMessageSEVERE(LibrarianDao.class, e.toString());
                }
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(LibrarianDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(LibrarianDao.class, e.toString());
        }

        return status;
    }
}
