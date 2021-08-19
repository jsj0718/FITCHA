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

@WebServlet("/main_board")
public class MainBoardController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MainBoardDAO bdao = new MainBoardDAO();
        List<MainBoardVO> blist = bdao.allBoard();
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
//		System.out.println(jsonResponse);
		
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/board/main-board.jsp");
//		rd.forward(request, response);

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
