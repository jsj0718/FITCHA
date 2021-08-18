package com.fitcha.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitcha.model.dao.MainBoardDAO;
import com.fitcha.model.vo.MainBoardVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/delete_board")
public class DeleteCommentController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/board/review-board.jsp");
//        rd.forward(request, response);
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		
		System.out.println("delete boardID: "+ boardId);
		MainBoardDAO bdao = new MainBoardDAO();
		int result = bdao.deleteBoard(boardId);
		
//		if(result != 0){
//			//정상 삭제
//			response.sendRedirect("/WEB-INF/board/main-board.jsp");
//			
//		}else{
//			//삭제실패
//			response.sendRedirect("review_board?boardId="+boardId);
//		}
	

}
}
