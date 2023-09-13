package com.green.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.dao.MemberDAO;
import com.green.vo.GradeAvgVO;

@WebServlet("/banGradeCheck")
public class BanGradeCheck extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ban = request.getParameter("ban"); // A,B
		String usergrade = request.getParameter("usergrade"); // 선생, 학생
		
		String url = "member/login.jsp";
		
		MemberDAO dao = MemberDAO.getInstance();
		
		GradeAvgVO gAVo = dao.getMemberGradeByBan(ban);
		
		
		// 학생과 선생 분류를 가져와서 분리하고 그에 따른 결과값 보내주기
		
		if (usergrade.equals("선생")) {
			
			String chResult = "반성적조회";
			url = "member/teacherMain.jsp";
			request.getSession().setAttribute("chResult", chResult);
			
		} else if(usergrade.equals("학생")) {
			
			String gdResult = null;
			String bdResult = "학생";
			url = "member/studentMain.jsp";
			request.setAttribute("gdResult", gdResult); 
			request.setAttribute("bdResult", bdResult);
		}
		
		request.setAttribute("gAVo", gAVo);
		// 모든 평균 점수 불러오기 완료 jsp 파일만 만들면 가능
		
//		System.out.println(gAVo);
	
		request.getRequestDispatcher(url).forward(request, response);
	}

}
