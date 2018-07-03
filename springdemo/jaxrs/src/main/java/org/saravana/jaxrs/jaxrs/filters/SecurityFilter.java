package org.saravana.jaxrs.jaxrs.filters;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;
import org.saravana.jaxrs.jaxrs.model.ErrorMessage;

//@Provider
public class SecurityFilter implements ContainerRequestFilter{
//public class SecurityFilter{
	private static final String AUTHORIZATION_HEADER="Authorization";
	private static final String AUTH_HEADER_PREFIX="Basic ";
	private static final String SECURED_URL="messages";
	//@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("inside filter");
		if(requestContext.getUriInfo().getPath().contains(SECURED_URL)){
		List<String> authHeader= requestContext.getHeaders().get(AUTHORIZATION_HEADER);
		if(authHeader.size()>0){
			String authToken = authHeader.get(0);
			authToken=authToken.replaceFirst(AUTH_HEADER_PREFIX, "");
			String decodedString=Base64.decodeAsString(authToken);
			StringTokenizer tokenizer=new StringTokenizer(decodedString,":");
			String username=tokenizer.nextToken();
			String password=tokenizer.nextToken();
			
			if("user".equals(username) && "pass".equals(password)){
				return;
			}
		}
		Response unauthorized = Response
				.status(Response.Status.UNAUTHORIZED)
				.entity(new ErrorMessage("unauthorized to access",101,"error url"))
				.build();
		requestContext.abortWith(unauthorized);
	}
	}
}
