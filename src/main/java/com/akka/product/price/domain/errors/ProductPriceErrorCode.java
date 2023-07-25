package com.akka.product.price.domain.errors;

import com.akka.common.domain.exceptions.ErrorCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ProductPriceErrorCode implements ErrorCode {
  PRICE_NOT_FOUND(1, "Price not found for that product"),
  PRICE_INVALID(2, "Invalid price for that product"),
  PRICE_PRIORITY_DUPLICATE(3, "Same priority for different available prices");

  private final int value;
  private final String message;

  @Override
  public String getName() {
    return this.name();
  }
}
