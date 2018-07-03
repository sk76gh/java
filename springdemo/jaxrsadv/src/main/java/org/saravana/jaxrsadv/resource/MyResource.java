package org.saravana.jaxrsadv.resource;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("{pathparam}/test")
public class MyResource {

	
	@PathParam("pathparam") private String pathParamExample;
	@QueryParam("query") private String queryParamExample;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String testMethod(){
		
		return"path param used "+ pathParamExample+" "+queryParamExample + Calendar.getInstance().getTime();
	}

	@GET
	@Path("date")
	@Produces(MediaType.TEXT_PLAIN)
	public Date getdate(){
		
		return Calendar.getInstance().getTime();
	}
	
	@GET
	@Path("shortdate")
	@Produces("text/shortdate")
	public Date getShortDate(){
		
		return Calendar.getInstance().getTime();
	}
	
	public String getPathParamExample() {
		return pathParamExample;
	}

	public void setPathParamExample(String pathParamExample) {
		this.pathParamExample = pathParamExample;
	}

	public String getQueryParamExample() {
		return queryParamExample;
	}

	public void setQueryParamExample(String queryParamExample) {
		this.queryParamExample = queryParamExample;
	}
}
