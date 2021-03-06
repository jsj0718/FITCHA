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
   
   public ArrayList<MyBoardVO> selectBoardList(String id, String btitle, String bcontent, String country, String mtitle, String genre, String order, int start, int end){
       Connection conn = DBConnect.getInstance();
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       String sql ="SELECT BOARDID, USERID, TITLE, POSTER "
               + "FROM(SELECT ROWNUM AS RNUM, A.* FROM ("   
                   + "SELECT DISTINCT B.BOARDID, B.USERID, B.TITLE, M.POSTER, B.VIEWS, B.LIKES "
                   + "FROM BOARD B, MOVIE M, GENRE G, MOVIEANDGENRE MAG "
                   + "WHERE B.MOVIEID = M.MOVIEID "
                   + "AND M.MOVIEID = MAG.MOVIEID "
                   + "AND MAG.GENREID = G.GENREID "
                   + "AND B.USERID = ? "
                   + "AND B.TITLE LIKE ? "
                   + "AND B.CONTENT LIKE ? "
                   + "AND M.TITLE LIKE ? "
                   + "AND M.COUNTRY LIKE ? "
                   + "AND G.GENRENAME LIKE ? "
                   + "ORDER BY "+order+" DESC"     
               + ") A ) "
               + "WHERE RNUM BETWEEN ? AND ?";
       
       ArrayList<MyBoardVO> blist= new ArrayList<MyBoardVO>();
       
       try {
           //prepared??? ??? ?????? ?????????
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, id);
           pstmt.setString(2,"%"+btitle+"%");
           pstmt.setString(3,"%"+bcontent+"%");
           pstmt.setString(4, "%"+mtitle+"%");
           pstmt.setString(5, "%"+country+"%");
           pstmt.setString(6, "%"+genre+"%");
           pstmt.setInt(7, start);
           pstmt.setInt(8, end);
           rs = pstmt.executeQuery();
           
           while(rs.next()) {
               MyBoardVO bvo = new MyBoardVO();
               bvo.setBoardId(rs.getInt(1));
               bvo.setUserId(rs.getString(2));
               bvo.setBtitle(rs.getString(3));
               bvo.setPoster(rs.getString(4));
               
               blist.add(bvo);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           closeAll(conn, pstmt, null, rs);
       }
       return blist;
   }   
   
   
 
//   String SQL = 
//           "SELECT B.BOARDID, B.USERID, B.TITLE, B.CONTENT, M.POSTER "
//           + "FROM BOARD B, MOVIE M, GENRE G, MOVIEANDGENRE MAG "
//           + "WHERE B.MOVIEID = M.MOVIEID "
//           + "AND M.MOVIEID = MAG.MOVIEID "
//           + "AND MAG.GENREID = G.GENREID "
//           + "AND B.USERID LIKE ? "
//           + "AND B.TITLE LIKE ? "
//           + "AND B.CONTENT LIKE ? "
//           + "AND M.COUNTRY LIKE ? "
//           + "AND G.GENRENAME LIKE ? "
//           + "ORDER BY "+ +" DESC";
   
   //?????? ???????????????  recently add , ?????? ?????? ??????(String id)??????
   public List<MyBoardVO> myBoardList(String id){
	   Connection conn = DBConnect.getInstance();
	   Statement stmt = null;
	   ResultSet rs = null;
	   
	   String sql ="SELECT B.BOARDID, B.USERID, M.TITLE, M.POSTER "
	            + " FROM BOARD B, MOVIE M"
	            + " WHERE B.MOVIEID = M.MOVIEID"
	            + " AND B.USERID = '"+ id +"'"
	            + " ORDER BY B.BOARDID DESC";
	          
	   
	   
	   List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
	   
	   try {
		   //prepared??? ??? ?????? ?????????
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
   
   //?????? ??????????????? ?????? ?????? ????????? ?????????
   public List<MyBoardVO> myBoardListPage(String id, int start, int end){
	   Connection conn = DBConnect.getInstance();
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   
	   String sql ="SELECT BOARDID, USERID, TITLE, CONTENT, POSTER "
	   		+ "FROM(SELECT ROWNUM AS RNUM, A.* FROM (SELECT B.BOARDID, B.USERID, B.TITLE, B.CONTENT, M.POSTER "
	   												+ "FROM BOARD B, MOVIE M "
	   												+ "WHERE B.MOVIEID = M.MOVIEID "
	   		+ "                                        AND B.USERID ='"+ id +"' "
													+ "ORDER BY B.BOARDID DESC) A ) "
	   		+ "WHERE RNUM BETWEEN ? AND ?";
	   
	   
	   List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
	   
	   try {
		   //prepared??? ??? ?????? ?????????
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setInt(1, start);
		   pstmt.setInt(2, end);
		   rs = pstmt.executeQuery();
		   
		   while(rs.next()) {
			   MyBoardVO bvo = new MyBoardVO();
			   bvo.setBoardId(rs.getInt(1));
			   bvo.setUserId(rs.getString(2));
			   bvo.setTitle(rs.getString(3));
			   bvo.setContent(rs.getString(4));
			   bvo.setPoster(rs.getString(5));
			   
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
   //????????? ????????? ?????? ????????? ??? ??????
   public int selectBoardCnt(String id,  String mtitle, String country, String genre) {
		String sql = "SELECT COUNT(COUNT(B.BOARDID)) "
				+ "FROM BOARD B, MOVIEANDGENRE MAG, GENRE G, MOVIE M "
                + "WHERE B.MOVIEID = M.MOVIEID "
                + "AND M.MOVIEID = MAG.MOVIEID "
                + "AND MAG.GENREID = G.GENREID "
				+ "AND B.USERID = '"+id+"' "
                + "AND M.TITLE LIKE '%"+mtitle+"%' "
                + "AND M.COUNTRY LIKE '%"+country+"%' "
                + "AND G.GENRENAME LIKE '%"+genre+"%' "
                + "GROUP BY B.BOARDID";
		Connection conn =DBConnect.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
		    pstmt = conn.prepareStatement(sql);		  
//		    pstmt.setString(1, id);
//		    pstmt.setString(1, "%"+mtitle+"%");
//		    pstmt.setString(2, "%"+country+"%");
//		    pstmt.setString(3, "%"+genre+"%");
		    
			rs= pstmt.executeQuery(sql);
			while(rs.next()) {				
				result = rs.getInt(1);
			}
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null, rs);
		}
		return result;
	}
   
//  ?????? ??????????????? ????????? ?????? , VIEWS????????? ??????
   public List<MyBoardVO> myBoardBestList(String id){
	   Connection conn = DBConnect.getInstance();
	   Statement stmt = null;
	   ResultSet rs = null;
	   
	   String sql ="SELECT B.BOARDID, B.USERID, B.TITLE, B.CONTENT, M.POSTER"
	            + " FROM BOARD B, MOVIE M"
	            + " WHERE B.MOVIEID = M.MOVIEID"
	            + " AND B.USERID = '"+ id +"'"
				+ " AND ROWNUM BETWEEN 1 AND 6"
	            + " ORDER BY B.VIEWS DESC";
	          
	   
	   List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
	
	   try {
		   //prepared??? ??? ?????? ?????????
		   stmt = conn.createStatement();
		   rs = stmt.executeQuery(sql);
		   
		   while(rs.next()) {
			   MyBoardVO bvo = new MyBoardVO();
			   bvo.setBoardId(rs.getInt(1));
			   bvo.setUserId(rs.getString(2));
			   bvo.setTitle(rs.getString(3));
			   bvo.setContent(rs.getString(4));
			   bvo.setPoster(rs.getString(5));
			   
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
   
   //??????????????? ?????????
   public List<MyBoardVO> search(String id, String searchTitle) {
		Connection conn = DBConnect.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyBoardVO> bslist= new ArrayList<MyBoardVO>();
		
		String sql = "SELECT B.BOARDID, B.USERID, B.TITLE, B.CONTENT, M.POSTER "
		        + "FROM MOVIE M, BOARD B "
		        + "WHERE M.MOVIEID = B.MOVIEID "
		        + "AND B.USERID = '" + id + "' "
		        + "AND M.TITLE LIKE ?"; 
//        String sql = "SELECT DISTINCT M.TITLE, M.POSTER, B.BOARDID, B.USERID " 
//				+ "FROM MOVIE M, GENRE G, MOVIEANDGENRE MAG, BOARD B "
//				+ "WHERE MAG.MOVIEID = M.MOVIEID "
//				+ "AND MAG.GENREID = G.GENREID "
//				+ "AND B.MOVIEID = MAG.MOVIEID "
//				+ "AND B.USERID = '"+ id +"' "
//				+ "AND M.TITLE LIKE ?"; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+searchTitle+"%");

			// resultSet
			rs = pstmt.executeQuery();
			  while(rs.next()) {
				   MyBoardVO bvo = new MyBoardVO();
				   bvo.setBoardId(rs.getInt(1));
	               bvo.setUserId(rs.getString(2));
	               bvo.setTitle(rs.getString(3));
	               bvo.setContent(rs.getString(4));
	               bvo.setPoster(rs.getString(5));
				   
				   bslist.add(bvo);

//				   bvo.setTitle(rs.getString(1));
//				   bvo.setPoster(rs.getString(2));
//				   bvo.setBoardId(rs.getInt(3));
//				   bvo.setUserId(rs.getString(4));
				   
			   }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null, rs);
		}

		return bslist;

	}
   //----------?????? ????????? select ?????????------------------------------------------------------------------------

 //??????????????? ?????????
    public List<MyBoardVO> myLatestBoard(String id, String genreId){
       Connection conn = DBConnect.getInstance();
       PreparedStatement pstmt = null;
       Statement stmt = null;
       ResultSet rs = null;
       List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
       String sql ="";

       if(genreId.equals("0")) {
          //????????????,???????????????
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
          //????????????, ???????????????
          sql =" SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
                + " FROM BOARD B, MOVIE M, MOVIEANDGENRE MG"
                + " WHERE B.MOVIEID = MG.MOVIEID"
                + " AND B.MOVIEID = M.MOVIEID"
                + " AND B.USERID = '"+ id +"'"
                + " AND MG.GENREID = ?"
                + " ORDER BY B.BOARDID DESC";
          try {
             //prepared??? ??? ?????? ?????????
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



    //????????? ?????????
    public List<MyBoardVO> myRecommendBoard(String id, String genreId){
       Connection conn = DBConnect.getInstance();
       PreparedStatement pstmt = null;
       Statement stmt = null;
       ResultSet rs = null;
       List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
       String sql ="";

       if(genreId.equals("0")) {
          //????????????, ?????????
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
          //?????????, ?????????
          sql =" SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
                + " FROM BOARD B, MOVIE M, MOVIEANDGENRE MG"
                + " WHERE B.MOVIEID = MG.MOVIEID"
                + " AND B.MOVIEID = M.MOVIEID"
                + " AND B.USERID = '"+ id +"'"
                + " AND MG.GENREID = ?"
                + " ORDER BY B.LIKES DESC";

          try {
             //prepared??? ??? ?????? ?????????
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




    //????????? ?????????
    public List<MyBoardVO> myRateBoard(String id,String genreId){
       Connection conn = DBConnect.getInstance();
       PreparedStatement pstmt = null;
       Statement stmt = null;
       ResultSet rs = null;
       List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
       String sql ="";
       if(genreId.equals("0")) {
          //????????????, ?????????
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
          //?????????, ?????????   
          sql =" SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
                + " FROM BOARD B, MOVIE M, MOVIEANDGENRE MG"
                + " WHERE B.MOVIEID = MG.MOVIEID"
                + " AND B.MOVIEID = M.MOVIEID"
                + " AND B.USERID = '"+ id +"'"
                + " AND MG.GENREID = ?"
                + " ORDER BY M.RATE DESC";

          try {
             //prepared??? ??? ?????? ?????????
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



    //???????????? ????????? ?????????
    public List<MyBoardVO> myRunningTimeBoard(String id, String genreId){
       Connection conn = DBConnect.getInstance();
       PreparedStatement pstmt = null;
       Statement stmt = null;
       ResultSet rs = null;
       List<MyBoardVO> blist= new ArrayList<MyBoardVO>();
       String sql ="";
       if(genreId.equals("0")) {
          //????????????, ???????????? ?????????
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
          //?????????, ???????????? ?????????
          sql =" SELECT B.USERID, M.TITLE, M.POSTER, B.BOARDID"
                + " FROM BOARD B, MOVIE M, MOVIEANDGENRE MG"
                + " WHERE B.MOVIEID = MG.MOVIEID"
                + " AND B.MOVIEID = M.MOVIEID"
                + " AND B.USERID = '"+ id +"'"
                + " AND MG.GENREID = ?"
                + " ORDER BY M.RUNNINGTIME";

          try {
             //prepared??? ??? ?????? ?????????
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
//-----------------------????????? ??????------------------------------------
    //????????? ?????????????????? ?????? SELECT
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
 		   //prepared??? ??? ?????? ?????????
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
 		   
 	   } catch (SQLException e) {
 		   // TODO Auto-generated catch block
 		   e.printStackTrace();
 	   }finally {
 		   closeAll(conn, pstmt, null, rs);
 		   
 	   }
 	   
 	   return blist;
    }   
   
    
    //keyword ????????????
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
  		   //prepared??? ??? ?????? ?????????
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
  		   
  	   } catch (SQLException e) {
  		   // TODO Auto-generated catch block
  		   e.printStackTrace();
  	   }finally {
  		   closeAll(conn, null, stmt, rs);
  		   
  	   }
  	   
  	   return blist;
     }   
    
   
}