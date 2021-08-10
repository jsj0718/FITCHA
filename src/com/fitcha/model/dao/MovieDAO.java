package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.fitcha.model.dbconn.DBConnect;
import com.fitcha.model.vo.MovieVO;

public class MovieDAO {
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

    public int insertMovieInfo(MovieVO mvo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "INSERT INTO MOVIE (movieId, title, subTitle, story, poster,"
                + " rate, grade, country, runningTime, openDate, attendance)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, mvo.getMovieId());
            pstmt.setString(2, mvo.getTitle());
            pstmt.setString(3, mvo.getSubTitle());
            pstmt.setString(4, mvo.getStory());
            pstmt.setString(5, mvo.getPoster());
            pstmt.setDouble(6, mvo.getRate());
            pstmt.setString(7, mvo.getGrade());
            pstmt.setString(8, mvo.getCountry());
            pstmt.setInt(9, mvo.getRunningTime());
            pstmt.setString(10, mvo.getOpenDate());
            pstmt.setInt(11, mvo.getAttendance());
            return pstmt.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, pstmt, conn);
        }
        return -1; // DB 오류
    }

}
