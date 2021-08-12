package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fitcha.model.dbconn.DBConnect;

public class DipsTotalDAO {
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
    public int insertDips(int movieId, String gender, String age) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "MERGE "
                + "    INTO DIPSTOTAL DT "
                + "    USING DUAL "
                + "    ON (DT.MOVIEID = ?) "
                + "    WHEN MATCHED THEN "
                + "                UPDATE "
                + "                SET ? = ? + 1, ? = ? + 1 "
                + "                WHERE DT.MOVIEID = ? "
                + "    WHEN NOT MATCHED THEN "
                + "                INSERT (MOVIEID, ?, ?) "
                + "                VALUES (?, 1, 1)";
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, movieId);
            
            pstmt.setString(2, gender);
            pstmt.setString(3, gender);
            pstmt.setString(4, age);
            pstmt.setString(5, age);
            pstmt.setInt(6, movieId);

            pstmt.setString(7, gender);
            pstmt.setString(8, age);
            pstmt.setInt(9, movieId);
            
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, pstmt, conn);
        }
        return -1; // DB 오류
    }
}
