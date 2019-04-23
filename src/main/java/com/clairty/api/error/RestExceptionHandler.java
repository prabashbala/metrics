package com.clairty.api.error;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
  {
    String error = "Malformed JSON request";
    return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex.getMessage()));
  }

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError)
  {
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
  {
    List<String> errors = new ArrayList<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors())
    {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }

    for (ObjectError error : ex.getBindingResult().getGlobalErrors())
    {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
    return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
  }
}