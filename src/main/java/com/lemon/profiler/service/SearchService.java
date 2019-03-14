package com.lemon.profiler.service;

import java.util.ArrayList;
import java.util.List;

import com.lemon.profiler.mappers.pagination.TextSearchCriteria;
import com.lemon.profiler.mappers.pagination.TextSearchResults;
import com.lemon.profiler.model.Node;
import com.lemon.profiler.model.Profile;
import com.lemon.profiler.model.Task;
 

public interface SearchService {
	public ArrayList<Node> searchText(String textToSearch, String pageNum, String pageSize);
	public List<Profile> searchProfile(Profile profile, String pageNum, String pageSize);
	public List<Task> searchTask(Task task);
	public TextSearchResults<Node> searchTextByCriteria(TextSearchCriteria textSearchCriteria, String textToSearch);

}
