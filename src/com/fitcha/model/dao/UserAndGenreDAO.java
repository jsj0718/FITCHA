package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fitcha.model.dbconn.DBConnect;

public class UserAndGenreDAO {
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
    
    // 유저-장르 테이블 누적
    public int insertDips(String userId, int genreId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "MERGE "
                + "    INTO USERANDGENRE UAG "
                + "    USING DUAL "
                + "    ON (UAG.USERID = ? AND UAG.GENREID = ?) "
                + "    WHEN MATCHED THEN "
                + "                UPDATE "
                + "                SET UAG.COUNT = COUNT + 1 "
                + "                WHERE UAG.USERID = ? "
                + "                AND UAG.GENREID = ? "
                + "    WHEN NOT MATCHED THEN "
                + "                INSERT (USERID, GENREID, COUNT) "
                + "                VALUES (?, ?, 1)";
        
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            
            pstmt.setString(1, userId);
            pstmt.setInt(2, genreId);
            
            pstmt.setString(3, userId);
            pstmt.setInt(4, genreId);
            
            pstmt.setString(5, userId);
            pstmt.setInt(6, genreId);
            
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, pstmt, conn);
        }
        return -1; // DB 오류
    }
    
}
