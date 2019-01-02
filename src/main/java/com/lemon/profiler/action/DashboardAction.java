package com.lemon.profiler.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.lemon.profiler.mappers.pagination.ProfileSearchCriteria;
import com.lemon.profiler.mappers.pagination.ProfileSearchResults;
import com.lemon.profiler.model.Event;
import com.lemon.profiler.model.Job;
import com.lemon.profiler.model.Profile;
import com.lemon.profiler.model.Task;
import com.lemon.profiler.model.User;
import com.lemon.profiler.service.EventService;
import com.lemon.profiler.service.JobService;
import com.lemon.profiler.service.ProfileService;
import com.lemon.profiler.service.PropertyReaderService;
import com.lemon.profiler.service.TaskService;
import com.lemon.profiler.service.UnprocessedProfileService;
import com.lemon.profiler.service.UserService;
import com.lemon.profiler.service.impl.EventServiceImpl;
import com.lemon.profiler.service.impl.JobServiceImpl;
import com.lemon.profiler.service.impl.ProfileServiceImpl;
import com.lemon.profiler.service.impl.PropertyReaderServiceImpl;
import com.lemon.profiler.service.impl.TaskServiceImpl;
import com.lemon.profiler.service.impl.UnprocessedProfileServiceImpl;
import com.lemon.profiler.service.impl.UserServiceImpl;
import com.lemon.profiler.util.Pagination;
import com.opensymphony.xwork2.ActionContext;


public class DashboardAction {
	
	private static final Logger log = Logger.getLogger(DashboardAction.class);
	
	JobService jobService;
	ProfileService profileService;
	TaskService taskService;
	EventService eventService;
	UnprocessedProfileService uppservice;
	UserService userService;
	PropertyReaderService propertyService = new PropertyReaderServiceImpl();
	
	public List<Job> jobs;
	public List<Task> tasks;
	public List<Profile> profiles;
	public List<Event> events;
	public List<User> myTeam;
	
	//Initializing Pagination with page size 10, and current page 1 
    private Pagination pagination = new Pagination(10, 1);
    
	//variable to hold unprocessed profile task list size
	public String totalUPTaskList;
	

	public String getTotalUPTaskList() {
		return totalUPTaskList;
	}

	public void setTotalUPTaskList(String totalUPTaskList) {
		this.totalUPTaskList = totalUPTaskList;
	}

	public String prepareDashboard(){		
		log.info("Prepare request at DashboardAction.preparedashboard with Page Items of "+pagination.getPage_size()+ " Page Number "+pagination.getPage_number());
		//get the tasks pending
		taskService = new TaskServiceImpl();
		tasks = taskService.obtainTaskList();
		
		//Resume list
		profileService= new ProfileServiceImpl();
		ProfileSearchCriteria psc = new ProfileSearchCriteria();
		psc.setPageSize( pagination.getPage_size());
		psc.setPageNum(pagination.getPage_number());
		ProfileSearchResults<Profile> searchResults = profileService.obtainAllProfiles(psc);
			//profiles = profileService.obtainAllProfiles(pagination.getPage_number()+"", pagination.getPage_size()+"");
		profiles = searchResults.getResults();
		pagination.setProperties(searchResults.getTotalResults());
		
	//	pagination.setPage_records(Integer.parseInt(propertyService.getKeyValue("pageSize")));
		pagination.setPage_records(searchResults.getTotalResults());
		log.info("Total profiles : "+profiles.size());
		
		//Job listing list
		jobService = new JobServiceImpl();
		jobs = jobService.obtainAllJobs();
		
		//Event list
		eventService = new EventServiceImpl();
		events = eventService.obtainAllEvents();
		
		uppservice = new UnprocessedProfileServiceImpl();
		totalUPTaskList = uppservice.obtainUnProcessedProfileCount(" ", pagination.getPage_number()+"", pagination.getPage_size()+"");
		
		userService = new UserServiceImpl();
		User whoami = (User)ActionContext.getContext().getSession().get("user");
		myTeam = userService.getAllUsers(whoami.userOrganization);
		log.info("Dashboard Prepared with :"+jobs.size()+" Jobs, "+profiles.size() + " Profiles , "+tasks.size()+ " tasks, "+ events.size()+" events.");
			
		return "success";
	}
	
	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
	 public List<User> getMyTeam() {
		return myTeam;
	}

	public void setMyTeam(List<User> myTeam) {
		this.myTeam = myTeam;
	}
}
