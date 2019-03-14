package com.lemon.profiler.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.lemon.profiler.mappers.pagination.ProfileSearchResults;
import com.lemon.profiler.mappers.pagination.TextSearchCriteria;
import com.lemon.profiler.mappers.pagination.TextSearchResults;
import com.lemon.profiler.model.Node;
import com.lemon.profiler.model.Profile;
import com.lemon.profiler.service.ProfileService;
import com.lemon.profiler.service.SearchService;
import com.lemon.profiler.service.impl.ProfileServiceImpl;
import com.lemon.profiler.service.impl.SearchServiceImpl;
import com.lemon.profiler.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class SearchAction extends ActionSupport implements Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SearchService searchService = new SearchServiceImpl();
	private ProfileService profileService = new ProfileServiceImpl();
	private Profile profile;
	private List<Node> nodes;
	private String textToSearch;
	public String getTextToSearch() {
		return textToSearch;
	}

	public void setTextToSearch(String textToSearch) {
		this.textToSearch = textToSearch;
	}
	public List<Profile> profileList;

	public List<Profile> getProfileList() {
		return profileList;
	}

	public void setProfileList(List<Profile> profileList) {
		this.profileList = profileList;
	}
	private static final Logger log = Logger.getLogger(SearchAction.class);

	public String textSearch() {
		try{

			log.info("Searching for text :"+textToSearch);
			TextSearchCriteria tsc = new TextSearchCriteria();
			tsc.setPageSize( pagination.getPage_size());
			tsc.setPageNum(pagination.getPage_number());
			TextSearchResults<Node> nodesR = searchService.searchTextByCriteria(tsc, textToSearch);
			nodes = nodesR.getResults();
		//nodes = searchService.searchText(textToSearch, ""+pagination.getPage_number(), ""+pagination.getPage_size());
		pagination.setProperties(nodesR.getTotalResults());
		
	//	pagination.setPage_records(Integer.parseInt(propertyService.getKeyValue("pageSize")));
		pagination.setPage_records(nodesR.getTotalResults());
		
		}
		
		catch(Exception e){
			e.printStackTrace();
			return "failure";
		}
		return "success";
	}	
	
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	

	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	 //Initializing Pagination with page size 10, and current page 1 
    private Pagination pagination = new Pagination(10, 1);
    
    
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
}
