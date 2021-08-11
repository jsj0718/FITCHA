package com.fitcha.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitcha.model.dao.MUserDAO;
import com.fitcha.model.vo.MUserVO;

@WebServlet("/main-join")
public class JoinController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/main/login.jsp");
	        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("join-id");
		String pw = request.getParameter("join-pw");
		String name = request.getParameter("join-name");
		String email= request.getParameter("join-email");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		
		
		MUserDAO mudao = new MUserDAO();
		String resultUserId = mudao.selectUserId(id);
		
		String msg = "";
		//id조회 성공 - 가입가능
		//pk의 id가 없으면 회원가입을 진행하도록하겠음.
		if(resultUserId == null){
			//회원가입-DB insert
			MUserVO muvo = new MUserVO();
			muvo.setUserId(id);
			muvo.setUserPw(pw);
			muvo.setUserName(name);
			muvo.setEmail(email);
			muvo.setGender(gender);
			muvo.setBirth(Date.valueOf(birth));
			
			int result = mudao.join(muvo);
			//결과가 1- 성공
			if(result == 1){
				msg="회원가입에 성공하였습니다.";
				response.sendRedirect(request.getContextPath()+"/main/login.jsp");
			}else{
				//결과가 0- 실패
				msg="회원가입에 실패하였습니다.다시 시도 해주세요";
			}
		
		}else{
		
			//id 조회실패
			//ID값이 존재합니다.
			msg="ID값이 존재합니다.";
			request.setAttribute("msg", msg);
			//페이지이동
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/main/login.jsp");	
			rd.forward(request, response);
			
		}
		
		
	}

}
