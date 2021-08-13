package com.fitcha.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fitcha.model.dao.MemoDAO;
import com.fitcha.model.vo.MemoVO;

@WebServlet("/memo-update")
public class MemoUpdateController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String mdate = request.getParameter("mdate");
		String title = request.getParameter("title");
		String memo = request.getParameter("memo");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		MemoVO mvo = new MemoVO();
		mvo.setMno(Integer.parseInt(no));
		mvo.setMdate(Date.valueOf(mdate));
		mvo.setTitle(title);
		mvo.setMemo(memo);
		
		MemoDAO mdao = new MemoDAO();
		mdao.updateMemo(mvo);
		
		response.sendRedirect(request.getContextPath()+ "/mcalendar");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
