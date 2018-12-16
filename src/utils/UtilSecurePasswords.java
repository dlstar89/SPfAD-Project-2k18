/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import mainlibrary.AllStudent;
import mainlibrary.DB;

/**
 *
 * @author dstarcenko
 */
public class UtilSecurePasswords {

    private UtilSecurePasswords() {
    }

    public static void securePaswords() {
        UpdateUserPasswordsToHashes();
        UpdateLibrarianPasswordsToHashes();
    }

    private static void UpdateUserPasswordsToHashes() {
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select * from Users", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                try (ResultSet rs = ps.executeQuery()) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int colnum = rsmd.getColumnCount();
                    String[] row;
                    row = new String[colnum];
                    while (rs.next()) {
                        for (int i = 1; i <= colnum; i++) {
                            row[i - 1] = rs.getString(i);
                        }
                        try (PreparedStatement preparedStmt = con.prepareStatement("UPDATE Users set UserPasshash = ?, UserPassSalt = ? WHERE UserID = ?")) {
                            String passSalt = UtilHashing.getNextSalt();
                            String passHash = UtilHashing.saltedHash(row[1], passSalt);
                            preparedStmt.setString(1, passHash);
                            preparedStmt.setString(2, passSalt);
                            preparedStmt.setInt(3, Integer.parseInt(row[0]));
                            preparedStmt.execute();
                        } catch (SQLException e) {
                            UtilLibLogger.logMessageSEVERE(UtilSecurePasswords.class, e.toString());
                        }

                    }
                    UtilLibLogger.logMessageINFO(UtilSecurePasswords.class, "USER PASSWORDS SECURED!!!");
                } catch (SQLException e) {
                    UtilLibLogger.logMessageSEVERE(AllStudent.class, e.toString());
                }
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(AllStudent.class, e.toString());
            }
        } catch (SQLException | IOException e) {
            UtilLibLogger.logMessageSEVERE(AllStudent.class, e.toString());
        }
    }

    private static void UpdateLibrarianPasswordsToHashes() {
        try (Connection con = DB.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("select * from Librarian", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                try (ResultSet rs = ps.executeQuery()) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int colnum = rsmd.getColumnCount();
                    String[] row;
                    row = new String[colnum];
                    while (rs.next()) {
                        for (int i = 1; i <= colnum; i++) {
                            row[i - 1] = rs.getString(i);
                        }
                        try (PreparedStatement preparedStmt = con.prepareStatement("UPDATE Librarian set LibrarianPasshash = ?, LibrarianPassSalt = ? WHERE LibrarianID = ?")) {
                            String passSalt = UtilHashing.getNextSalt();
                            String passHash = UtilHashing.saltedHash(row[3], passSalt);
                            preparedStmt.setString(1, passHash);
                            preparedStmt.setString(2, passSalt);
                            preparedStmt.setInt(3, Integer.parseInt(row[0]));
                            preparedStmt.execute();
                        } catch (SQLException e) {
                            UtilLibLogger.logMessageSEVERE(AllStudent.class, e.toString());
                        }

                        UtilLibLogger.logMessageINFO(UtilSecurePasswords.class, "LIBRARIAN PASSWORDS SECURED!!!");
                    }
                } catch (SQLException e) {
                    UtilLibLogger.logMessageSEVERE(AllStudent.class, e.toString());
                }
            } catch (SQLException e) {
                UtilLibLogger.logMessageSEVERE(AllStudent.class, e.toString());
            }
        } catch (SQLException | IOException e) {
            UtilLibLogger.logMessageSEVERE(AllStudent.class, e.toString());
        }
    }
}
