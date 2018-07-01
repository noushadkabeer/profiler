package com.lemon.profiler.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.lemon.profiler.common.ProfilerUtil;
import com.lemon.profiler.constants.ProfilerConstants;
import com.lemon.profiler.model.Profile;
import com.lemon.profiler.service.ProfileService;
import com.lemon.profiler.service.impl.ProfileServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class ProfileAction extends ActionSupport implements Preparable, SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ProfileService profileService = new ProfileServiceImpl();
	private Profile profile;

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	private static final Logger log = Logger.getLogger(ProfileAction.class);

	private List<Profile> profiles;

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public String getAllProfiles() {
		profiles = profileService.obtainAllProfiles(ProfilerUtil.readSessionConfig(ProfilerConstants.PROPERTY_PAGE_NO),ProfilerUtil.readSessionConfig(ProfilerConstants.PROPERTY_PAGE_SIZE));
		return "success";
	}

	public String setUpForInsertOrUpdate() {
		if (profile != null && !profile.getId().isEmpty()) {
			log.info("Profile not null.");
			profile = profileService.pullProfile(profile.getId());
		}
		return "success";
	}

	public String insertOrUpdate() {
//		log.info("insert or update check..");
		if (!validationSuccessful()) {
			log.info("Validation failed..");
			return "input";
		} else {
			if (profile.getId() == null || profile.getId().isEmpty()) {
				log.info("Profile insert..");
//				log.info("----------->"+ActionContext.getContext().getSession().get("alf_ticket"));
				profileService.insert(profile);
			} else {
				log.info(">>>>>>>>>>>>>>>> Profile Update"
						+ profile.id);
				profileService.update(profile);
			}
		}
		return "success";
	}

	private boolean validationSuccessful() { 
		
		if (profile == null) {
			log.info("profile null");
			if (log.isDebugEnabled()) {
				log.debug("profile - : " + " items found");
			}
		}
		if (profile.getName() == null || profile.getName().trim().length() < 1) {
			 addActionMessage("Name is required");
			return false;
		}
		
		if(this.hasActionMessages()){
			 return false;
		} else {
			return true;
		}
	}

	public String viewProfile() {
		
		if(profile!=null || !profile.getId().isEmpty()){
			profile = profileService.pullProfile(profile.getId());
		}
		log.info("View Profile");
		return "success";
	}

	public String editProfile() {
		log.info("Edit Profile");
		return "success";
	}

	public String deleteConfirm() {
		log.info(profile.id);
		profile = profileService.pullProfile(profile.getId());
		log.info("Delete Confirmation..");
		return "success";
	}

	public String deleteProfile() {
		String result = profileService.delete(profile.id);
		log.info("Deleting Profile" + profile.id);
		return result;
	}

	public String attachResume(){
		log.info(profile.getId());
		profile = profileService.pullProfile(profile.getId());
		return "success";
	}
	@Override
	public void prepare() throws Exception {
//		if (profile != null && profile.getName() != null) {
//			profile = profileService.getProfile(profile.getName());
//		}
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	Map<String, Object> session;
}
