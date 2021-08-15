package com.fitcha.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitcha.model.dao.MovieAndStaffDAO;
import com.fitcha.model.dao.MovieDAO;
import com.fitcha.model.vo.MovieAndStaffVO;
import com.fitcha.model.vo.MovieVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/search-contents")
public class SearchContentsController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    
	    String genre = request.getParameter("genre");
	    String country = request.getParameter("country");
        String order = request.getParameter("order");
        int index = Integer.parseInt(request.getParameter("index"));
	    
        MovieDAO mdao = new MovieDAO();
        
        ArrayList<MovieVO> mlist = mdao.selectMovieBySearch(country, genre, order, index);
        
        JsonArray jsonArr = new JsonArray();
        
        for (MovieVO movie : mlist) {
            
            JsonObject json = new JsonObject();
            MovieVO mvo = mdao.selectMovie(movie.getMovieId());
            json.addProperty("movieid", mvo.getMovieId());
            json.addProperty("title", mvo.getTitle());
            json.addProperty("poster", mvo.getPoster());
            json.addProperty("rate", mvo.getRate());
            json.addProperty("opendate", mvo.getOpenDate());
            json.addProperty("runningtime", mvo.getRunningTime());
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
