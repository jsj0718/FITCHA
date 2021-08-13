package com.fitcha.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitcha.model.dao.MemoDAO;

@WebServlet("/memo-delete")
public class MemoDeleteController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		
		MemoDAO mdao = new MemoDAO();
		mdao.deleteMemo(Integer.parseInt(no));
		
		response.sendRedirect(request.getContextPath()+ "/mcalendar");
		
	}
	

}
