package com.fitcha.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;


import com.fitcha.model.dao.BCommentDAO;
import com.fitcha.model.dao.MainBoardDAO;
import com.fitcha.model.vo.BCommentVO;
import com.fitcha.model.vo.MainBoardVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/comment")
public class CommentController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/board/review-board.jsp");
//        rd.forward(request, response);
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String boardId = request.getParameter("boardId");
		String newComment = request.getParameter("newComment");
		String userId = (String)session.getAttribute("id");
		
		
		BCommentDAO bcdao = new BCommentDAO();
		List<BCommentVO> bclist = bcdao.insertComment(boardId, newComment, userId);
        JsonArray jsonArr = new JsonArray();
        
        for(BCommentVO bcvo: bclist) {
        	JsonObject json = new JsonObject();
        	json.addProperty("boardId", bcvo.getBoardId());
        	json.addProperty("userId", bcvo.getUserID());
        	json.addProperty("newComment", bcvo.getContent());
        	json.addProperty("commentId", bcvo.getCommentId());
        	jsonArr.add(json);
        	
        }
        	
        
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(jsonArr);
        
        response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonResponse);

	}
}
