package com.green.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.green.dao.MemberDAO;
import com.green.vo.MemberVO;

@WebServlet("/noGradeCheck")
public class NoGradeCheckServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ban = request.getParameter("ban");
		System.out.println(ban);
		MemberDAO dao = MemberDAO.getInstance();
		// 반이라는 값을 가져온 뒤 반이라는 값을 통해서
		List<MemberVO> noGradeMemberList = dao.getNoGradeMemberListByBan(ban); // null or list 값

		System.out.println("리스트 뽑아옴");
		if (noGradeMemberList == null || noGradeMemberList.isEmpty()) {

			String chResult = null;

			request.setAttribute("chResult", chResult);

			request.setAttribute("msg", "학생중 미동록자가 없습니다.");

			request.getRequestDispatcher("member/teacherMain.jsp").forward(request, response);

		} else {

//			Gson gson = new Gson();
//		    String memberJson = gson.toJson(noGradeMemberList);
		    String chResult = "미등록자조회";
		    
		    request.getSession().setAttribute("chResult", chResult);
		    
		    request.setAttribute("memberList", noGradeMemberList);
//		    System.out.println(chResult);
//		    request.setAttribute("chResult", chResult); // chResult를 JSP로 전달
//		    response.setContentType("application/json;charset=UTF-8");
		    
		    
		    request.getRequestDispatcher("member/teacherMain.jsp").forward(request, response);
		    
//		    try (PrintWriter out = response.getWriter()) {
//		        out.print(memberJson);
//		    }
			

		}

	}

}
