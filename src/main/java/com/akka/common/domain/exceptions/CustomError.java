package com.akka.common.domain.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomError implements Serializable {
  @Serial private static final long serialVersionUID = -2278843985681971729L;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
  private Instant timestamp;

  @JsonIgnore private transient ErrorCode code;
  private String error;
  private String message;

  public CustomError(ErrorCode code) {
    this.timestamp = Instant.now();
    this.code = code;
    this.error = code.getName();
    this.message = code.getMessage();
  }
}
