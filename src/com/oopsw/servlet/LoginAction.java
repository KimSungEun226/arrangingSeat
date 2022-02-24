package com.oopsw.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oopsw.javabean.Member;
import com.oopsw.javabean.MemberDAO;

public class LoginAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		String url = "login.jsp";

		try {
			int memberId = new MemberDAO().login(id, pw);
			// ȸ�� ���� ������ �̵� - session, request�޸𸮿�
			HttpSession session = request.getSession(true); // Ȯ���� ���� ����
			session.setAttribute("memberId", memberId);
			Member m = new MemberDAO().viewMyInfo(memberId);
			request.setAttribute("memberVO", m);
			url = "controller?cmd=myPageUI";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

}