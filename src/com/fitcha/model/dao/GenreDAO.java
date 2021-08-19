package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fitcha.model.dbconn.DBConnect;
import com.fitcha.model.vo.GenreVO;
import com.fitcha.model.vo.MovieVO;

public class GenreDAO {
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
    
    // 선택된 장르 조회
    public ArrayList<GenreVO> selectGenreList() {
        String SQL = "SELECT GENREID, GENRENAME "
                + "FROM GENRE "
                + "WHERE GENREID IN (1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        ArrayList<GenreVO> glist = new ArrayList<>();
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                GenreVO gvo = new GenreVO();
                gvo.setGenreId(rs.getInt(1));
                gvo.setGenreName(rs.getString(2));   
                glist.add(gvo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }

        return glist;
    }
    
    
}
