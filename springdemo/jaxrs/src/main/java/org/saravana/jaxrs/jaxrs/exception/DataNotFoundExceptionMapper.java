package org.saravana.jaxrs.jaxrs.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.saravana.jaxrs.jaxrs.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		// TODO Auto-generated method stub
	    ErrorMessage errorMessage=new ErrorMessage(ex.getMessage(),404,"404 error url");
		return Response
				.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
