package com.lemon.profiler.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.lemon.profiler.model.Job; 
import com.lemon.profiler.service.JobService; 
import com.lemon.profiler.service.impl.JobServiceImpl; 
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class JobAction extends ActionSupport implements Preparable{

	private static final long serialVersionUID = 1L;
	private static JobService jobService = new JobServiceImpl();
	private Job job; 
	private List<Job> jobs;

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	private static final Logger log = Logger.getLogger(JobAction.class);


	public String getAllJobs() {
		jobs = jobService.obtainAllJobs();
		return "success";
	}

	public String setUpForInsertOrUpdate() {
		if (job != null && !job.getJobId().isEmpty()) {
			log.info("job not null. :"+job.getJobId());
			job = jobService.pullJob(job.getJobId());
		}
		return "success";
	}

	public String insertOrUpdate() {
		log.info("insert or update check..");
		if (!validationSuccessful()) {
			log.info("Validation failed..");
			return "input";
		} else {
			if (job.getJobId() == null || job.getJobId().isEmpty()) {
				log.info("job insert..");
				jobService.insert(job);
			} else {
				log.info(">>>>>>>>>>>>>>>> job Update"
						+ job.jobId);
				jobService.update(job);
			}
		}
		return "success";
	}

	private boolean validationSuccessful() {
		log.info("==============================>");
		if (job == null) {
			log.info("job null");
			if (log.isDebugEnabled()) {
				log.debug("job - : " + " items found");
			}
		}
		if(job==null || job.getJobExperience() ==null || job.getJobExperience().trim().length()<=0)
			addActionError("Experience is required");
		if(job==null || job.getLocation() ==null || job.getLocation().trim().length()<=0)
			addActionError("Job location is required");
		if (job.getJobType() == null || job.getJobType().trim().length() < 1)
			addActionError("Job Type is required");
		if (job.getAboutJob() == null || job.getAboutJob().trim().length() < 1)
			addActionError("Job description is required");
		if(this.hasActionErrors())
		{
			return false;
		} else {
			return true;
		}
	}

	public String viewJob() {
		log.info("View job");
		return "success";
	}

	public String editJob() {
		log.info("Edit job");
		return "success";
	}

	public String deleteConfirm() {
		log.info(job.jobId);
		job = jobService.pullJob(job.getJobId());
		log.info("Delete Confirmation..");
		return "success";
	}

	public String deleteJob() {
		String result = jobService.delete(job.jobId);
		log.info("Deleting Job" + job.jobId);
		return result;
	}
	
	public String attachJD(){
		job = jobService.pullJob(job.getJobId());
		return "success";
	}
	@Override
	public void prepare() throws Exception {
//		if (job != null && job.getJobTitle() != null) {
//			job = jobService.getJob(job.getJobId());
//		}
	}
}
