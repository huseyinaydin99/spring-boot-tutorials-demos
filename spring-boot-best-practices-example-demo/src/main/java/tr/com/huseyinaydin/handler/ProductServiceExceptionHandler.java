package tr.com.huseyinaydin.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tr.com.huseyinaydin.dto.APIResponse;
import tr.com.huseyinaydin.dto.ErrorDTO;
import tr.com.huseyinaydin.exception.ProductNotFoundException;
import tr.com.huseyinaydin.exception.ProductServiceBusinessException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

@RestControllerAdvice
public class ProductServiceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public APIResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception) {
		APIResponse<?> serviceResponse = new APIResponse<>();
		List<ErrorDTO> errors = new ArrayList<>();
		exception.getBindingResult().getFieldErrors().forEach(error -> {
			ErrorDTO errorDTO = new ErrorDTO(error.getField(), error.getDefaultMessage());
			errors.add(errorDTO);
		});
		serviceResponse.setStatus("FAILED");
		serviceResponse.setErrors(errors);
		return serviceResponse;
	}

	@ExceptionHandler(ProductServiceBusinessException.class)
	public APIResponse<?> handleServiceException(ProductServiceBusinessException exception) {
		APIResponse<?> serviceResponse = new APIResponse<>();
		serviceResponse.setStatus("FAILED");
		serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
		return serviceResponse;
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public APIResponse<?> handleProductNotFoundException(ProductNotFoundException exception) {
		APIResponse<?> serviceResponse = new APIResponse<>();
		serviceResponse.setStatus("FAILED");
		serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
		return serviceResponse;
	}
}
