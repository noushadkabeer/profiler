package com.lemon.profiler.service;

import java.util.List;

import com.lemon.profiler.model.Job;
import com.lemon.profiler.model.Profile;


public interface JobService {
	public List<Job> obtainAllJobs(); 
	public Job pullJob(String jobId);
    public void update(Job job);
    public void insert(Job job);
    public String delete(String id);
	public List<Job> searchJob(String searchString, String pageNum, String pageSize);
	public Job pullJobByName(String name);
}
