//Prabhat: Similar to UserController; with 2 attributes
package com.mie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.EventDao;
import com.mie.model.Event;

public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/event.jsp";
	private static String LIST_EVENT = "/listEvent.jsp";
	private static String SEARCH_NAME_EVENT = "/searchNEvent.jsp";
	private EventDao dao;

	public EventController() {
		super();
		dao = new EventDao();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("delete")) {
			int eventId = Integer.parseInt(request.getParameter("eventId"));
			dao.deleteEvent(eventId);
			forward = LIST_EVENT;
			request.setAttribute("events", dao.getAllEvents());
		} else if (action.equalsIgnoreCase("edit")) {
			forward = INSERT_OR_EDIT;
			int eventId = Integer.parseInt(request.getParameter("eventId"));
			Event event = dao.getEventById(eventId);
			request.setAttribute("event", event);
		} else if (action.equalsIgnoreCase("listEvent")) {
			forward = LIST_EVENT;
			request.setAttribute("events", dao.getAllEvents());
		} else {
			forward = INSERT_OR_EDIT;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Event event = new Event();
		event.setEventName(request.getParameter("eventName"));
		String eventid = request.getParameter("eventid");
		if (eventid == null || eventid.isEmpty()) {
			dao.addEvent(event);
		} else {
			event.setEventid(Integer.parseInt(eventid));
			dao.updateEvent(event);
		}
		RequestDispatcher view = request.getRequestDispatcher(LIST_EVENT);
		request.setAttribute("events", dao.getAllEvents());
		view.forward(request, response);
	}
}