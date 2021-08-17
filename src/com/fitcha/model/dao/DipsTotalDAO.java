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
    
    // 찜 누적 테이블 추가 혹은 제거
    public int mergeDipsTotal(int movieId, String gender, String generation, String operator) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "MERGE "
                + "    INTO DIPSTOTAL DT "
                + "    USING DUAL "
                + "    ON (DT.MOVIEID = "+movieId+") "
                + "    WHEN MATCHED THEN "
                + "                UPDATE "
                + "                SET "+gender+" = "+gender+" " + operator + " 1, "+generation+" = "+generation+" " + operator + " 1 "
                + "                WHERE DT.MOVIEID = "+movieId+" "
                + "    WHEN NOT MATCHED THEN "
                + "                INSERT (DT.MOVIEID, "+gender+", "+generation+") "
                + "                VALUES ("+movieId+", 1, 1)";
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);        
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, pstmt, conn);
        }
        return -1; // DB 오류
    }
}
