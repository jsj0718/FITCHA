package com.fitcha.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fitcha.model.dao.MemoDAO;
import com.fitcha.model.vo.MemoVO;

@WebServlet("/memo-regist")
public class MemoRegistController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mdate = request.getParameter("mdate");
		String title = request.getParameter("title");
		String memo = request.getParameter("memo");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		MemoVO mvo = new MemoVO();
		mvo.setId(id);
		mvo.setMdate(Date.valueOf(mdate));
		mvo.setTitle(title);
		mvo.setMemo(memo);
		
		MemoDAO mdao = new MemoDAO();
		int result = mdao.memoRegist(mvo);
		
		boolean code=false;
		
		if(result != 0) {
			//성공
			code = true;
			
		} else {
			//실패
			code = false;
			
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(code);
		
//		response.sendRedirect(request.getContextPath() + "/index");
	}

}
