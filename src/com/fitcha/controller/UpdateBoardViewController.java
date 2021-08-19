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

@WebServlet("/update_board_view")
public class UpdateBoardViewController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/board/review-board.jsp");
		rd.forward(request, response);

		
	}

}
