package com.lemon.profiler.model;

public class Task {

	public Task(){}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPercentcomplete() {
		return percentcomplete;
	}

	public void setPercentcomplete(String percentcomplete) {
		this.percentcomplete = percentcomplete;
	}

	public String getDuetoday() {
		return duetoday;
	}

	public void setDuetoday(String duetoday) {
		this.duetoday = duetoday;
	}

	public String getOverdue() {
		return overdue;
	}

	public void setOverdue(String overdue) {
		this.overdue = overdue;
	}

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	private String id;
	private String name;
	private String description;
	private String type;
	private String taskid;
	private String startdate;
	private String priority;
	private String status;
	private String percentcomplete;
	private String duetoday;
	private String overdue;
	private String duedate;
	public Task(String id, String name, String description, String type,
			String taskid, String startdate, String priority, String status,
			String percentcomplete, String duetoday, String overdue,
			String duedate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.taskid = taskid;
		this.startdate = startdate;
		this.priority = priority;
		this.status = status;
		this.percentcomplete = percentcomplete;
		this.duetoday = duetoday;
		this.overdue = overdue;
		this.duedate = duedate;
	}

}