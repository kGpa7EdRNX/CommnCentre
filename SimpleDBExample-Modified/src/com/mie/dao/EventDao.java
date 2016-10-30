//Prabhat: Similar to UserDao but only with EventID and eventName
package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mie.model.Event;
import com.mie.util.DbUtil;

public class EventDao {

	private Connection connection;

	public EventDao() {
		connection = DbUtil.getConnection();
	}

	public void addEvent(Event event) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into events(eventname) values (? )");
			// Parameters start with 1
			preparedStatement.setString(1, event.getEventName());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteEvent(int eventId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from events where eventid=?");
			// Parameters start with 1
			preparedStatement.setInt(1, eventId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateEvent(Event event) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update events set eventname=?" + " where eventid=?");
			// Parameters start with 1
			preparedStatement.setString(1, event.getEventName());
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Event> getAllEvents() {
		List<Event> events = new ArrayList<Event>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from events");
			while (rs.next()) {
				Event event = new Event();
				event.setEventid(rs.getInt("eventid"));
				event.setEventName(rs.getString("eventname"));
				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return events;
	}

	public Event getEventById(int eventId) {
		Event event = new Event();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from events where eventid=?");
			preparedStatement.setInt(1, eventId);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				event.setEventid(rs.getInt("eventid"));
				event.setEventName(rs.getString("eventname"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return event;
	}
	
/*	public User getUserByFName(String name) {
		System.out.println("test");
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from users where firstname=?");
			preparedStatement.setString(1, name);
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("test.................");
			if (rs.next()) {
				user.setUserid(rs.getInt("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setDob(rs.getDate("dob"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
	*/
	public List<Event> getEventByKeyword(String keyword) {
		List<Event> events = new ArrayList<Event>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from events where eventname LIKE ?");
			
//			SELECT title FROM pages WHERE my_col LIKE %$param1% OR another_col LIKE %$param2%;
			
			preparedStatement.setString(1, "%" + keyword + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Event event = new Event();
				event.setEventid(rs.getInt("eventid"));
				event.setEventName(rs.getString("eventname"));
				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return events;
	}
	
}