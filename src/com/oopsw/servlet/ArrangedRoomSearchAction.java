package com.oopsw.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.javabean.Member;
import com.oopsw.javabean.Room;
import com.oopsw.javabean.RoomDAO;
import com.oopsw.javabean.SeatHistory;
import com.oopsw.javabean.SeatHistoryDAO;

public class ArrangedRoomSearchAction implements Action {

	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException, SQLException, ClassNotFoundException, SeatHistoryNotFoundException {
		//educationNumber, roomNumber
		SeatHistoryDAO seatHistoryDAO = new SeatHistoryDAO();
		String date = request.getParameter("date");
		int memberId = (int) request.getSession().getAttribute("memberId");
		System.out.println("memberId" + memberId);
		SeatHistory sh =  seatHistoryDAO.searchSeatHistoryByMemberId(date, memberId);
		
		if(sh==null) throw new SeatHistoryNotFoundException("�ش��ϴ� ��ġ�̷��� �������� �ʽ��ϴ�.");
		
		System.out.println(sh);
		
		
		//return "arrangeSeatHistory.jsp";
		return "controller?cmd=arrangeSeatHistory&educationNumber="+
		sh.getEducationNumber()+"&roomNumber="+sh.getRoomNumber()
		+"&date="+date;
	}

}
