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

import com.fitcha.model.dao.MyBoardDAO;
import com.fitcha.model.vo.MyBoardVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@WebServlet("/myBoardView")
public class MyBoardViewPageController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
		
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
        
       
        if(id==null) {
        	//로그인 안된 상태
        	response.sendRedirect(request.getContextPath()+"/index-login");
        	
        }else {
        	//로그인이 된 상태
        	
        	
       	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/board/my-board.jsp");
       	rd.forward(request, response);
        	
        }
	} 
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        	
        }
}