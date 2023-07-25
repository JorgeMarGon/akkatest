package com.akka.product.price.application.get_product_price;

import com.akka.product.price.domain.models.ProductPrice;
import java.util.List;

public interface ProductPriceRepository {
  List<ProductPrice> findAllByIdAndBrandId(ProductPrice productPrice);
}
