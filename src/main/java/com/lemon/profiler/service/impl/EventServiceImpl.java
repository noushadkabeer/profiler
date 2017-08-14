package com.lemon.profiler.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lemon.profiler.model.Event;
import com.lemon.profiler.service.EventService;

public class EventServiceImpl implements EventService {

	private static ArrayList<Event> eventList;

	static {
		eventList = new ArrayList<Event>();
		eventList.add(new Event("Event001", "Demo","Demonstration of HR Process","20-Mar-14", "Scheduled"));
		eventList.add(new Event("Event002", "Demo","Demonstration of HR Process","20-Mar-14", "Scheduled"));
	}

	Log logger = LogFactory.getLog(this.getClass());

	@Override
	public List<Event> obtainAllEvents() {
		// TODO Auto-generated method stub
		return eventList;
	}

}
