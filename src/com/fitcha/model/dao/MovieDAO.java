package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.fitcha.model.dbconn.DBConnect;
import com.fitcha.model.vo.MovieVO;
import com.fitcha.model.vo.StaffVO;

public class MovieDAO {
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
    
    // 영화 데이터 추가 (이미 존재하면 평점, 관객수만 업데이트)
    public int insertMovieInfo(MovieVO mvo) {
        String SQL = "MERGE "
                + "    INTO MOVIE M "
                + "    USING DUAL "
                + "    ON (M.MOVIEID = ?) "
                + "    WHEN MATCHED THEN "
                + "                UPDATE  "
                + "                SET RATE = ?, ATTENDANCE = ? "
                + "                WHERE M.MOVIEID = ? "
                + "    WHEN NOT MATCHED THEN "
                + "                INSERT (MOVIEID, TITLE, SUBTITLE, STORY, POSTER, RATE, GRADE, COUNTRY, RUNNINGTIME, OPENDATE, ATTENDANCE) "
                + "                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, mvo.getMovieId());
            
            pstmt.setDouble(2, mvo.getRate());
            pstmt.setInt(3, mvo.getAttendance());
            pstmt.setInt(4, mvo.getMovieId());
            
            pstmt.setInt(5, mvo.getMovieId());
            pstmt.setString(6, mvo.getTitle());
            pstmt.setString(7, mvo.getSubTitle());
            pstmt.setString(8, mvo.getStory());
            pstmt.setString(9, mvo.getPoster());
            pstmt.setDouble(10, mvo.getRate());
            pstmt.setString(11, mvo.getGrade());
            pstmt.setString(12, mvo.getCountry());
            pstmt.setInt(13, mvo.getRunningTime());
            pstmt.setString(14, mvo.getOpenDate());
            pstmt.setInt(15, mvo.getAttendance());
            
            return pstmt.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, pstmt, conn);
        }
        return -1; // DB 오류
    }
    
    
    public MovieVO selectMovie(int movieId) {
        String SQL = "SELECT MOVIEID, TITLE, SUBTITLE, STORY, POSTER, RATE, GRADE, COUNTRY, RUNNINGTIME, OPENDATE, ATTENDANCE "
                + "FROM MOVIE "
                + "WHERE MOVIEID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        MovieVO mvo = null;
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, movieId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                mvo = new MovieVO();
                
                mvo.setMovieId(rs.getInt(1));
                mvo.setTitle(rs.getString(2));
                mvo.setSubTitle(rs.getString(3));
                mvo.setStory(rs.getString(4));
                mvo.setPoster(rs.getString(5));
                mvo.setRate(rs.getDouble(6));
                mvo.setGrade(rs.getString(7));
                mvo.setCountry(rs.getString(8));
                mvo.setRunningTime(rs.getInt(9));
                mvo.setOpenDate(rs.getString(10));
                mvo.setAttendance(rs.getInt(11));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }

        return mvo;
    }
    
    // 개봉일 순으로 조회
    public ArrayList<MovieVO> selectMovieByOpenDate(int start, int end) {
        String SQL = "SELECT TITLE, POSTER, OPENDATE "
                + "FROM (SELECT ROWNUM AS RNUM, A.* "
                + "      FROM (SELECT TITLE, POSTER, OPENDATE "
                + "            FROM MOVIE "
                + "            WHERE OPENDATE IS NOT NULL "
                + "            ORDER BY OPENDATE DESC) A "
                + "      ) "
                + "WHERE RNUM BETWEEN ? AND ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MovieVO> mlist = new ArrayList<>();
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, start);
            pstmt.setInt(2, end);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                MovieVO mvo = new MovieVO();
                mvo.setTitle(rs.getString(1));
                mvo.setPoster(rs.getString(2));
//                mvo.setOpenDate(rs.getString(3));
                
                mlist.add(mvo);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        
        return mlist;
    }
    
    // 상영 중인 영화 수 가져오기
    public int selectMovieCountByAttendance() {
        String SQL = "SELECT COUNT(*) "
                + "FROM MOVIE "
                + "WHERE ATTENDANCE > 0";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        
        return -1;  // DB 오류
    }

    
    // 상영 중인 영화면 관객수 순으로 조회
    public ArrayList<MovieVO> selectMovieByAttendance(int start, int end) {
        String SQL = "SELECT TITLE, POSTER, ATTENDANCE "
                + "FROM (SELECT ROWNUM AS RNUM, A.* "
                + "      FROM ( "
                + "            SELECT TITLE, POSTER, ATTENDANCE "
                + "            FROM MOVIE "
                + "            WHERE ATTENDANCE > 0 "
                + "            ORDER BY ATTENDANCE DESC "
                + "            ) A "
                + "      ) "
                + "WHERE RNUM BETWEEN ? AND ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MovieVO> mlist = new ArrayList<>();
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, start);
            pstmt.setInt(2, end);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                MovieVO mvo = new MovieVO();
                mvo.setTitle(rs.getString(1));
                mvo.setPoster(rs.getString(2));
//                mvo.setOpenDate(rs.getString(3));
                
                mlist.add(mvo);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        
        return mlist;
    }
    
    // 유저가 선호하는 장르(최대 3개)를 평점순으로 추천
    public ArrayList<MovieVO> selectMovieByUser(String userId, int start, int end) {
        String SQL = "SELECT DISTINCT TITLE, POSTER "
                + "FROM (SELECT ROWNUM AS RNUM, A.* "
                + "      FROM ( "
                + "            SELECT M.TITLE, M.POSTER "
                + "            FROM MOVIEANDGENRE MAG, MOVIE M, GENRE G "
                + "            WHERE MAG.MOVIEID = M.MOVIEID "
                + "            AND MAG.GENREID = G.GENREID "
                + "            AND M.OPENDATE > '20000101' "
                + "            AND MAG.GENREID IN (SELECT * "
                + "                                FROM ( "
                + "                                      SELECT GENREID "
                + "                                      FROM USERANDGENRE "
                + "                                      WHERE USERID = ? "
                + "                                      ORDER BY COUNT DESC "
                + "                                      ) "
                + "                                WHERE ROWNUM <= 3) "
                + "            ORDER BY M.RATE DESC "
                + "            ) A "
                + "      ) "
                + "WHERE RNUM BETWEEN ? AND ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MovieVO> mlist = new ArrayList<>();
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, userId);
            pstmt.setInt(2, start);
            pstmt.setInt(3, end);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                MovieVO mvo = new MovieVO();
                mvo.setTitle(rs.getString(1));
                mvo.setPoster(rs.getString(2));
                
                mlist.add(mvo);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        
        return mlist;
    }
    
    // 성별, 나이로 선호하는 영화 조회
    public ArrayList<MovieVO> selectMovieByUserInfo(String userInfo, int start, int end) {
        String SQL = "SELECT TITLE, POSTER "
                + "     FROM MOVIE "
                + "     WHERE MOVIEID IN (SELECT MOVIEID "
                + "                         FROM (SELECT ROWNUM AS RNUM, A.* "
                + "                                 FROM ( "
                + "                                       SELECT MOVIEID "
                + "                                       FROM DIPSTOTAL "
                + "                                       ORDER BY ? DESC "
                + "                                      ) A "
                + "                               ) "
                + "                        WHERE RNUM BETWEEN ? AND ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MovieVO> mlist = new ArrayList<>();
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, userInfo);
            pstmt.setInt(2, start);
            pstmt.setInt(3, end);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                MovieVO mvo = new MovieVO();
                mvo.setTitle(rs.getString(1));
                mvo.setPoster(rs.getString(2));
                
                mlist.add(mvo);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        
        return mlist;
    }
    
    // 장르별 검색 (평점순)
    public ArrayList<MovieVO> selectMovieByGenre(int genreId, int start, int end) {
        String SQL = "SELECT * "
                + "     FROM (SELECT ROWNUM AS RNUM, A.* "
                + "             FROM ( "
                + "                  SELECT M.TITLE, M.POSTER "
                + "                  FROM MOVIEANDGENRE MAG, MOVIE M, GENRE G "
                + "                  WHERE MAG.MOVIEID = M.MOVIEID "
                + "                  AND MAG.GENREID = G.GENREID "
                + "                  AND G.GENREID = ? "
                + "                  ORDER BY M.RATE DESC "
                + "                  ) A "
                + "           ) "
                + "     WHERE RNUM BETWEEN ? AND ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MovieVO> mlist = new ArrayList<>();
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, genreId);
            pstmt.setInt(2, start);
            pstmt.setInt(3, end);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MovieVO mvo = new MovieVO();
                mvo.setTitle(rs.getString(1));
                mvo.setPoster(rs.getString(2));

                mlist.add(mvo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }

        return mlist;
    }
    
    // 영화 검색 시 관련 영화 조회 (최신순)
    public ArrayList<MovieVO> selectMovieByTitle(int title, int start, int end) {
        String SQL = "SELECT DISTINCT M.TITLE, M.POSTER "
                + "FROM MOVIEANDGENRE MAG, MOVIE M, GENRE G "
                + "WHERE MAG.MOVIEID = M.MOVIEID "
                + "AND MAG.GENREID = G.GENREID "
                + "AND M.TITLE LIKE '?' "
                + "ORDER BY M.RATE DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MovieVO> mlist = new ArrayList<>();
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, "%" + title + "%");
            pstmt.setInt(2, start);
            pstmt.setInt(3, end);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MovieVO mvo = new MovieVO();
                mvo.setTitle(rs.getString(1));
                mvo.setPoster(rs.getString(2));
                
                mlist.add(mvo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }

        return mlist;
    }
    
    // 조건에 맞는 영화 탐색
    public ArrayList<MovieVO> selectMovieBySearch(String title, String country, String genre, String order, int index) {
        String SQL = "SELECT MOVIEID, TITLE, POSTER, RATE, OPENDATE, RUNNINGTIME "
                + "FROM (SELECT ROWNUM AS RNUM, A.* "
                + "      FROM (SELECT DISTINCT M.* "
                + "            FROM (SELECT M.MOVIEID, M.TITLE, M.POSTER, M.RATE, M.OPENDATE, M.RUNNINGTIME "
                + "                  FROM MOVIE M, GENRE G, MOVIEANDGENRE MAG "
                + "                  WHERE MAG.MOVIEID = M.MOVIEID "
                + "                  AND MAG.GENREID = G.GENREID "
                + "                  AND M.TITLE LIKE ? "
                + "                  AND M.COUNTRY LIKE ? "
                + "                  AND G.GENRENAME LIKE ? "
                + "                  AND M.OPENDATE IS NOT NULL "
                + "                  AND M.COUNTRY IS NOT NULL) M "
                + "            ORDER BY " + order + " DESC) A) "
                + "WHERE RNUM BETWEEN ? AND ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MovieVO> mlist = new ArrayList<>();
        int end = index * 50;
        int start = end - 49;
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, "%" + title + "%");
            pstmt.setString(2, "%" + country + "%");
            pstmt.setString(3, "%" + genre + "%");
            pstmt.setInt(4, start);
            pstmt.setInt(5, end);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MovieVO mvo = new MovieVO();
                mvo.setMovieId(rs.getInt(1));
                mlist.add(mvo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }

        return mlist;
    }
    
}
