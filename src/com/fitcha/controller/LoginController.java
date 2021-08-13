package com.fitcha.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fitcha.model.dao.MUserDAO;


@WebServlet("/main-login")
public class LoginController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/test/login.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	//id, pw request
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		//로그인 DB조회
		MUserDAO muserdao = new MUserDAO();
		String result = muserdao.login(id,pw);
		String msg="";
		
		//세션 연결
		if(result!=null) {
			//로그인성공
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			
			//페이지이동(index)
			//sendRedirect는 contextPath 필수!!
			response.sendRedirect(request.getContextPath()+"/main/index.jsp");
			
		}else {
			//로그인 실패! 페이지이동(로그인)
			msg= "ID 또는 PW가 존재하지 않습니다.";
			request.setAttribute("msg", msg);
			
			//페이지이동
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/main/login.jsp");	
			rd.forward(request, response);
		}
    	
    }

}
