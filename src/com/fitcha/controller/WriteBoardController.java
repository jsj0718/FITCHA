package com.fitcha.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fitcha.model.dao.BoardDAO;

@WebServlet("/review/2")
public class WriteBoardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    
	    HttpSession session = request.getSession();
	    String id = (String) session.getAttribute("id");
	    int movieId = Integer.parseInt(request.getParameter("movieid"));
	    String reviewTitle = request.getParameter("reviewtitle");
	    String reviewContents = request.getParameter("reviewcontents");
	    String reviewDate = request.getParameter("reviewdate");
	    String reviewAccess = request.getParameter("reviewaccess");
	    
	    BoardDAO bdao = new BoardDAO();
	    String msg = "";
	    if (bdao.insertBoard(id, movieId, reviewTitle, reviewContents, reviewDate, reviewAccess) > 0) {
	        msg = "게시글 등록에 성공했습니다.";
	    } else {
	        msg = "게시글 등록에 실패했습니다.";
	    }
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print(msg);
	    
	}

}
