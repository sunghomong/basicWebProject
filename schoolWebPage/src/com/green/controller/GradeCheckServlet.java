package com.green.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.dao.MemberDAO;
import com.green.vo.GradeVO;

/**
 * Servlet implementation class GradeCheckServlet
 */
@WebServlet("/gradeCheck")
public class GradeCheckServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "member/login.jsp";
		String usergrade = request.getParameter("usergrade");
		String username = request.getParameter("username");

		MemberDAO dao = MemberDAO.getInstance();

		GradeVO grades = null;

		int result = dao.checkGrade(username); // -1(오류),0(성적 테이블에 없음),1(성적 테이블에 존재함)

		if (usergrade.equals("선생")) {
			String chResult = "학생성적조회";

			if (result == 1) { // 선생 화면에서 조회시 학생이 성적테이블에 존재한다면
				grades = dao.getGradeByUserName(username);

				int totalGrade = grades.getEng_score() + grades.getKor_score() + grades.getMath_score()
						+ grades.getScience_score() + grades.getSocial_score();
				float avgGrade = totalGrade / 5;
				
				request.setAttribute("totalGrade", totalGrade);
				request.setAttribute("avgGrade", avgGrade);
			}

			request.getSession().setAttribute("chResult", chResult);

			url = "member/teacherMain.jsp";
		} else {
			// 만약 학생이 클릭시
			String gdResult = "통과"; // 학생 화면에 출력
			String bdResult = null; // 학생 화면에 출력 x

			if(result == 1) { // 학생이 성적 조회를 눌렀을때 본인의 성적이 등록이 되어 있다면
				grades = dao.getGradeByUserName(username);
			}
			
			request.setAttribute("bdResult", bdResult);
			request.setAttribute("gdResult", gdResult);

			url = "member/studentMain.jsp";
		}

		// 이름을 통한 성적들 출력
		request.setAttribute("grades", grades);

		request.getRequestDispatcher(url).forward(request, response);
	}

}
