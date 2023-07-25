package com.akka.common.infrastructure.configuration.exceptions;

import com.akka.common.domain.errors.GeneralErrorCode;
import com.akka.common.domain.exceptions.CustomError;
import com.akka.common.domain.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({CustomException.class})
  public ResponseEntity<Object> handleAll(final CustomException ex) {
    log.error("Rest Api Exception: {}", ex.getMessage());
    return new ResponseEntity<>(ex.getError(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleAll(Exception ex) {
    log.error("EXCEPTION NOT CONTROLLED", ex);
    CustomError rae = new CustomError(GeneralErrorCode.EXCEPTION_NOT_CONTROLLED);
    return new ResponseEntity<>(rae, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
