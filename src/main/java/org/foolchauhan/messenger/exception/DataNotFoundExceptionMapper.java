package org.foolchauhan.messenger.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.foolchauhan.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException dataNotFoundException) {
		ErrorMessage errorMessage = new ErrorMessage(dataNotFoundException.getMessage(), 
													 "No Data : 004", 
													 "foolchauhan.github.io");
		return Response.status(Status.NOT_FOUND)
				       .type(MediaType.APPLICATION_JSON)
					   .entity(errorMessage)
					   .build();
	}

}
