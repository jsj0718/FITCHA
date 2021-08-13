package com.fitcha.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import com.fitcha.model.dao.MemoDAO;
import com.fitcha.model.vo.MemoJson;
import com.fitcha.model.vo.MemoVO;

@WebServlet("/mcalendar")
public class McalendarController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		if(id == null) {
			//로그인 안된 상태
			response.sendRedirect(request.getContextPath() + "/WEB-INF/main/login.jsp");
		} else {
			//로그인 상태
			MemoDAO mdao = new MemoDAO();
			List<MemoVO> mlist = mdao.allMemo(id);
			
			List<MemoJson> mjlist = new ArrayList<MemoJson>();
			for(MemoVO mvo : mlist) {
				MemoJson mj = new MemoJson();
				mj.setId(mvo.getMno());
				mj.setTitle(mvo.getTitle());
				mj.setStart(String.valueOf(mvo.getMdate()));
				mj.setDescription(mvo.getMemo());
				
				mjlist.add(mj);
			}
			
			Gson gson = new Gson();
			String json = gson.toJson(mjlist);
//			request에 json값을 널어서 보내줌
			request.setAttribute("mjson", json);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/main/mcalendar.jsp");
			rd.forward(request, response);
		}
			
		}
		
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
