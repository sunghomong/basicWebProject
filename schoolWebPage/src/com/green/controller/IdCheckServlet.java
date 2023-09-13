package com.green.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.dao.MemberDAO;

@WebServlet("/checkId")
public class IdCheckServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = request.getParameter("userid");

		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.checkId(userid); // 0,-1

		request.setAttribute("userid", userid);
		request.setAttribute("result", result);

		request.getRequestDispatcher("member/idCheck.jsp").forward(request, response);
	}


}
