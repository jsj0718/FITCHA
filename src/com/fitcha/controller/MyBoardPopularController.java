package com.fitcha.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fitcha.model.dao.MyBoardDAO;
import com.fitcha.model.vo.MyBoardVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@WebServlet("/my_board_popular.do")
public class MyBoardPopularController extends HttpServlet {
  
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
        
        
        if(id==null) {
        	//로그인 안된 상태
        	response.sendRedirect(request.getContextPath()+"/index-login");
        	
        }else {
        	//로그인이 된 상태
        	
        	MyBoardDAO bdao = new MyBoardDAO();
        	List<MyBoardVO> blist = bdao.myBoardBestList(id);
        	JsonArray jsonArr = new JsonArray();
        	
        	for(MyBoardVO bvo: blist) {
        		JsonObject json = new JsonObject();
        		json.addProperty("boardId", bvo.getBoardId());
        		json.addProperty("userId", bvo.getUserId());
        		json.addProperty("title", bvo.getTitle());
        		json.addProperty("poster", bvo.getPoster());
        		jsonArr.add(json);
        		
        	}
        	System.out.println("test:"+jsonArr);
        	
        	Gson gson = new Gson();
        	String jsonResponse = gson.toJson(jsonArr);
        	response.setContentType("application/json; charset=utf-8");
        	PrintWriter out = response.getWriter();
        	out.print(jsonResponse);
      	
        	
        	
        }
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		BoardDAO bdao = new BoardDAO();
//		List<BoardVO> blist = bdao.allBoard();
//    	JsonArray jsonArr = new JsonArray();
//    	
//    	for(BoardVO bvo: blist) {
//    		JsonObject json = new JsonObject();
//    		json.addProperty("boardId", bvo.getBoardId());
//    		json.addProperty("userId", bvo.getUserId());
//    		json.addProperty("title", bvo.getTitle());
//    		json.addProperty("poster", bvo.getPoster());
//    		jsonArr.add(json);
//    		
//    	}
//    	
//    	System.out.println("test:"+jsonArr);
//    	
//    	Gson gson = new Gson();
//    	String jsonResponse = gson.toJson(jsonArr);
//    	response.setContentType("application/json; charset=utf-8");
//    	PrintWriter out = response.getWriter();
//    	out.print(jsonResponse);
  	
    	
    	
    
		        } 
		}