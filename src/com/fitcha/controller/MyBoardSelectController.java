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


@WebServlet("/my_board_select.do")
public class MyBoardSelectController extends HttpServlet {
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	   }

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      request.setCharacterEncoding("utf-8");
	      HttpSession session = request.getSession();
	      String id = (String)session.getAttribute("id");
	      String genre = request.getParameter("genre");
	      String recommend = request.getParameter("recommend");
	      
	      System.out.println("서블릿 장르: "+genre+", 추천순: "+recommend);
	      
	      MyBoardDAO bdao = new MyBoardDAO();
	      List<MyBoardVO> blist=null;
	      
	      //recommend 1=최신순, 2=추천순, 3=평점순, 4=러닝타임 짧은순
	      if(recommend.equals("1")) {
	          blist = bdao.myLatestBoard(id,genre);
	      }else if(recommend.equals("2")){
	         blist = bdao.myRecommendBoard(id,genre);
	      }else if(recommend.equals("3")) {
	          blist = bdao.myRateBoard(id,genre);
	      }else {
	          blist = bdao.myRunningTimeBoard(id,genre);
	      }
	        JsonArray jsonArr = new JsonArray();
	        for(MyBoardVO bvo: blist) {
	           JsonObject json = new JsonObject();
	           json.addProperty("boardId", bvo.getBoardId());
	           json.addProperty("userId", bvo.getUserId());
	           json.addProperty("title", bvo.getTitle());
	           json.addProperty("poster", bvo.getPoster());
	           jsonArr.add(json);
	           
	        }
	        System.out.println("selectBox:"+jsonArr);
	        Gson gson = new Gson();
	        String jsonResponse = gson.toJson(jsonArr);
	        
	        response.setContentType("text/html; charset=utf-8");
	        PrintWriter out = response.getWriter();
	        out.print(jsonResponse);
	      
	      
	      
	   }

	}