package com.fitcha.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitcha.model.dao.MainBoardDAO;
import com.fitcha.model.vo.BoardJson;
import com.fitcha.model.vo.MainBoardVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/main_board_select")
public class MainBoardSelectController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String genre = request.getParameter("genre");
		String recommend = request.getParameter("recommend");
		
//		System.out.println("서블릿 받아온 장르: "+genre+", 추천순: "+recommend);
		
		MainBoardDAO bdao = new MainBoardDAO();
		List<MainBoardVO> blist=null;
		
		//recommend 1=최신순, 2=추천순, 3=평점순, 4=러닝타임 짧은순
		if(recommend.equals("1")) {
			 blist = bdao.latestBoard(genre);
		}else if(recommend.equals("2")){
			blist = bdao.recommendBoard(genre);
		}else if(recommend.equals("3")) {
			 blist = bdao.rateBoard(genre);
		}else {
			 blist = bdao.runningTimeBoard(genre);
		}
        JsonArray jsonArr = new JsonArray();
        for(MainBoardVO bvo: blist) {
        	JsonObject json = new JsonObject();
        	json.addProperty("boardId", bvo.getBoardId());
        	json.addProperty("userId", bvo.getUserId());
        	json.addProperty("title", bvo.getTitle());
        	json.addProperty("poster", bvo.getPoster());
        	jsonArr.add(json);
        	
        }
        
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(jsonArr);
        
        response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonResponse);
		
		
		
	}

}
