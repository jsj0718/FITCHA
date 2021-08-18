package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fitcha.model.dbconn.DBConnect;
import com.fitcha.model.vo.MainBoardVO;



public class MainBoardDAO {

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

	//전체 게시판에 게시글 불러오는 메소드
	public List<MainBoardVO> allBoard(){
		Connection conn = DBConnect.getInstance();
		Statement stmt = null;
		ResultSet rs = null;

		String sql ="SELECT B.BOARDID, B.USERID, M.TITLE, M.POSTER"
				+ " FROM BOARD B, MOVIE M"
				+ " WHERE B.MOVIEID = M.MOVIEID"
				+ " AND B.FLAG = 'O'";

		List<MainBoardVO> blist= new ArrayList<MainBoardVO>();
		try {
			//prepared는 값 넣을 때필요
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				MainBoardVO bvo = new MainBoardVO();
				bvo.setBoardId(rs.getInt(1));
				bvo.setUserId(rs.getString(2));
				bvo.setTitle(rs.getString(3));
				bvo.setPoster(rs.getString(4));

				blist.add(bvo);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, null, stmt, rs);

		}
		return blist;
	}	



	//BEST게시물 보여주는 메소드
	public MainBoardVO bestBoard(){
		Connection conn = DBConnect.getInstance();
		Statement stmt = null;
		ResultSet rs = null;
		MainBoardVO bvo = null;

		String sql ="SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
				+" FROM BOARD B, MOVIE M"
				+" WHERE B.MOVIEID = M.MOVIEID"
				+" AND B.FLAG = 'O'"
				+" AND B.LIKES = (SELECT MAX(LIKES) FROM BOARD)"
				+" AND ROWNUM = 1";

		try {
			//prepared는 값 넣을 때필요
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				bvo = new MainBoardVO();
				bvo.setUserId(rs.getString(1));
				bvo.setTitle(rs.getString(2));
				bvo.setPoster(rs.getString(3));
				bvo.setBoardId(rs.getInt(4));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, null, stmt, rs);

		}
		return bvo;
	}	



	//최신리뷰순 게시물
	public List<MainBoardVO> latestBoard(String genreId){
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<MainBoardVO> blist= new ArrayList<MainBoardVO>();
		String sql ="";

		if(genreId.equals("0")) {
			//모든장르,최신리뷰순
			sql = " SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
					+ " FROM BOARD B, MOVIE M"
					+ " WHERE B.MOVIEID = M.MOVIEID"
					+ " AND B.FLAG = 'O'"
					+ " ORDER BY B.BOARDID DESC";

			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					MainBoardVO bvo = new MainBoardVO();
					bvo.setUserId(rs.getString(1));
					bvo.setTitle(rs.getString(2));
					bvo.setPoster(rs.getString(3));
					bvo.setBoardId(rs.getInt(4));

					blist.add(bvo);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeAll(conn, null, stmt, rs);

			}
		}else {
			//장르선택, 최신리뷰순
			sql =" SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
					+ " FROM BOARD B, MOVIE M, MOVIEANDGENRE MG"
					+ " WHERE B.MOVIEID = MG.MOVIEID"
					+ " AND B.MOVIEID = M.MOVIEID"
					+ " AND B.FLAG = 'O'"
					+ " AND MG.GENREID = ?"
					+ " ORDER BY B.BOARDID DESC";
			try {
				//prepared는 값 넣을 때필요
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, genreId);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					MainBoardVO bvo = new MainBoardVO();
					bvo.setUserId(rs.getString(1));
					bvo.setTitle(rs.getString(2));
					bvo.setPoster(rs.getString(3));
					bvo.setBoardId(rs.getInt(4));

					blist.add(bvo);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeAll(conn, pstmt, null, rs);
			}
		}
		return blist;
	}	



	//추천순 게시물
	public List<MainBoardVO> recommendBoard(String genreId){
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<MainBoardVO> blist= new ArrayList<MainBoardVO>();
		String sql ="";

		if(genreId.equals("0")) {
			//모든장르, 추천순
			sql = " SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
					+ " FROM BOARD B, MOVIE M"
					+ " WHERE B.MOVIEID = M.MOVIEID"
					+ " AND B.FLAG = 'O'"
					+ " ORDER BY B.LIKES DESC";

			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					MainBoardVO bvo = new MainBoardVO();
					bvo.setUserId(rs.getString(1));
					bvo.setTitle(rs.getString(2));
					bvo.setPoster(rs.getString(3));
					bvo.setBoardId(rs.getInt(4));

					blist.add(bvo);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeAll(conn, null, stmt, rs);

			}
		}else {
			//장르별, 추천순
			sql =" SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
					+ " FROM BOARD B, MOVIE M, MOVIEANDGENRE MG"
					+ " WHERE B.MOVIEID = MG.MOVIEID"
					+ " AND B.MOVIEID = M.MOVIEID"
					+ " AND B.FLAG = 'O'"
					+ " AND MG.GENREID = ?"
					+ " ORDER BY B.LIKES DESC";

			try {
				//prepared는 값 넣을 때필요
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, genreId);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					MainBoardVO bvo = new MainBoardVO();
					bvo.setUserId(rs.getString(1));
					bvo.setTitle(rs.getString(2));
					bvo.setPoster(rs.getString(3));
					bvo.setBoardId(rs.getInt(4));

					blist.add(bvo);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeAll(conn, pstmt, null, rs);
			}
		}
		return blist;
	}			




	//평점순 게시물
	public List<MainBoardVO> rateBoard(String genreId){
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<MainBoardVO> blist= new ArrayList<MainBoardVO>();
		String sql ="";
		if(genreId.equals("0")) {
			//모든장르, 평점순
			sql = " SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
					+ " FROM BOARD B, MOVIE M"
					+ " WHERE B.MOVIEID = M.MOVIEID"
					+ " AND B.FLAG = 'O'"
					+ " ORDER BY M.RATE DESC";

			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					MainBoardVO bvo = new MainBoardVO();
					bvo.setUserId(rs.getString(1));
					bvo.setTitle(rs.getString(2));
					bvo.setPoster(rs.getString(3));
					bvo.setBoardId(rs.getInt(4));

					blist.add(bvo);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeAll(conn, null, stmt, rs);

			}
		}else {
			//장르별, 평점순	
			sql =" SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
					+ " FROM BOARD B, MOVIE M, MOVIEANDGENRE MG"
					+ " WHERE B.MOVIEID = MG.MOVIEID"
					+ " AND B.MOVIEID = M.MOVIEID"
					+ " AND B.FLAG = 'O'"
					+ " AND MG.GENREID = ?"
					+ " ORDER BY M.RATE DESC";

			try {
				//prepared는 값 넣을 때필요
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, genreId);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					MainBoardVO bvo = new MainBoardVO();
					bvo.setUserId(rs.getString(1));
					bvo.setTitle(rs.getString(2));
					bvo.setPoster(rs.getString(3));
					bvo.setBoardId(rs.getInt(4));

					blist.add(bvo);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeAll(conn, pstmt, null, rs);

			}
		}
		return blist;
	}	



	//러닝타임 짧은순 게시물
	public List<MainBoardVO> runningTimeBoard(String genreId){
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<MainBoardVO> blist= new ArrayList<MainBoardVO>();
		String sql ="";
		if(genreId.equals("0")) {
			//모든장르, 러닝타임 짧은순
			sql = " SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
					+ " FROM BOARD B, MOVIE M"
					+ " WHERE B.MOVIEID = M.MOVIEID"
					+ " AND B.FLAG = 'O'"
					+ " ORDER BY M.RATE DESC";

			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					MainBoardVO bvo = new MainBoardVO();
					bvo.setUserId(rs.getString(1));
					bvo.setTitle(rs.getString(2));
					bvo.setPoster(rs.getString(3));
					bvo.setBoardId(rs.getInt(4));

					blist.add(bvo);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeAll(conn, null, stmt, rs);

			}
		}else {
			//장르별, 러닝타임 짧은순
			sql =" SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
					+ " FROM BOARD B, MOVIE M, MOVIEANDGENRE MG"
					+ " WHERE B.MOVIEID = MG.MOVIEID"
					+ " AND B.MOVIEID = M.MOVIEID"
					+ " AND B.FLAG = 'O'"
					+ " AND MG.GENREID = ?"
					+ " ORDER BY M.RUNNINGTIME";

			try {
				//prepared는 값 넣을 때필요
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, genreId);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					MainBoardVO bvo = new MainBoardVO();
					bvo.setUserId(rs.getString(1));
					bvo.setTitle(rs.getString(2));
					bvo.setPoster(rs.getString(3));
					bvo.setBoardId(rs.getInt(4));

					blist.add(bvo);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeAll(conn, pstmt, null, rs);

			}
		}
		return blist;
	}	
	
	
	//메인게시판에서 영화제목으로 검색
	   public List<MainBoardVO> MainBoardTitleSearch(String searchTitle) {
	      Connection conn = DBConnect.getInstance();
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      List<MainBoardVO> blist= new ArrayList<MainBoardVO>();

			String sql ="SELECT B.BOARDID, B.USERID, M.TITLE, M.POSTER"
					+ " FROM BOARD B, MOVIE M"
					+ " WHERE B.MOVIEID = M.MOVIEID"
					+ " AND B.FLAG = 'O'"
					+ " AND M.TITLE LIKE ?";
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1,"%"+searchTitle+"%");

	         // resultSet
	         rs = pstmt.executeQuery();
	           while(rs.next()) {
	               MainBoardVO bvo = new MainBoardVO();
	               bvo.setBoardId(rs.getInt(1));
	               bvo.setUserId(rs.getString(2));
	               bvo.setTitle(rs.getString(3));
	               bvo.setPoster(rs.getString(4));
	               
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
	
		
		
	//상세리뷰 보여주는 메소드
	public MainBoardVO reviewDetail(String boardId){
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MainBoardVO bvo =null;
		int boardIdInt = Integer.parseInt(boardId);
		String sql ="SELECT B.USERID, B.TITLE, B.CONTENT, B.BDATE, M.STORY, M.POSTER, B.BOARDID"
				+ " FROM BOARD B, MOVIE M"
				+ " WHERE M.MOVIEID = B.MOVIEID"
				+ " AND B.BOARDID = ?";

		try {
			//prepared는 값 넣을 때필요
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdInt);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bvo = new MainBoardVO();
				bvo.setUserId(rs.getString(1));
				bvo.setTitle(rs.getString(2));
				bvo.setContent(rs.getString(3));
				bvo.setBdate(rs.getString(4));
				bvo.setStory(rs.getString(5));
				bvo.setPoster(rs.getString(6));
				bvo.setBoardId(rs.getInt(7));


			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null, rs);

		}
		return bvo;
	}	
	
	//게시물 update
	public int updateBoard(String boardId, String title, String content){
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		int result=0;
		MainBoardVO bvo =null;
		int boardIdInt = Integer.parseInt(boardId);
		String sql ="UPDATE BOARD"
				+ " SET TITLE= ?, CONTENT= ?"
				+ " WHERE BOARDID=?";

		try {
			//prepared는 값 넣을 때필요
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, boardIdInt);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null, null);

		}
		return result;
	}	
	
	//게시물 delete
	public int deleteBoard(int boardId) {
		//conn
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		int result=0;
	
		//sql
		String sql = "DELETE FROM BOARD WHERE BOARDID =?";
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
	
	//게시물 좋아요
	public int likeBoard(String boardId){
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		int result=0;
		MainBoardVO bvo =null;
		int boardIdInt = Integer.parseInt(boardId);
		String sql ="UPDATE BOARD"
				+ " SET LIKES = LIKES + 1"
				+ " WHERE BOARDID = ?";

		try {
			//prepared는 값 넣을 때필요
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdInt);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null, null);

		}
		return result;
	}	
	
	
	//조회수 증가
	public int viewBoard(String boardId){
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		int result=0;
		MainBoardVO bvo =null;
		int boardIdInt = Integer.parseInt(boardId);
		String sql ="UPDATE BOARD"
				+ " SET VIEWS = VIEWS + 1"
				+ " WHERE BOARDID = ?";

		try {
			//prepared는 값 넣을 때필요
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdInt);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null, null);

		}
		return result;
	}	
	
	
	
	
	//totalContent값 구하기
	public int selectBoardCnt() {
		
		Connection conn = DBConnect.getInstance();
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		
		String sql = "SELECT COUNT(*) FROM BOARD WHERE FLAG = 'O'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, null, stmt, rs);
		}
		return result;
				
	}
	
	public List<MainBoardVO> selectBoardPage(int start, int end){
		//conn
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MainBoardVO> blist = new ArrayList<MainBoardVO>();
		
		//sql
		String sql = "SELECT * "
				+ "FROM (SELECT ROWNUM AS RNUM, A.* "
				+ "      FROM(SELECT * "
				+ "            FROM BOARD "
				+ "            ORDER BY BOARDID DESC) A "
				+ "       ) "
				+ "WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MainBoardVO bvo = new MainBoardVO();
				bvo.setBoardId(rs.getInt("BOARDID"));
				bvo.setTitle(rs.getString("TITLE"));
				bvo.setContent(rs.getString("CONTENT"));
				bvo.setUserId(rs.getString("USERID"));
				bvo.setBdate(rs.getString("BDATE"));
					
				blist.add(bvo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null, rs);
		}
		return blist;
		
	}

}
