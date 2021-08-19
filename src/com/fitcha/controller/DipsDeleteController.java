package com.fitcha.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitcha.model.dao.DipsDAO;

@WebServlet("/dips-delete")
public class DipsDeleteController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dipsNo = request.getParameter("dipsNo");
		
		DipsDAO ddao = new DipsDAO();
		ddao.deleteDips(Integer.parseInt(dipsNo));
		
		response.sendRedirect(request.getContextPath()+ "/mcalendar");
		
	}
	

}
