//PRABHAT: Changed to reflect events database
package com.mie.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.EventDao;
import com.mie.model.Event;
import com.mie.util.DbUtil;

public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/event.jsp";
	private static String LIST_EVENT = "/listEvent.jsp";
	private static String SEARCH_EVENT = "/searchNEvent.jsp";
	private EventDao dao;

	private Connection connection;

	public SearchController() {
		super();
		dao = new EventDao();
		connection = DbUtil.getConnection();
	}


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = SEARCH_EVENT;

		String pid = request.getParameter("pid");

//        ArrayList al = null;
//        ArrayList pid_list = new ArrayList();
//        String query = "select * from users where userid='" + pid + "' ";
//
//        System.out.println("query " + query);
//        st = conn.createStatement();
//        ResultSet rs = st.executeQuery(query);

        
    	List<Event> events = new ArrayList<Event>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from events where eventid='" + pid + "' ");
			while (rs.next()) {
				Event event = new Event();
				event.setEventid(rs.getInt("eventid"));
				event.setEventName(rs.getString("eventname"));
				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//return users;
        
//        while (rs.next()) {
//            al = new ArrayList();
//
////            out.println(rs.getString(1));
////            out.println(rs.getString(2));
////            out.println(rs.getString(3));
////            out.println(rs.getString(4));
//            al.add(rs.getString(1));
//            al.add(rs.getString(2));
//            al.add(rs.getString(3));
//            al.add(rs.getString(4));
//
//            System.out.println("al :: " + al);
//            pid_list.add(al);
//        }

		 request.setAttribute("piList",events);
         RequestDispatcher view = request.getRequestDispatcher("/searchview.jsp");
         view.forward(request, response);
	}

//	protected void doPost(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		User user = new User();
//		user.setFirstName(request.getParameter("firstName"));
//		user.setLastName(request.getParameter("lastName"));
//		try {
//			Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(request
//					.getParameter("dob"));
//			user.setDob(dob);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		user.setEmail(request.getParameter("email"));
//		String userid = request.getParameter("userid");
//		if (userid == null || userid.isEmpty()) {
//			dao.addUser(user);
//		} else {
//			user.setUserid(Integer.parseInt(userid));
//			dao.updateUser(user);
//		}
//		RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
//		request.setAttribute("users", dao.getAllUsers());
//		view.forward(request, response);
//	}
}