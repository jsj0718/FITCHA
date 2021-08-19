package com.fitcha.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fitcha.model.dao.MUserDAO;


@WebServlet("/sign-in")
public class SignInController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/test/sign-in.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        // 파라미터 값 받아오기
		String id = request.getParameter("user-id");
		String pw = request.getParameter("user-password");
		String cookieCheck = request.getParameter("cookie-check");
		
		// 로그인 DB 조회
		MUserDAO udao = new MUserDAO();
		String result = udao.signin(id, pw);
		String msg="";
		
		// 쿠키 설정
		Cookie cookie = new Cookie("cookieCheck", id);
		
		if (cookieCheck == null) {
		    cookieCheck = "N";
		}
		
		// 로그인 성공 시
		if(result != null) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			
			// 쿠키 확인
			if (cookieCheck.equals("Y")) {
			    cookie.setMaxAge(60*60*24);  // 1일
			    cookie.setPath("/");
			} else {
			    cookie.setMaxAge(0); // 만료
			    cookie.setPath("/");
			}
			
			// 페이지 이동 (main-movie)
			// sendRedirect는 contextPath 필수!!
			response.addCookie(cookie);
			response.sendRedirect(request.getContextPath() + "/main-movie");
					
		// 로그인 실패 시
		} else {
			msg = "잘못된 정보입니다. 다시 입력해주세요.";
			request.setAttribute("msg", msg);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/test/sign-in.jsp");	
			rd.forward(request, response);
		}
    	
    }

}
