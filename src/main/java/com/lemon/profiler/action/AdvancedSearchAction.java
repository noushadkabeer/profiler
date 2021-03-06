package com.lemon.profiler.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.lemon.profiler.model.Node;
import com.lemon.profiler.model.Profile;
import com.lemon.profiler.service.ProfileService;
import com.lemon.profiler.service.impl.ProfileServiceImpl;
import com.lemon.profiler.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

//@InterceptorRefs({
//    @InterceptorRef(value="defaultStack", 
//    params={"params.excludeParams", "^action:(?!advancedSearch$)\\w*$",
//   "params.acceptParamNames", "^action:(?!advancedSearch$)\\w*$, id, name, email, number, deleteOneRow", 
//    "validation.excludeMethods", "test"})
//})
public class AdvancedSearchAction extends ActionSupport implements Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private SearchService searchService = new SearchServiceImpl();
	private ProfileService profileService = new ProfileServiceImpl();
	// Initializing Pagination with page size 10, and current page 1
	private Pagination pagination = new Pagination(10, 1);

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	private Profile profile;

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	private List<Node> nodes;
	public List<Profile> profileList;

	public List<Profile> getProfileList() {
		return profileList;
	}

	public void setProfileList(List<Profile> profileList) {
		this.profileList = profileList;
	}

	private static final Logger log = Logger.getLogger(AdvancedSearchAction.class);

	private List<Profile> profiles;

	@Override
	public String execute() {
		try {
			log.debug("Advanced search initiated :" + pagination.getPage_size() + " Page Number "
					+ pagination.getPage_number());
			if (pagination.getPage_size() <= 0)
				pagination.setPage_size(10);
			if (pagination.getPage_number() <= 0)
				pagination.setPage_number(1);
			log.debug("New :" + pagination.getPage_size() + " : " + pagination.getPage_number());
			log.debug(profile == null);
			if (profile == null || profile.getId().isEmpty() && profile.getName().isEmpty()
					&& profile.getAddress().isEmpty() && profile.getEducation().isEmpty()
					&& profile.getSkills().isEmpty() && profile.getLocation().isEmpty()
					&& profile.getExperience().isEmpty() && profile.getResumeSummary().isEmpty()) {
				log.debug("Returning for inputs");
				return "input";
			}
			log.debug("Proceeding with search as profile info filled" + profile.getName());
			profileList = profileService.advSearchProfile(profile, "" + pagination.getPage_number(),
					"" + pagination.getPage_size());
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}

		return "success";
	}

	public String searchedProfiles() {
		return "";
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}
}
