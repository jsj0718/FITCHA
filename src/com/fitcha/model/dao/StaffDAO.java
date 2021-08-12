package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.fitcha.model.dbconn.DBConnect;
import com.fitcha.model.vo.MovieAndStaffVO;
import com.fitcha.model.vo.StaffVO;

public class StaffDAO {
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
    
    public int insertStaffInfo(StaffVO svo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "INSERT INTO STAFF (staffId, name, birth, img)" 
                + " VALUES (?, ?, ?, ?)";
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, svo.getStaffId());
            pstmt.setString(2, svo.getName());
            pstmt.setString(3, svo.getBirth());
            pstmt.setString(4, svo.getImg());
            
            return pstmt.executeUpdate();

        } catch(SQLIntegrityConstraintViolationException e) {
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, pstmt, conn);
        }
        return -1; // DB 오류
    }
    
    // staffid로 staff 정보 조회
    public StaffVO selectStaff(int staffId) {
        String SQL = "SELECT STAFFID, NAME, BIRTH, IMG "
                + "FROM STAFF "
                + "WHERE STAFFID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        StaffVO svo = null;
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, staffId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                svo = new StaffVO();
                svo.setStaffId(rs.getInt(1));
                svo.setName(rs.getString(2));
                svo.setBirth(rs.getString(3));
                svo.setImg(rs.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }

        return svo;
    }
}
