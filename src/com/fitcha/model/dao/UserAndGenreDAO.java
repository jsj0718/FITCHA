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
    public int mergeDips(String userId, int genreId, String operator) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "MERGE "
                + "    INTO USERANDGENRE UAG "
                + "    USING DUAL "
                + "    ON (UAG.USERID = '"+userId+"' AND UAG.GENREID = "+genreId+") "
                + "    WHEN MATCHED THEN "
                + "                UPDATE "
                + "                SET UAG.COUNT = UAG.COUNT "+operator+" 1 "
                + "                WHERE UAG.USERID = '"+userId+"' "
                + "                AND UAG.GENREID = "+genreId+" "
                + "    WHEN NOT MATCHED THEN "
                + "                INSERT (USERID, GENREID, COUNT) "
                + "                VALUES ('"+userId+"', "+genreId+", 1)";
        
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
