package com.lemon.profiler.service;

import java.util.ArrayList;
import java.util.List;

import com.lemon.profiler.model.Profile;
import com.lemon.profiler.model.Task;
import com.lemon.profiler.model.Node;
 

public interface SearchService {
	public ArrayList<Node> searchText(String textToSearch, String pageNum, String pageSize);
	public List<Profile> searchProfile(Profile profile, String pageNum, String pageSize);
	public List<Task> searchTask(Task task);

}
