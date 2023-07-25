package com.akka.common.domain.exceptions;

public interface ErrorCode {

  String getName();

  int getValue();

  String getMessage();
}
