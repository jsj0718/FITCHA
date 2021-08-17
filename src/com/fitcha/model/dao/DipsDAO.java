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
    
    // 찜 여부 확인
    public String selectDips(String userId, int movieId, String key) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL = "SELECT "+key+" "
                + "FROM DIPS "
                + "WHERE USERID = ? "
                + "AND MOVIEID = ?";
        String result = "";
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
//            pstmt.setString(1, key);
            pstmt.setString(1, userId);
            pstmt.setInt(2, movieId);

            rs = pstmt.executeQuery();
            while(rs.next()) {
                result = rs.getString(1);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, pstmt, conn);
        }
        return null; // DB 오류
    }
    
    // 찜, 좋아요 업데이트 (모달창 기준 -> 날짜 필요 X)
    public int mergeDips(String userId, int movieId, String key, String value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "MERGE "
                + "    INTO DIPS D "
                + "    USING DUAL "
                + "    ON (D.USERID = '"+userId+"' AND D.MOVIEID = "+movieId+") "
                + "    WHEN MATCHED THEN "
                + "                UPDATE "
                + "                SET "+key+" = '"+value+"' "
                + "                WHERE D.USERID = '"+userId+"' "
                + "                AND D.MOVIEID = "+movieId+" "
                + "    WHEN NOT MATCHED THEN "
                + "                INSERT (DIPSID, USERID, MOVIEID, " + key +") "
                + "                VALUES (DIPS_DIPSID_SEQ.NEXTVAL, '"+userId+"', "+movieId+", '"+value+"')";
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
