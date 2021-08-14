package com.fitcha.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitcha.model.dao.MovieDAO;
import com.fitcha.model.vo.MovieVO;
import com.fitcha.pagination.Pagination;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/popular-contents")
public class PopularContentsController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    
	    MovieDAO mdao = new MovieDAO();

	    String curPage = request.getParameter("curpage");
	    if (curPage == null) {
	        curPage = "1";
	    }
	    
	    int curPageInt = Integer.parseInt(curPage);
	    int totalContent = mdao.selectMovieCountByAttendance();
	    Pagination pagination = new Pagination(curPageInt, totalContent, 6);
	    
	    // 한 page 내 보여줘야하는 게시물의 첫 번째 Rownum
	    int start = (curPageInt * pagination.getContentCnt()) - (pagination.getContentCnt() - 1);
	    // 한 page 내 보여줘야하는 게시물의 마지막 Rownum
	    int end = curPageInt * pagination.getContentCnt();
	    
	    ArrayList<MovieVO> mlist = mdao.selectMovieByAttendance(start, end);
        
        JsonArray jsonArr = new JsonArray();
        
        for (MovieVO mvo : mlist) {
            JsonObject json = new JsonObject();
            json.addProperty("title", mvo.getTitle());
            json.addProperty("poster", mvo.getPoster());
            
            jsonArr.add(json);
        }
        
        
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(jsonArr);
                
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
	}

}
