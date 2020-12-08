package org.foolchauhan.messenger.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.foolchauhan.messenger.model.ErrorMessage;

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 
													 "Generic Error : 001", 
													 "foolchauhan.github.io");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
			       	   .type(MediaType.APPLICATION_JSON)
					   .entity(errorMessage)
					   .build();
	}

}