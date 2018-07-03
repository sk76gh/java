package org.saravana.jaxrs.jaxrs.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.saravana.jaxrs.jaxrs.model.Profile;
import org.saravana.jaxrs.jaxrs.service.LinkService;
import org.saravana.jaxrs.jaxrs.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	private ProfileService profileService=new ProfileService();
	
	@GET
	public List<Profile> getProfiles(@Context UriInfo uriInfo){
		List<Profile> profiles = profileService.getAllProfiles();
		
		return profiles;
	}
	
	@GET
	@Path("/{profilename}")
	public Profile getProfile(@PathParam("profilename") String firstname){
		Profile profile=profileService.getProfile(firstname);
		return profile;
	}

	@POST
	public Profile addProfile(Profile profile,@Context UriInfo uriInfo){
		profile.addLink(LinkService.getUriForSelf(uriInfo, profile), "self");
		return profileService.addProfile(profile);
	}
	
	@PUT
	@Path("/{profilename}")
	public Profile updateProfile(@PathParam("profilename") String firstName,Profile profile){
		profile.setFirstName(firstName);
		return profileService.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profilename}")
	public Profile deleteMessage(@PathParam("profilename") String firstName){
		return profileService.removeProfile(firstName);
	}

}
