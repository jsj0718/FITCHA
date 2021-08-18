package com.fitcha.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fitcha.model.dao.MainBoardDAO;
import com.fitcha.model.vo.MainBoardVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@WebServlet("/main_board_search")
public class MainBoardSearchContoller extends HttpServlet {

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           
   }
   

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
        
        String searchTitle = request.getParameter("searchBox");
//        if(id==null) {
//           //로그인 안된 상태
//           response.sendRedirect(request.getContextPath()+"/index-login");
//           
//        }else {
           //로그인이 된 상태
           
           MainBoardDAO bdao = new MainBoardDAO();
           List<MainBoardVO> blist = bdao.MainBoardTitleSearch(searchTitle);
           JsonArray jsonArr = new JsonArray();
           
           for(MainBoardVO bvo: blist) {
              JsonObject json = new JsonObject();
              json.addProperty("boardId", bvo.getBoardId());
              json.addProperty("userId", bvo.getUserId());
              json.addProperty("title", bvo.getTitle());
              json.addProperty("poster", bvo.getPoster());
              jsonArr.add(json);
              
           }
//           System.out.println("search="+jsonArr);
           
           Gson gson = new Gson();
           String jsonResponse = gson.toJson(jsonArr);
           response.setContentType("application/json; charset=utf-8");
           PrintWriter out = response.getWriter();
           out.print(jsonResponse);
         
//        }   
  }
}