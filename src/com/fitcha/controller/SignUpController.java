package com.fitcha.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitcha.model.dao.MUserDAO;
import com.fitcha.model.vo.MUserVO;

@WebServlet("/sign-up")
public class SignUpController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/test/sign-up.jsp");
	      rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    request.setCharacterEncoding("UTF-8");
	    
		String id = request.getParameter("user-id");
		String pw = request.getParameter("user-password");
		String pwCheck = request.getParameter("user-password-check");
		String name = request.getParameter("user-name");
		String email= request.getParameter("user-email");
		String birth = request.getParameter("user-birth");
		String gender = request.getParameter("user-gender");
						
		String msg = "";
		if (!pw.equals(pwCheck)) {
		    msg = "비밀번호가 일치하지 않습니다.";
		    request.setAttribute("msg", msg);
            
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/test/sign-up.jsp");   
            rd.forward(request, response);
		} else if (!pattern("id", id)){
            msg = "아이디 양식이 올바르지 않습니다. <br> 영문, 숫자만을 이용하여 5 ~ 20 자리를 입력해주세요.";
            request.setAttribute("msg", msg);
            
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/test/sign-up.jsp");   
            rd.forward(request, response);
		} else if (!pattern("pw", pw)) {
		    msg = "비밀번호 양식이 올바르지 않습니다. <br> 영문, 숫자, 특수문자를 포함하여 8 ~ 20 자리를 입력해주세요.";
            request.setAttribute("msg", msg);
            
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/test/sign-up.jsp");   
            rd.forward(request, response);
		} else if (!pattern("name", name)) {
		    msg = "이름 양식이 올바르지 않습니다. <br> 영문과 한글로만 입력해주세요.";
            request.setAttribute("msg", msg);
            
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/test/sign-up.jsp");   
            rd.forward(request, response);
		} else {
		    MUserDAO udao = new MUserDAO();
		    String resultUserId = udao.selectUserId(id);
		    
		    // id가 없는 경우 (회원가입 가능)
		    if(resultUserId == null){
		        MUserVO uvo = new MUserVO();
		        uvo.setUserId(id);
		        uvo.setUserPw(pw);
		        uvo.setUserName(name);
		        uvo.setEmail(email);
		        uvo.setBirth(Date.valueOf(birth));
		        uvo.setGender(gender);
		        
		        int result = udao.signup(uvo);
		        // 회원가입 성공
		        if(result == 1){
		            msg="회원가입에 성공하였습니다.";
		            request.setAttribute("id", id);
		            
		            response.sendRedirect(request.getContextPath() + "/sign-in");
		            // 회원가입 실패
		        } else {
		            msg="회원가입에 실패하였습니다.다시 시도 해주세요";
		            request.setAttribute("msg", msg);
		            
		            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/test/sign-up.jsp");   
		            rd.forward(request, response);
		        }
		        
		    } else {		
		        // 이미 존재하는 회원이 있는 경우
		        msg="이미 존재하는 아이디입니다.";
		        request.setAttribute("msg", msg);
		        
		        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/test/sign-up.jsp");	
		        rd.forward(request, response);
		        
		    }
		    
		}

	}
	
    // 정규화 메소드
    public static boolean pattern(String key, String value) {
        Pattern pattern = null;
        Matcher matcher = null;
        
        if (key.equals("id")) {
            pattern = Pattern.compile("^[a-zA-Z0-9]{5,20}$");
            matcher = pattern.matcher(value);
            return matcher.find();
        } else if (key.equals("pw")) {
            pattern = Pattern.compile("^.*(?=^.{8,20}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$");
            matcher = pattern.matcher(value);
            return matcher.find();
        } else if (key.equals("name")) {
            pattern = Pattern.compile("^[a-zA-Z가-힣]{2,30}$");
            matcher = pattern.matcher(value);
            return matcher.find();
        }
        
        return false;
    }


}
