package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fitcha.model.dbconn.DBConnect;
import com.fitcha.model.vo.BCommentVO;
import com.fitcha.model.vo.MainBoardVO;

public class BCommentDAO {

	public void closeAll(Connection conn, PreparedStatement pstmt, Statement stmt, ResultSet rs ) {
		try {
			if(rs!=null && !rs.isClosed()) {
				rs.close();
			}
			if(stmt!=null && !stmt.isClosed()) {
				stmt.close();
			}
			if(pstmt!=null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//댓글Insert
	public List<BCommentVO> insertComment(String boardId, String newComment, String userId) {
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BCommentVO> blist= new ArrayList<BCommentVO>();
		int boardIdInt = Integer.parseInt(boardId);

		String sql ="INSERT INTO BCOMMENT (COMMENTID, BOARDID, CONTENT, USERID)"
				+ " VALUES (BCOMMENT_COMMENTID_SEQ.NEXTVAL, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,boardIdInt);
			pstmt.setString(2, newComment);
			pstmt.setString(3, userId);

			// resultSet
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BCommentVO bvo = new BCommentVO();
				bvo.setCommentId(rs.getInt(1));
				bvo.setBoardId(rs.getInt(2));
				bvo.setContent(rs.getString(3));
				bvo.setUserID(rs.getString(4));

				blist.add(bvo);

			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, rs);
		}

		return blist;

	}




	//댓글Load
	public List<BCommentVO> selectComment(String boardId) {
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BCommentVO> blist= new ArrayList<BCommentVO>();
		int boardIdInt = Integer.parseInt(boardId);

		String sql ="SELECT * FROM BCOMMENT"
					+" WHERE BOARDID =? AND FLAG ='O'";
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,boardIdInt);

			// resultSet
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BCommentVO bvo = new BCommentVO();
				bvo.setCommentId(rs.getInt(1));
				bvo.setBoardId(rs.getInt(2));
				bvo.setUserID(rs.getString(3));
				bvo.setContent(rs.getString(4));
				bvo.setCdate(rs.getString("cdate"));

				blist.add(bvo);

			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, rs);
		}

		return blist;

	}
	
	
	
	//댓글delete (게시물삭제시)
	public int deleteComment(int boardId) {
		//conn
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		int result=0;
	
		//sql
		String sql = "DELETE FROM BCOMMENT WHERE BOARDID =?";
		try {
			//pstmt
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, boardId);
			
			//result
			result = pstmt.executeUpdate();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			closeAll(conn, pstmt, null, null);
		}
		return result;
		
		
	}



}
