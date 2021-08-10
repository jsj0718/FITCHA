package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fitcha.model.dbconn.DBConnect;

public class MovieAndStaffDAO {
    public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int insertMovieAndStaff(int movieId, int staffId, String role, String roleName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "INSERT INTO MOVIEANDSTAFF (masId, movieId, staffId, role, roleName)"
                + " VALUES (MOVIEANDSTAFF_MASID_SEQ.NEXTVAL, ?, ?, ?, ?)";
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, movieId);
            pstmt.setInt(2, staffId);
            pstmt.setString(3, role);
            pstmt.setString(4, roleName);
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, pstmt, conn);
        }
        return -1; // DB 오류
    }
}
