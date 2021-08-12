package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fitcha.model.dbconn.DBConnect;

public class DipsDAO {
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
    
    // 찜하기 추가
    public int insertDips(String userId, int movieId, String ddate) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "INSERT INTO DIPS (DIPSID, USERID, MOVIEID, DDATE) "
                + "VALUES (DIPS_DIPSID_SEQ.NEXTVAL, ?, ?, ?)";
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, userId);
            pstmt.setInt(2, movieId);
            pstmt.setString(3, ddate);
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, pstmt, conn);
        }
        return -1; // DB 오류
    }
}
