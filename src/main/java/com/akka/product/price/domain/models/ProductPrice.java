package com.akka.product.price.domain.models;

import com.akka.common.domain.exceptions.CustomException;
import com.akka.product.price.domain.errors.ProductPriceErrorCode;
import com.akka.product.price.domain.types.CurrencyType;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import lombok.Data;

@Data
public class ProductPrice {
  private Integer id;
  private Integer tariff;
  private Integer priority;
  private Double price;
  private CurrencyType currency;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private LocalDateTime selectedDate;
  private Brand brand;

  public ProductPrice findPriorityPrice(List<ProductPrice> productPrices) {
    List<ProductPrice> validProductPrices = pricesOnDate(productPrices);
    return getPriorityPrice(validProductPrices);
  }

  private List<ProductPrice> pricesOnDate(List<ProductPrice> productPrices) {
    List<ProductPrice> validProductPrices =
        productPrices.stream()
            .filter(
                product ->
                    selectedDate.isAfter(product.getStartDate())
                        && selectedDate.isBefore(product.getEndDate()))
            .toList();

    if (validProductPrices.isEmpty()) {
      throw new CustomException(ProductPriceErrorCode.PRICE_INVALID);
    }

    return validProductPrices;
  }

  private ProductPrice getPriorityPrice(List<ProductPrice> validProductPrices) {

    ProductPrice highestPriorityPrice =
        validProductPrices.stream()
            .max(Comparator.comparingInt(ProductPrice::getPriority))
            .orElseThrow(() -> new CustomException(ProductPriceErrorCode.PRICE_INVALID));

    long countDuplicates =
        validProductPrices.stream()
            .filter(
                product ->
                    Objects.equals(product.getPriority(), highestPriorityPrice.getPriority()))
            .count();

    if (countDuplicates > 1) {
      throw new CustomException(ProductPriceErrorCode.PRICE_PRIORITY_DUPLICATE);
    }

    return highestPriorityPrice;
  }
}
