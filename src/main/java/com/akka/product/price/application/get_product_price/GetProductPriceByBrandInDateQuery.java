package com.akka.product.price.application.get_product_price;

import com.akka.product.price.domain.models.ProductPrice;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GetProductPriceByBrandInDateQuery {
  private final ProductPrice productPrice;
}
