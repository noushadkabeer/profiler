package com.lemon.profiler.service;

import java.util.List;

import com.lemon.profiler.model.Event;


public interface EventService {
	public List<Event> obtainAllEvents();
}
