package mainlibrary;

import utils.UtilLibLogger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDao {
    private BookDao() {
    }

    public static int save(String callno, String name, String author, String publisher, int quantity) {
        int status = 0;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("insert into books(callno,name,author,publisher,quantity) values(?,?,?,?,?)")) {
                ps.setString(1, callno);
                ps.setString(2, name);
                ps.setString(3, author);
                ps.setString(4, publisher);
                ps.setInt(5, quantity);
                status = ps.executeUpdate();
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(BookDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(BookDao.class, e.toString());
        }
        return status;
    }

    public static boolean PublisherValidate(String Publisher) {
        boolean status = false;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select * from Publisher where PublisherName = ?")) {
                ps.setString(1, Publisher);
                try (ResultSet rs = ps.executeQuery()) {
                    status = rs.next();
                } catch (SQLException e) {
                    UtilLibLogger.logMessageSEVERE(BookDao.class, e.toString());
                }
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(BookDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(BookDao.class, e.toString());
        }

        return status;
    }

    public static int AddPublisher(String Publisher) {
        int status = 0;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("insert into Publisher(PublisherName) values(?)")) {
                ps.setString(1, Publisher);
                status = ps.executeUpdate();
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(BookDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(BookDao.class, e.toString());
        }

        return status;
    }


    public static int SaveBook(String BookN, String AuthorN, String PublisherN, String ShelfN, String RowN, String GenreN) {
        int status = 0;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("insert into Books(BookName,Author,Genre,Publisher,Shelf, Row) values(?,?,?,?,?,?)")) {
                ps.setString(1, BookN);
                ps.setString(2, AuthorN);
                ps.setString(3, GenreN);
                ps.setString(4, PublisherN);
                ps.setString(5, ShelfN);
                ps.setString(6, RowN);
                status = ps.executeUpdate();
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(BookDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(BookDao.class, e.toString());
        }

        return status;
    }

    public static int Delete(int BookID) {
        int status = 0;
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM Books where BookID=?")) {
                ps.setInt(1, BookID);
                status = ps.executeUpdate();
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(BookDao.class, e.toString());
            }
        } catch (IOException | SQLException e) {
            UtilLibLogger.logMessageSEVERE(BookDao.class, e.toString());
        }

        return status;
    }
}
