package com.akka.common.domain.exceptions;

import java.io.Serial;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomException extends RuntimeException {

  @Serial private static final long serialVersionUID = -1335991811592142594L;
  protected final CustomError error;

  public CustomException(ErrorCode code) {
    super(String.format("Error: %s", code.getName()));
    error = new CustomError(code);
  }
}
