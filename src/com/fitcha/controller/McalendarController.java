package com.fitcha.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fitcha.model.dao.DipsDAO;
import com.fitcha.model.vo.DipsVO;
import com.fitcha.model.vo.MemoJson;
import com.fitcha.model.vo.MovieJson;
import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;

@WebServlet("/mcalendar")
public class McalendarController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id"); // 이 id는 로그인 id
		
		if(id == null) { // id가 null일 때 없을 때
//			로그인 안된 상태에서는 로그인을 할 수 있게 로그인창으로 넘김 , 로그인 페이지 호출
			response.sendRedirect(request.getContextPath() + "/login");
		} else { // id가 들어왔으면
//			로그인 상태가 되면 dips의 내용을 가져와 캘린더에 넣어줌
			DipsDAO ddao = new DipsDAO(); // DipsDAO를 사용하기위해 선언
//			List<DipsVO> dlist = ddao.allDips(id); // 로그인id ALLDIPS = SELECT문
			
//			List<MemoJson> mjlist = new ArrayList<MemoJson>();
//			List<MovieJson> movieList = new ArrayList<MovieJson>();
//			JsonArray jsonArr = new JsonArray();
//			for(DipsVO dvo : dlist) {
//				MemoJson mj = new MemoJson();
//				mj.setId(dvo.getDipsId());
//				mj.setTitle(String.valueOf(dvo.getMovieId()));
//				mj.setStart(dvo.getDdate());
//				mj.setDescription(dvo.getUserId());
				
//				mjlist.add(mj);
//			}
//			DipsDAO ddao = new DipsDAO();
			List<MovieJson> movieList = new ArrayList<MovieJson>();
			// 영화정보 조회
			List<DipsVO> dlist2 = ddao.getDipsMovies(id); // 로그인id ALLDIPS = SELECT문
			for(DipsVO dvo : dlist2) {
				// 영화정보
				MovieJson movieJson = new MovieJson();
//				movieJson.setMovieId(dvo.getTitle());
				movieJson.setMovieTitle(dvo.getTitle());
				movieJson.setImage_url(dvo.getPoster());
				movieJson.setStart(String.valueOf(dvo.getDdate()));
				movieJson.setDipsNo(dvo.getDipsId());
				movieList.add(movieJson);
				
				
//				JsonObject json = new JsonObject();
//				json.addProperty("title",dvo.getTitle());
//				json.addProperty("ddate",dvo.getDdate());
//				json.addProperty("Poster",dvo.getPoster());
//				jsonArr.add(json);
				
				
				
				
			}
			
			
			Gson gson = new Gson();
//			String json1 = gson.toJson(mjlist);
			
//			String jsonResponse = gson.toJson(jsonArr);
//			response.setContentType("application/json; charset=utf-8");
//			PrintWriter out =response.getWriter();
//			out.print(jsonResponse);
			String json2 = gson.toJson(movieList);
//			request에 json값을 널어서 보내줌
//			request.setAttribute("mjson", json1);
			
			request.setAttribute("movieJson", json2);
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/main/mcalendar.jsp");
			rd.forward(request, response);
		}
			
		}
		
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
