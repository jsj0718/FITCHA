package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fitcha.model.vo.DipsVO;
import com.fitcha.model.dbconn.DBConnect;

public class DipsDAO {
    public void closeAll(ResultSet rs, PreparedStatement pstmt, Statement stmt, Connection conn) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
			if (stmt != null && !stmt.isClosed()) {
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
            closeAll(rs, pstmt, null, conn);
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
            closeAll(null, pstmt, null, conn);
        }
        return -1; // DB 오류
    }
    

	
	public int insertDips(DipsVO dvo) {
		//conn
		Connection conn = DBConnect.getInstance();
		
		//sql
		String sql = "INSERT INTO DIPS (DIPSID, USERID, MOVIEID, DDATE) "
					+"VALUES (DIPS_DIPSID_SEQ, ?, ?, ?)";
		
		//prepared
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dvo.getDipsId());
			pstmt.setString(2, dvo.getUserId());
			pstmt.setInt(3, dvo.getMovieId());
			pstmt.setDate(4, (Date) dvo.getDdate());
			
			//result
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, null, conn);
		}
		
		return result;
		
	}
	
	
	public int updateDips(DipsVO dvo) {
		Connection conn = DBConnect.getInstance();
		
		String sql = "UPDATE DIPS "
				+ "SET MOVIEID = ?, "
				+ "	   DDATE = ? "
				+ "WHERE USERID = ? ";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dvo.getMovieId());
			pstmt.setDate(2, (Date) dvo.getDdate());
			pstmt.setString(3, dvo.getUserId());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, null, conn);
		}
		
		return result;
		
	}
	
	public int deleteDips(int dipsId) {
		Connection conn = DBConnect.getInstance();
		
		String sql = "DELETE FROM DIPS WHERE DIPSID = ?";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dipsId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, null, conn);
		}
		
		return result;
		
	}
	
	public List<DipsVO> getDipsMovies(String userId) {
		//conn
		Connection conn = DBConnect.getInstance();
		
		//sql
		String sql = "SELECT B.TITLE, B.POSTER, A.DDATE, A.DIPSID FROM DIPS A,\r\n"
				+ "MOVIE B \r\n"
				+ "WHERE 1=1\r\n"
				+ "AND A.MOVIEID = B.MOVIEID\r\n"
				+ "AND A.USERID = '"+userId+"'";
		
		//prepared
		Statement stmt = null;
		ResultSet rs = null;
		List<DipsVO> dlist = new ArrayList<DipsVO>();
		
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				DipsVO dvo = new DipsVO();
				dvo.setTitle(rs.getString(1));
				dvo.setPoster(rs.getString(2));
				dvo.setDdate(rs.getDate(3));
				dvo.setDipsId(rs.getInt(4));
				
				
				dlist.add(dvo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, null, stmt, conn);
		}
		
		return dlist;
		
	}
}

