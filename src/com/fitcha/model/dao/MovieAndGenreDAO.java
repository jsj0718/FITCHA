package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fitcha.model.dbconn.DBConnect;
import com.fitcha.model.vo.MovieAndGenreVO;
import com.fitcha.model.vo.MovieAndStaffVO;

public class MovieAndGenreDAO {
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

    public int insertMovieAndGenre(int movieId, int genreId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "INSERT INTO MOVIEANDGENRE (movieId, genreId)" 
                + " VALUES (?, ?)";
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, movieId);
            pstmt.setInt(2, genreId);
            return pstmt.executeUpdate();

        } catch (SQLException e) {

        } finally {
            closeAll(null, pstmt, conn);
        }
        return -1; // DB 오류
    }
    
    // Movie에 관련된 모든 genre 조회
    public ArrayList<MovieAndGenreVO> selectGenreList(int movieId) {
        String SQL = "SELECT  G.GENREID, G.GENRENAME "
                + "     FROM MOVIEANDGENRE MAG, MOVIE M, GENRE G "
                + "     WHERE MAG.MOVIEID = M.MOVIEID "
                + "     AND MAG.GENREID = G.GENREID "
                + "     AND M.MOVIEID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MovieAndGenreVO> maglist = new ArrayList<>();
        
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, movieId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MovieAndGenreVO magvo = new MovieAndGenreVO();
                magvo.setGenreId(rs.getInt(1));
                magvo.setGenreName(rs.getString(2));
                maglist.add(magvo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }

        return maglist;
    }
}
