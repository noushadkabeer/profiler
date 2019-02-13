package com.lemon.profiler.action;

import org.apache.log4j.Logger;

import com.lemon.profiler.model.User;
import com.lemon.profiler.util.EmailUtil;
import com.lemon.profiler.util.ValidationHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class EmailCandidateAction extends ActionSupport implements Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(EmailCandidateAction.class);

	private String to;
	private String from;
	private String subject;
	private String content;
	private String cc;
	private String bcc;
	private String jsonString;

	EmailUtil emailUtil = new EmailUtil();

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public String loadReportDashboard() {
		try {

		} catch (Exception e) {
			return "failure";
		}
		return "success";
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	public String draftemailToCandidate() {
		log.debug("To : " + to);
		User usr = (User) ActionContext.getContext().getSession().get("user");
		if (usr != null)
			from = usr.getUserEmail().toString();
		return "input";
	}

	public String emailCandidate() {
		log.debug("Sending Mail To: " + to + " about: " + subject + " by :" + from + " content :" + content);
		try {
			if(validationSuccessful())
				emailUtil.pushMail(subject, to, cc, bcc, content, from);
			else
				return "input";
		} catch (Error e) {
			log.error("Error in Sending email to Candidate " + e);
			return "input";
		}
		log.debug("Send Email");
		return "success";
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public EmailUtil getEmailUtil() {
		return emailUtil;
	}

	public void setEmailUtil(EmailUtil emailUtil) {
		this.emailUtil = emailUtil;
	}

	public boolean validationSuccessful() {
		if(to == null || to.isEmpty() || to.trim().length()<=0)
			addActionError("To is a required field");
		if(from == null || from.isEmpty() || from.trim().length()<=0)
			addActionError("From is a required field");
		if(content == null || content.isEmpty() || content.trim().length()<=0)
			addActionError("Content is a required");
		if(to!=null && !to.isEmpty() ) {
			if(to.contains(";")) {
				String[] toArr = to.split(";");
				for(int i=0; i<toArr.length; i++) {
					if(!ValidationHelper.isValidEmailAddress(to))
						addActionError("Target email :"+toArr[i]+" is not valid");
				}
			}			
		}
		if(to!=null && !to.isEmpty() ) {
			if(!ValidationHelper.isValidEmailAddress(to))
				addActionError("Email entered is not valid");
		}
		if(this.hasActionErrors())
			return false;
		else
			return true;
	}
}
