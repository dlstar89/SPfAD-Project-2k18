package mainlibrary;

import utils.UtilLibLogger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransBookDao {
    private TransBookDao() { }

    public static boolean checkBook(String bookcallno) {
        boolean status = false;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select * from Books where BookID=?")) {
                ps.setString(1, bookcallno);
                try (ResultSet rs = ps.executeQuery()) {
                    status = rs.next();
                } catch (SQLException e) {
                    UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
                }
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
        }

        return status;
    }

    public static boolean BookValidate(String BookID) {
        boolean status = false;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select * from Books where BookID = ?")) {
                ps.setString(1, BookID);
                try (ResultSet rs = ps.executeQuery()) {
                    status = rs.next();
                } catch (SQLException e) {
                    UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
                }
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
        }

        return status;
    }

    public static boolean UserValidate(String UserID) {
        boolean status = false;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select * from Users where UserID = ?")) {
                ps.setString(1, UserID);
                try (ResultSet rs = ps.executeQuery()) {
                    status = rs.next();
                } catch (SQLException e) {
                    UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
                }
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
        }

        return status;
    }

    public static int updatebook(String bookcallno) {
        int status = 0;
        int quantity = 0;
        int issued = 0;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select quantity,issued from books where callno=?")) {
                ps.setString(1, bookcallno);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        quantity = rs.getInt("quantity");
                        issued = rs.getInt("issued");
                        if (quantity > 0) {
                            try (PreparedStatement ps2 = con.prepareStatement("update books set quantity=?,issued=? where callno=?")) {
                                ps2.setInt(1, quantity - 1);
                                ps2.setInt(2, issued + 1);
                                ps2.setString(3, bookcallno);

                                status = ps2.executeUpdate();
                            } catch (SQLException e) {
                                UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
                            }
                        }
                    }
                } catch (SQLException e) {
                    UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
                }
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
        }
        return status;
    }

    public static int IssueBook(int BookID, int UserID, String IDate, String RDate) {
        int status = 0;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("insert into IssuedBook values(?,?,?,?)")) {
                ps.setInt(1, BookID);
                ps.setInt(2, UserID);
                ps.setString(3, IDate);
                ps.setString(4, RDate);
                status = ps.executeUpdate();
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
        }

        return status;
    }

    public static int ReturnBook(int BookID, int UserID) {
        int status = 0;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("delete from IssuedBook where BookID=? and UserID=?")) {
                ps.setInt(1, BookID);
                ps.setInt(2, UserID);
                status = ps.executeUpdate();
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
        }

        return status;
    }

    public static boolean CheckIssuedBook(int BookID) {
        boolean status = false;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select * from IssuedBook where BookID=?")) {
                ps.setInt(1, BookID);
                try (ResultSet rs = ps.executeQuery()) {
                    status = rs.next();
                } catch (SQLException e) {
                    UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
                }
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
        }

        return status;
    }

    public static int Check(int UserID) {
        int num = 0;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select * from Book_Count UserID=?")) {
                ps.setInt(1, UserID);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next())
                        num = rs.getInt("BookNo");
                } catch (SQLException e) {
                    UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
                }
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(TransBookDao.class, e.toString());
        }
        if (num == 5) {
            return 0;
        } else {
            return 1;
        }
    }

}
