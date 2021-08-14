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
import com.fitcha.model.vo.BoardVO;
import com.fitcha.model.vo.MovieVO;
import com.fitcha.pagination.Pagination;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/age-contents")
public class AgeContentsController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
        
        MovieDAO mdao = new MovieDAO();
        
        ArrayList<MovieVO> mlist = mdao.selectMovieByUserInfo("YOUTH", 1, 18);
        
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	}

}
