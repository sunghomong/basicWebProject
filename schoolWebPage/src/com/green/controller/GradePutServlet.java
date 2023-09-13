package com.green.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.dao.MemberDAO;
import com.green.vo.GradeVO;
import com.green.vo.MemberVO;


@WebServlet("/gradePut")
public class GradePutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		String chResult = "성적등록";
		
		request.setAttribute("chResult", chResult);
		
		request.setAttribute("gradePutUsername", username);
		
		request.getRequestDispatcher("teacherMain.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String username = request.getParameter("username");
		int kor_score = Integer.parseInt(request.getParameter("kor_score"));
		int eng_score = Integer.parseInt(request.getParameter("eng_score"));
		int math_score = Integer.parseInt(request.getParameter("math_score"));
		int science_score = Integer.parseInt(request.getParameter("science_score"));
		int social_score = Integer.parseInt(request.getParameter("social_score"));
		
		GradeVO gVo = new GradeVO();
		
		gVo.setUsername(username);
		gVo.setKor_score(kor_score);
		gVo.setEng_score(eng_score);
		gVo.setMath_score(math_score);
		gVo.setScience_score(science_score);
		gVo.setSocial_score(social_score);
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.insertGrade(gVo);
		
		String chResult = null;
		request.setAttribute("chResult", chResult);
		
		request.setAttribute("message", "성적등록이 완료 됐습니다.");
		
		request.getRequestDispatcher("teacherMain.jsp").forward(request, response);
		
	}

}
