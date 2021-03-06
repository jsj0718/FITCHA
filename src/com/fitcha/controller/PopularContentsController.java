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
	    request.setCharacterEncoding("UTF-8");
        
	    // 상영중인 영화 중 인기있는 영화 최대 10개 조회
        MovieDAO mdao = new MovieDAO();
        ArrayList<MovieVO> mlist = mdao.selectMovieByAttendance(1, 10);
        
        JsonArray jsonArr = new JsonArray();
        for (MovieVO mvo : mlist) {
            JsonObject json = new JsonObject();
            json.addProperty("movieid", mvo.getMovieId());
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	}

}
