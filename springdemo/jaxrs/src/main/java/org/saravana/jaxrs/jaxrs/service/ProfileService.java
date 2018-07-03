package org.saravana.jaxrs.jaxrs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.saravana.jaxrs.jaxrs.database.DatabaseClass;
import org.saravana.jaxrs.jaxrs.model.Profile;
 
public class ProfileService {
	private Map<String, Profile> profiles= DatabaseClass.getProfiles();
	
	public ProfileService(){
		profiles.put("saravana", new Profile(1L,"saravana","kumar"));
		profiles.put("arul", new Profile(2L,"arul","sub"));
	}
	
	public List<Profile> getAllProfiles(){
			
		return new ArrayList<Profile>(profiles.values());
	}
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getFirstName(), profile);
		return profile;
	}
	public Profile updateProfile(Profile profile){
		if (profile.getFirstName().isEmpty()){
			return null;
		}
		profiles.put(profile.getFirstName(), profile);
		//System.out.println(profiles.toString());
		return profile;
	}
	public Profile removeProfile(String profileName){
		
		return profiles.remove(profileName);
		
	}

}