package org.foolchauhan.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.foolchauhan.messenger.model.Profile;
import org.foolchauhan.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	ProfileService profileService = new ProfileService();
	
	@GET
	public List<Profile> getAllProfiles(){
		return profileService.getAllProfiles();
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getMessage(@PathParam("profileName") String profileName) {
		return profileService.getProfile(profileName);
		
	}
	
	@POST
	public Profile addProfile(Profile profile) {
		Profile prof = profileService.addProfile(profile);
		if(prof == null) {
			return (new Profile(-1, "Invalid profile name", null, null, null));
		}
		return prof;
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateMessage(@PathParam("profileName") String profileName, Profile profile) {
		profile.setProfileName(profileName);
		return  profileService.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	public void removeMessage(@PathParam("profileName") String profileName) {
		profileService.removeProfile(profileName);
		
	}
}
