package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fitcha.model.dbconn.DBConnect;
import com.fitcha.model.vo.MUserVO;

public class MUserDAO {
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
	
	// 로그인
	public String signin(String id, String pw) {
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		
		String sql = "SELECT USERID " 
				+ "FROM MUSER " 
				+ "WHERE USERID = ? " 
				+ "AND USERPW = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			// resultSet
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}

		return result;

	}

	// 가입(id, pw, email)등록
	public int signup(MUserVO muservo) {
		// conn
		Connection conn = DBConnect.getInstance();

		// sql
		String sql = "INSERT INTO MUSER(USERID, USERPW, USERNAME, EMAIL, BIRTH, GENDER)" 
					+ " VALUES(?, ?, ?, ?, ?, ?)";

		int result = 0;
		PreparedStatement pstmt = null;
		try {
			// prepared
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, muservo.getUserId());
			pstmt.setString(2, muservo.getUserPw());
			pstmt.setString(3, muservo.getUserName());
			pstmt.setString(4, muservo.getEmail());
			pstmt.setDate(5, (Date) muservo.getBirth());
			pstmt.setString(6, muservo.getGender());

			// result
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return result;
	}

	// 회원가입을 위한 ID조회
	public String selectUserId(String userId) {
		// conn
		Connection conn = DBConnect.getInstance();
		// sql
		String sql = "SELECT USERID " 
				+ "FROM MUSER " 
				+ "WHERE USERID = ?";

		String result = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// prepared
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			// resultSet
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// result
				result = rs.getString(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}

		return result;

	}

}
