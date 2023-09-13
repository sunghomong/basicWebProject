package com.green.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.dao.MemberDAO;
import com.green.vo.MemberVO;

@WebServlet("/productId")
public class ProductId extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("member/productId.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		String ban = request.getParameter("ban");
		String usergrade = request.getParameter("usergrade");
		
		MemberVO mVo = new MemberVO();
		mVo.setUsername(username);
		mVo.setUserid(userid);
		mVo.setUserpw(userpw);
		mVo.setBan(ban);
		mVo.setUsergrade(usergrade);

		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.insertMember(mVo);

		if (result == 1) {
			request.setAttribute("userid", userid);
			request.setAttribute("message", "회원가입에 성공하셨습니다.");
		} else {
			request.setAttribute("message", "회원가입에 실패하셨습니다.");
		}


		request.getRequestDispatcher("member/login.jsp").forward(request, response);
	}

}
