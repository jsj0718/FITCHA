package com.fitcha.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitcha.model.dao.MovieAndGenreDAO;
import com.fitcha.model.dao.MovieAndStaffDAO;
import com.fitcha.model.dao.MovieDAO;
import com.fitcha.model.vo.MovieAndGenreVO;
import com.fitcha.model.vo.MovieAndStaffVO;
import com.fitcha.model.vo.MovieVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/search-detail")
public class SearchDetailController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    
	    int movieId = Integer.parseInt(request.getParameter("movieid"));
	    
        MovieDAO mdao = new MovieDAO();
        MovieAndStaffDAO masdao = new MovieAndStaffDAO();
        MovieAndGenreDAO magdao = new MovieAndGenreDAO();
        
        MovieVO mvo = mdao.selectMovie(movieId);
        ArrayList<MovieAndStaffVO> maslist = masdao.selectStaffList(movieId);
        ArrayList<MovieAndGenreVO> maglist = magdao.selectGenreList(movieId);
        JsonArray movieArr = new JsonArray();
        JsonArray staffArr = new JsonArray();
        JsonArray genreArr = new JsonArray();
        
        for (MovieAndStaffVO masvo : maslist) {
            JsonObject staff = new JsonObject();
            staff.addProperty("staffname", masvo.getStaffName());
            staff.addProperty("staffimg", masvo.getStaffImg());
            staff.addProperty("role", masvo.getRole());
            staff.addProperty("rolename", masvo.getRoleName());
                
            staffArr.add(staff);
        }
         
        for (MovieAndGenreVO magvo : maglist) {
            JsonObject genre = new JsonObject();
            genre.addProperty("genrename", magvo.getGenreName());
            
            genreArr.add(genre);
        }
        
        JsonObject movie = new JsonObject();
        movie.addProperty("movieid", mvo.getMovieId());
        movie.addProperty("title", mvo.getTitle());
        movie.addProperty("subtitle", mvo.getSubTitle());
        movie.addProperty("story", mvo.getStory());
        movie.addProperty("poster", mvo.getPoster());
        movie.addProperty("grade", mvo.getGrade());
        movie.addProperty("country", mvo.getCountry());
        movie.addProperty("rate", mvo.getRate());
        movie.addProperty("opendate", mvo.getOpenDate());
        movie.addProperty("runningtime", mvo.getRunningTime());
        movieArr.add(movie);
    
        
        Gson gson = new Gson();
        
        String staffStr = gson.toJson(staffArr);
        JsonObject staffObj = new JsonObject();
        staffObj.addProperty("staffList", staffStr);
        movieArr.add(staffObj);
        
        String genreStr = gson.toJson(genreArr);
        JsonObject genreObj = new JsonObject();
        genreObj.addProperty("genreList", genreStr);
        movieArr.add(genreObj);
        
        String jsonResponse = gson.toJson(movieArr);
                
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
