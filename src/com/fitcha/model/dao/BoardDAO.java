package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.fitcha.model.dbconn.DBConnect;

public class BoardDAO {
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
    
    // 리뷰 추가
    public int insertBoard(String userId, int movieId, String title, String content, String bdate, String flag) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "INSERT INTO BOARD (BOARDID, USERID, MOVIEID, TITLE, CONTENT, BDATE, FLAG) "
                + "VALUES (BOARD_BOARDID_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, userId);
            pstmt.setInt(2, movieId);
            pstmt.setString(3, title);
            pstmt.setString(4, content);
            pstmt.setString(5, bdate);
            pstmt.setString(6, flag);
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, pstmt, conn);
        }
        return -1; // DB 오류
    }
}
