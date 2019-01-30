package com.lemon.profiler.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.lemon.profiler.common.ProfilerUtil;
import com.lemon.profiler.constants.ProfilerConstants;
import com.lemon.profiler.model.Profile;
import com.lemon.profiler.service.ProfileService;
import com.lemon.profiler.service.impl.ProfileServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class EventAction extends ActionSupport implements Preparable{
	private static final Logger log = Logger.getLogger(EventAction.class);
	private static final long serialVersionUID = 1L;
	private static ProfileService profileService = new ProfileServiceImpl();
	private Profile profile;

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
 
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
			log.debug("Profile not null.");
			profile = profileService.pullProfile(profile.getId());
		}
		return "success";
	}

	public String insertOrUpdate() {
		log.debug("insert or update check..");
		if (!validationSuccessful()) {
			log.debug("Validation failed..");
			return "input";
		} else {
			if (profile.getId() == null || profile.getId().isEmpty()) {
				log.debug("Profile insert..");
				profileService.insert(profile);
			} else {
				log.debug(">>>>>>>>>>>>>>>> Profile Update"
						+ profile.id);
				profileService.update(profile);
			}
		}
		return "success";
	}

	private boolean validationSuccessful() {
		log.debug("==============================>");
		if (profile == null) {
			log.debug("profile null");
			if (log.isDebugEnabled()) {
				log.debug("profile - : " + " items found");
			}
		}
		if (profile.getName() == null || profile.getName().trim().length() < 1) {
			// addActionMessage("Name is required");
			return false;
			// }
			// if(this.hasActionMessages()){
			// return false;
		} else {
			return true;
		}
	}

	public String viewProfile() {
		log.debug("View Profile");
		return "success";
	}

	public String editProfile() {
		log.debug("Edit Profile");
		return "success";
	}

	public String deleteConfirm() {
		log.debug(profile.id);
		profile = profileService.pullProfile(profile.getId());
		log.debug("Delete Confirmation..");
		return "success";
	}

	public String deleteProfile() {
		String result = profileService.delete(profile.id);
		log.debug("Deleting Profile" + profile.id);
		return result;
	}

	@Override
	public void prepare() throws Exception {
		if (profile != null && profile.getName() != null) {
			profile = profileService.pullProfile(profile.getName());
		}
	}
}
