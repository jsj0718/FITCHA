package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fitcha.model.dbconn.DBConnect;
import com.fitcha.model.vo.MovieAndStaffVO;
import com.fitcha.model.vo.MovieVO;

public class MovieAndStaffDAO {
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

    // Movie와 Staff의 연관 데이터 넣기
    public int insertMovieAndStaff(int movieId, int staffId, String role, String roleName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "INSERT INTO MOVIEANDSTAFF (masId, movieId, staffId, role, roleName)"
                + " VALUES (MOVIEANDSTAFF_MASID_SEQ.NEXTVAL, ?, ?, ?, ?)";
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, movieId);
            pstmt.setInt(2, staffId);
            pstmt.setString(3, role);
            pstmt.setString(4, roleName);
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, pstmt, conn);
        }
        return -1; // DB 오류
    }
    
    // Movie에 출연한 모든 Staff 조회
    public ArrayList<MovieAndStaffVO> selectStaffList(int movieId) {
        String SQL = "SELECT MAS.MOVIEID, MAS.STAFFID, MAS.ROLE, MAS.ROLENAME "
                + "     FROM MOVIEANDSTAFF MAS, MOVIE M, STAFF S "
                + "     WHERE MAS.MOVIEID = M.MOVIEID "
                + "     AND MAS.STAFFID = S.STAFFID "
                + "     AND M.MOVIEID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MovieAndStaffVO> maslist = new ArrayList<>();
        
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, movieId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MovieAndStaffVO masvo = new MovieAndStaffVO();
                masvo.setMovieId(rs.getInt(1));
                masvo.setStaffId(rs.getInt(2));
                masvo.setRole(rs.getString(3));
                masvo.setRoleName(rs.getString(4));
                
                maslist.add(masvo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }

        return maslist;
    }
    
}
