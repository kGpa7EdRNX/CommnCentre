// Prabhat: Similar to User.java; for "Events" database

package com.mie.model;



public class Event {
	private int eventid;
	private String eventName;
	
	public int getEventid() {
		return eventid;
	}

	public void setEventid(int eventid) {
		this.eventid = eventid;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}


	@Override
	public String toString() {
		return "Event [eventid=" + eventid + ", eventName=" + eventName + "]";
	}
}