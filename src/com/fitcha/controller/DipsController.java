package com.fitcha.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fitcha.model.dao.DipsDAO;
import com.fitcha.model.dao.DipsTotalDAO;
import com.fitcha.model.dao.MUserDAO;
import com.fitcha.model.dao.MovieAndGenreDAO;
import com.fitcha.model.dao.UserAndGenreDAO;
import com.fitcha.model.vo.MUserVO;
import com.fitcha.model.vo.MovieAndGenreVO;

@WebServlet("/dips")
public class DipsController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    
	    HttpSession session = request.getSession();
	    String userId = (String) session.getAttribute("id");
	    int movieId = Integer.parseInt(request.getParameter("movieid"));
	    String dip = request.getParameter("dip");
	    
	    MovieAndGenreDAO magdao = new MovieAndGenreDAO();
	    UserAndGenreDAO uagdao = new UserAndGenreDAO();
	    ArrayList<MovieAndGenreVO> maglist = magdao.selectGenreList(movieId);
	    
	    String operator = "";
	    if (dip.equals("none")) {
	        dip = "dip";
	        operator = "+";
	        for (MovieAndGenreVO magvo : maglist) {
	            uagdao.mergeDips(userId, magvo.getGenreId(), operator);
	        }
	    } else {
	        dip = "none";
	        operator = "-";
	        for (MovieAndGenreVO magvo : maglist) {
                uagdao.mergeDips(userId, magvo.getGenreId(), operator);
            }
	    }
	    
	    // 회원정보 조회
	    MUserDAO udao = new MUserDAO();
	    MUserVO uvo = udao.selectUserInfo(userId);
	    String gender = uvo.getGender();
	    Date now = new Date();
	    long age = (now.getYear() - uvo.getBirth().getYear());
	    System.out.println(age);
	    String generation = "";
	    if (age < 20) {
	        generation = "teenager";
	    } else if (age < 40) {
	        generation = "youth";
	    } else if (age < 60) {
	        generation = "middleaged";
	    } else {
	        generation = "mature";
	    }
	    
	    // dips 테이블 최신화
	    DipsDAO ddao = new DipsDAO();
	    ddao.mergeDips(userId, movieId, "dip", dip);
	    
	    // dipstotal 테이블 누적
	    DipsTotalDAO dtdao = new DipsTotalDAO();
	    dtdao.mergeDipsTotal(movieId, gender, generation, operator);
	    
	    response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(dip);
	}

}
