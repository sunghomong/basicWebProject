package com.green.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.green.dao.MemberDAO;
import com.green.vo.MemberVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "member/login.jsp";

		HttpSession session = request.getSession();
		if (session.getAttribute("userInfo") != null) {
			
			MemberVO mVo = (MemberVO) session.getAttribute("userInfo");

			String userGrade = mVo.getUsergrade();

			if (userGrade == "학생") { // 학생일때
				url = "member/studentMain.jsp";
			} else if (userGrade == "선생"){ // 선생일때
				url = "member/teacherMain.jsp";
			}
		}

		request.getRequestDispatcher(url).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String url = "member/login.jsp";

		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");

		MemberDAO dao = MemberDAO.getInstance();

		int result = dao.userCheck(userid, userpw);

		if (result == 1) { 
			HttpSession session = request.getSession();
			MemberVO mVo = dao.getMemberByUserid(userid);
			
			// 담임 선생의 이름을 알기위한 메서드 호출
			String ban = mVo.getBan();
			
			String teacherName = dao.getTeacherNameByBan(ban);

			session.setAttribute("userInfo", mVo);

			String userGrade = mVo.getUsergrade();
			
			
			if (userGrade.equals("학생")) {
				url = "member/studentMain.jsp";
				session.setAttribute("teacherName", teacherName);
				
			} else if (userGrade.equals("선생")){ 
				url = "member/teacherMain.jsp";
			}
			
		} else if (result == 0) { 
			request.setAttribute("message", "비밀번호가 일치하지 않습니다.");
		} else if (result == -1) {
			request.setAttribute("message", "비밀번호 또는 아이디를 다시 확인해 주세요.");
		}

		request.getRequestDispatcher(url).forward(request, response);

	}

}
