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
import com.fitcha.model.vo.MemoVO;

public class MemoDAO {
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
	
	public int memoRegist(MemoVO mvo) {
		//conn
		Connection conn = DBConnect.getInstance();
		
		//sql
		String sql = "INSERT INTO MEMO (NO, MDATE, ID, TITLE, MEMO) "
					+"VALUES (MEMO_NO_SEQ.NEXTVAL, ?, ?, ?, ?)";
		
		//prepared
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, (Date) mvo.getMdate());
			pstmt.setString(2, mvo.getId());
			pstmt.setString(3, mvo.getTitle());
			pstmt.setString(4, mvo.getMemo());
			
			//result
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		
		return result;
		
	}
	
	public List<MemoVO> allMemo(String id) {
		//conn
		Connection conn = DBConnect.getInstance();
		
		//sql
		String sql = "SELECT * FROM MEMO WHERE ID = '"+id+"'";
		
		//prepared
		Statement stmt = null;
		ResultSet rs = null;
		List<MemoVO> mlist = new ArrayList<MemoVO>();
		
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				MemoVO mvo = new MemoVO();
				mvo.setMno(rs.getInt(1));
				mvo.setMdate(rs.getDate(2));
				mvo.setId(rs.getString(3));
				mvo.setTitle(rs.getString(4));
				mvo.setMemo(rs.getString(5));
				
				mlist.add(mvo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, null, stmt, rs);
		}
		
		return mlist;
		
	}
	
	public int updateMemo(MemoVO mvo) {
		Connection conn = DBConnect.getInstance();
		
		String sql = "UPDATE MEMO "
				+ "SET TITLE = ?, "
				+ "		MEMO = ?, "
				+ "		MDATE = ? "
				+ "WHERE NO = ? ";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mvo.getTitle());
			pstmt.setString(2, mvo.getMemo());
			pstmt.setDate(3, (Date) mvo.getMdate());
			pstmt.setInt(4, mvo.getMno());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		
		return result;
		
	}
	
	public int deleteMemo(int no) {
		Connection conn = DBConnect.getInstance();
		
		String sql = "DELETE FROM MEMO WHERE NO = ?";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, null);
		}
		
		return result;
		
	}
	
	
	
	
}
