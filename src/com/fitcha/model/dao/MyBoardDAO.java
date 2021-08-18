package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fitcha.model.dbconn.DBConnect;
import com.fitcha.model.vo.MyBoardVO;
import com.fitcha.model.vo.MovieVO;



public class MyBoardDAO {

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
   
 
   //나의 게시판에서  recently add , 전체 글들 보기(String id)넣기
   public List<MyBoardVO> myBoardList(String id){
	   Connection conn = DBConnect.getInstance();
	   Statement stmt = null;
	   ResultSet rs = null;
	   
	   String sql ="SELECT B.BOARDID, B.USERID, M.TITLE, M.POSTER"
	            + " FROM BOARD B, MOVIE M"
	            + " WHERE B.MOVIEID = M.MOVIEID"
	            + " AND B.USERID = '"+ id +"'"
	            + " ORDER BY B.BOARDID DESC";
	          
	   
	   List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
	   
	   try {
		   //prepared는 값 넣을 때필요
		   stmt = conn.createStatement();
		   rs = stmt.executeQuery(sql);
		   
		   while(rs.next()) {
			   MyBoardVO bvo = new MyBoardVO();
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
   
   //나의 게시판에서 전체 글들 페이징 처리용
   public List<MyBoardVO> myBoardListPage(String id, int start, int end){
	   Connection conn = DBConnect.getInstance();
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   
	   String sql ="SELECT BOARDID, USERID, TITLE, POSTER "
	   		+ "FROM(SELECT ROWNUM AS RNUM, A.* FROM (SELECT B.BOARDID, B.USERID, M.TITLE, M.POSTER "
	   												+ "FROM BOARD B, MOVIE M "
	   												+ "WHERE B.MOVIEID = M.MOVIEID "
	   		+ "                                        AND B.USERID ='"+ id +"' "
													+ "ORDER BY B.BOARDID DESC) A ) "
	   		+ "WHERE RNUM BETWEEN ? AND ?";
	   
	   
	   List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
	   
	   try {
		   //prepared는 값 넣을 때필요
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setInt(1, start);
		   pstmt.setInt(2, end);
		   rs = pstmt.executeQuery();
		   
		   while(rs.next()) {
			   MyBoardVO bvo = new MyBoardVO();
			   bvo.setBoardId(rs.getInt(1));
			   bvo.setUserId(rs.getString(2));
			   bvo.setTitle(rs.getString(3));
			   bvo.setPoster(rs.getString(4));
			   
			   blist.add(bvo);
			   
		   }
		   
		   System.out.println("페이징:"+blist);
	   } catch (SQLException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }finally {
		   closeAll(conn, pstmt, null, rs);
		   
	   }
	   
	   return blist;
   }   
   //페이징 처리를 위한 게시물 수 세기
   public int selectBoardCnt(String id) {
		//conn
		Connection conn =DBConnect.getInstance();
		//sql
		String sql = "SELECT COUNT(*) "
				+ "FROM BOARD "
				+ "WHERE USERID = '"+ id +"'";
		//statement
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			stmt= conn.createStatement();
		
			//resultset
			rs= stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				//result(int)
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
   
//  나의 게시판에서 인기글 목록 , VIEWS순으로 나열
   public List<MyBoardVO> myBoardBestList(String id){
	   Connection conn = DBConnect.getInstance();
	   Statement stmt = null;
	   ResultSet rs = null;
	   
	   String sql ="SELECT B.BOARDID, B.USERID, M.TITLE, M.POSTER"
	            + " FROM BOARD B, MOVIE M"
	            + " WHERE B.MOVIEID = M.MOVIEID"
	            + " AND B.USERID = '"+ id +"'"
				+ " AND ROWNUM BETWEEN 1 AND 6"
	            + " ORDER BY B.VIEWS DESC";
	          
	   
	   List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
	
	   try {
		   //prepared는 값 넣을 때필요
		   stmt = conn.createStatement();
		   rs = stmt.executeQuery(sql);
		   
		   while(rs.next()) {
			   MyBoardVO bvo = new MyBoardVO();
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
   
   //나의게시판 검색용
   public List<MyBoardVO> search(String id, String searchTitle) {
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyBoardVO> bslist= new ArrayList<MyBoardVO>();

		String sql = "SELECT DISTINCT M.TITLE, M.POSTER, B.BOARDID, B.USERID " 
				+ "FROM MOVIE M, GENRE G, MOVIEANDGENRE MAG, BOARD B "
				+ "WHERE MAG.MOVIEID = M.MOVIEID "
				+ "AND MAG.GENREID = G.GENREID "
				+ "AND B.MOVIEID = MAG.MOVIEID "
				+ "AND B.USERID = '"+ id +"' "
				+ "AND M.TITLE LIKE ?"; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+searchTitle+"%");

			// resultSet
			rs = pstmt.executeQuery();
			  while(rs.next()) {
				   MyBoardVO bvo = new MyBoardVO();
				   bvo.setTitle(rs.getString(1));
				   bvo.setPoster(rs.getString(2));
				   bvo.setBoardId(rs.getInt(3));
				   bvo.setUserId(rs.getString(4));
				   
				   bslist.add(bvo);
				   
			   }
			
			System.out.println("searchDAO="+bslist);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, rs);
		}

		return bslist;

	}
   //----------나의 게시판 select 선택란------------------------------------------------------------------------

 //최신리뷰순 게시물
    public List<MyBoardVO> myLatestBoard(String id, String genreId){
       Connection conn = DBConnect.getInstance();
       PreparedStatement pstmt = null;
       Statement stmt = null;
       ResultSet rs = null;
       List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
       String sql ="";

       if(genreId.equals("0")) {
          //모든장르,최신리뷰순
          sql = " SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
                + " FROM BOARD B, MOVIE M"
                + " WHERE B.MOVIEID = M.MOVIEID"
                + " AND B.USERID = '"+ id +"'"
                + " ORDER BY B.BOARDID DESC";

          try {
             stmt = conn.createStatement();
             rs = stmt.executeQuery(sql);
             while(rs.next()) {
                MyBoardVO bvo = new MyBoardVO();
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
                + " AND B.USERID = '"+ id +"'"
                + " AND MG.GENREID = ?"
                + " ORDER BY B.BOARDID DESC";
          try {
             //prepared는 값 넣을 때필요
             pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, genreId);
             rs = pstmt.executeQuery();
             while(rs.next()) {
                MyBoardVO bvo = new MyBoardVO();
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
    public List<MyBoardVO> myRecommendBoard(String id, String genreId){
       Connection conn = DBConnect.getInstance();
       PreparedStatement pstmt = null;
       Statement stmt = null;
       ResultSet rs = null;
       List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
       String sql ="";

       if(genreId.equals("0")) {
          //모든장르, 추천순
          sql = " SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
                + " FROM BOARD B, MOVIE M"
                + " WHERE B.MOVIEID = M.MOVIEID"
                + " AND B.USERID = '"+ id +"'"
                + " ORDER BY B.LIKES DESC";

          try {
             stmt = conn.createStatement();
             rs = stmt.executeQuery(sql);
             while(rs.next()) {
                MyBoardVO bvo = new MyBoardVO();
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
                + " AND B.USERID = '"+ id +"'"
                + " AND MG.GENREID = ?"
                + " ORDER BY B.LIKES DESC";

          try {
             //prepared는 값 넣을 때필요
             pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, genreId);
             rs = pstmt.executeQuery();
             while(rs.next()) {
                MyBoardVO bvo = new MyBoardVO();
                bvo.setUserId(rs.getString(1));
                bvo.setTitle(rs.getString(2));
                bvo.setPoster(rs.getString(3));
                bvo.setBoardId(rs.getInt(4));

                blist.add(bvo);

             }
//             System.out.println("장르별 추천순:"+blist);
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
    public List<MyBoardVO> myRateBoard(String id,String genreId){
       Connection conn = DBConnect.getInstance();
       PreparedStatement pstmt = null;
       Statement stmt = null;
       ResultSet rs = null;
       List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
       String sql ="";
       if(genreId.equals("0")) {
          //모든장르, 평점순
          sql = " SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
                + " FROM BOARD B, MOVIE M"
                + " WHERE B.MOVIEID = M.MOVIEID"
                + " AND B.USERID = '"+ id +"'"
                + " ORDER BY M.RATE DESC";

          try {
             stmt = conn.createStatement();
             rs = stmt.executeQuery(sql);
             while(rs.next()) {
                MyBoardVO bvo = new MyBoardVO();
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
                + " AND B.USERID = '"+ id +"'"
                + " AND MG.GENREID = ?"
                + " ORDER BY M.RATE DESC";

          try {
             //prepared는 값 넣을 때필요
             pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, genreId);
             rs = pstmt.executeQuery();
             while(rs.next()) {
                MyBoardVO bvo = new MyBoardVO();
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
    public List<MyBoardVO> myRunningTimeBoard(String id, String genreId){
       Connection conn = DBConnect.getInstance();
       PreparedStatement pstmt = null;
       Statement stmt = null;
       ResultSet rs = null;
       List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
       String sql ="";
       if(genreId.equals("0")) {
          //모든장르, 러닝타임 짧은순
          sql = " SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
                + " FROM BOARD B, MOVIE M"
                + " WHERE B.MOVIEID = M.MOVIEID"
                + " AND B.USERID = '"+ id +"'"
                + " ORDER BY M.RATE DESC";

          try {
             stmt = conn.createStatement();
             rs = stmt.executeQuery(sql);
             while(rs.next()) {
                MyBoardVO bvo = new MyBoardVO();
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
                + " AND B.USERID = '"+ id +"'"
                + " AND MG.GENREID = ?"
                + " ORDER BY M.RUNNINGTIME";

          try {
             //prepared는 값 넣을 때필요
             pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, genreId);
             rs = pstmt.executeQuery();
             while(rs.next()) {
                MyBoardVO bvo = new MyBoardVO();
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
//-----------------------페이징 연습------------------------------------
    //비동기 페이징처리를 위한 SELECT
    public List<MyBoardVO> mySelectListPage(String id, String keyword,int genreId, int start, int end){
 	   Connection conn = DBConnect.getInstance();
 	   PreparedStatement pstmt = null;
 	   ResultSet rs = null;
 	   
 	   String sql ="SELECT BOARDID, USERID, TITLE, POSTER "
 	   			+ "FROM(SELECT ROWNUM AS RNUM, A.* FROM (SELECT B.BOARDID, B.USERID, M.TITLE, M.POSTER "
 	   												+ "FROM BOARD B, MOVIE M "
 	   												+ "WHERE B.MOVIEID = M.MOVIEID "
 	   												+ "AND MG.MOVIEID = B.MOVIEID "
 	   												+ "AND B.USERID ='"+ id + "' "
 	   												+ "AND MG.GENREID ="+genreId+" "
 													+ "ORDER BY" +keyword+ "DESC) A ) "
 				+ "WHERE RNUM BETWEEN ? AND ?";
 	   
 	   
 	   List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
 	   
 	   try {
 		   //prepared는 값 넣을 때필요
 		   pstmt = conn.prepareStatement(sql);
// 		   pstmt.setInt(1, genreId);
 		   pstmt.setInt(1, start);
 		   pstmt.setInt(2, end);
 		   rs = pstmt.executeQuery();
 		   
 		   while(rs.next()) {
 			   MyBoardVO bvo = new MyBoardVO();
 			   bvo.setBoardId(rs.getInt(1));
 			   bvo.setUserId(rs.getString(2));
 			   bvo.setTitle(rs.getString(3));
 			   bvo.setPoster(rs.getString(4));
 			   
 			   blist.add(bvo);
 			   
 		   }
 		   
 		   System.out.println("페이징:"+blist);
 	   } catch (SQLException e) {
 		   // TODO Auto-generated catch block
 		   e.printStackTrace();
 	   }finally {
 		   closeAll(conn, pstmt, null, rs);
 		   
 	   }
 	   
 	   return blist;
    }   
   
    
    //keyword 선택하기
    public List<MyBoardVO> keywordList(String id){
  	   Connection conn = DBConnect.getInstance();
  	   Statement stmt = null;
  	   ResultSet rs = null;
  	   
  	   String sql ="SELECT DISTINCT B.VIEWS, B.LIKES, M.RATE, M.RUNNINGTIME "
  	   		+ "FROM BOARD B, MOVIE M, MOVIEANDGENRE MG "
  	   		+ "WHERE B.MOVIEID = M.MOVIEID "
  	   		+ "AND B.USERID = '"+id+"'";
  	   
  	   
  	   List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
  	   
  	   try {
  		   //prepared는 값 넣을 때필요
  		   stmt = conn.createStatement();
  		   rs = stmt.executeQuery(sql);
  		   
  		   while(rs.next()) {
  			   MyBoardVO bvo = new MyBoardVO();
  			   bvo.setViews(rs.getInt(1));
  			   bvo.setLikes(rs.getInt(2));
  			   bvo.setRate(rs.getInt(3));
  			   bvo.setRunningTime(rs.getInt(4));
  			   
  			   blist.add(bvo);
  			   
  		   }
  		   
//  		   System.out.println("allKeyword:"+blist);
  	   } catch (SQLException e) {
  		   // TODO Auto-generated catch block
  		   e.printStackTrace();
  	   }finally {
  		   closeAll(conn, null, stmt, rs);
  		   
  	   }
  	   
  	   return blist;
     }   
    
   
}