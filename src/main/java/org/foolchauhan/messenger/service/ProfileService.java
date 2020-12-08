package org.foolchauhan.messenger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.foolchauhan.messenger.database.DatabaseClass;
import org.foolchauhan.messenger.model.Profile;

public class ProfileService {

	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("chchauha", new Profile(1, "chchauha", "Chetan", "Chauhan", new Date()));
		profiles.put("kechauha", new Profile(2, "kechauha", "Ketan", "Chauhan", new Date()));
	}
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);		
	}
	
	public Profile addProfile(Profile profile) {
		if(profile.getProfileName() == null || profiles.containsKey(profile.getProfileName())) {
			return null;
		}
		Profile prof = new Profile();
		prof.setId(profiles.size()+1);
		prof.setCreation_date(new Date());
		prof.setFirstName(profile.getFirstName());
		prof.setLastName(profile.getLastName());
		prof.setProfileName(profile.getProfileName());
		profiles.put(prof.getProfileName(), prof);
		return prof;
	}
	
	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		Profile prof = profiles.get(profile.getProfileName());
		prof.setFirstName(profile.getFirstName());
		prof.setLastName(profile.getLastName());
		
		return prof;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
