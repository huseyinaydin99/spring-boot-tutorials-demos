package tr.com.huseyinaydin.spring.rest.advice;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.springframework.http.HttpStatus;

import tr.com.huseyinaydin.spring.rest.api.dto.ErrorMessage;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

	@Override
	public Response toResponse(ServiceException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorMessage(ex.getMessage());
		error.setErrorCode(ex.getStatusCode());
		StringWriter writter = new StringWriter();
		ex.printStackTrace(new PrintWriter(writter));
		return Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).entity(error).type(MediaType.APPLICATION_JSON).build();
	}

}
