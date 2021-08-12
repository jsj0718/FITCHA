package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fitcha.model.dbconn.DBConnect;

public class MovieAndGenreDAO {
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

    public int insertMovieAndGenre(int movieId, int genreId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "INSERT INTO MOVIEANDGENRE (movieId, genreId)" 
                + " VALUES (?, ?)";
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, movieId);
            pstmt.setInt(2, genreId);
            return pstmt.executeUpdate();

        } catch (SQLException e) {

        } finally {
            closeAll(null, pstmt, conn);
        }
        return -1; // DB 오류
    }
}
