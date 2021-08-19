package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fitcha.model.dbconn.DBConnect;
import com.fitcha.model.vo.DipsVO;

public class DipsDAO {
	public void closeAll(Connection conn, PreparedStatement pstmt, Statement stmt, ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
			if(pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
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
			closeAll(conn, pstmt, null, null);
		}
		
		return result;
		
	}
	
//	public List<DipsVO> allDips(String userId) {
//		//conn
//		Connection conn = DBConnect.getInstance();
//		
//		//sql
//		String sql = "SELECT * FROM DIPS WHERE USERID = '"+userId+"'";
//		
//		//prepared
//		Statement stmt = null;
//		ResultSet rs = null;
//		List<DipsVO> dlist = new ArrayList<DipsVO>();
//		
//		try {
//			stmt = conn.createStatement();
//			
//			rs = stmt.executeQuery(sql);
//			
//			while(rs.next()) {
//				DipsVO dvo = new DipsVO();
//				dvo.setDipsId(rs.getInt(1));
//				dvo.setUserId(rs.getString(2));
//				dvo.setMovieId(rs.getInt(3));
//				dvo.setDdate(rs.getString(4));
//				
//				dlist.add(dvo);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			closeAll(conn, null, stmt, rs);
//		}
//		
//		return dlist;
//		
//	}
	
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
			closeAll(conn, pstmt, null, null);
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
			closeAll(conn, pstmt, null, null);
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
			closeAll(conn, null, stmt, rs);
		}
		
		return dlist;
		
	}
	

}
