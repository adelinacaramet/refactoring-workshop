package userservice.application.exceptionhandler;


import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Collections;
import java.util.Comparator;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import userservice.infrastructure.exception.ResourceExistsException;
import userservice.infrastructure.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestProcessingExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public @ResponseBody ResponseError handleInternalServerError(Throwable exception) {
        return createResponseError("internal_server_error");
    }

    private ResponseError createResponseError(String errorMessage) {
        return new ResponseError(new ErrorMessage(errorMessage));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>(createResponseError("unsupported media type"), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>(createResponseError("unsupported media type"), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>(createResponseError("response generation failed"), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>(createResponseError("Missing required parameter '" + exception.getParameterName() + "' of type '" + exception.getParameterType() + "'"), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = exception.getBindingResult();

        FieldError fieldError = Collections.max(bindingResult.getFieldErrors(), new Comparator<FieldError>() {
            @Override
            public int compare(FieldError o1, FieldError o2) {
                return o1.hashCode() - o2.hashCode();
            }
        });

        ResponseError responseError = createResponseError(getErrorMessageForMethodArgumentNotValidException(exception, fieldError));
        return new ResponseEntity<Object>(responseError, headers, status);
    }

    private String getErrorMessageForMethodArgumentNotValidException(MethodArgumentNotValidException exception, FieldError fieldError) {
        return "Bad request: " + fieldError.getField() + "=" + fieldError.getRejectedValue()
                + " expected " + exception.getBindingResult().getFieldType(fieldError.getField()).getSimpleName()
                + ". " + fieldError.getDefaultMessage();
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>(createResponseError("request method not allowed"), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>(createResponseError("request method not allowed"), headers, status);
    }

    @ExceptionHandler
    @ResponseStatus(value = BAD_REQUEST)
    public @ResponseBody ResponseError handleIllegalArgumentException(IllegalArgumentException exception) {
        return createResponseError(exception.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = NOT_FOUND)
    public @ResponseBody ResponseError handleResourceNotFoundException(ResourceNotFoundException exception) {
        return createResponseError(exception.getMessage());
    }

    @ExceptionHandler(ResourceExistsException.class)
    @ResponseStatus(value = CONFLICT)
    public @ResponseBody ResponseError handleResourceExistsException(ResourceExistsException exception) {
        return createResponseError(exception.getMessage());
    }
}
