package com.fitcha.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitcha.model.dao.GenreDAO;
import com.fitcha.model.vo.GenreVO;
import com.fitcha.model.vo.MovieVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/genre")
public class GenreController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    
	    GenreDAO gdao = new GenreDAO();
	    ArrayList<GenreVO> glist = gdao.selectGenreList();
	    
	    JsonArray jsonArr = new JsonArray();
        for (GenreVO gvo : glist) {
            JsonObject json = new JsonObject();
            json.addProperty("genreid", gvo.getGenreId());
            json.addProperty("name", gvo.getGenreName());
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
