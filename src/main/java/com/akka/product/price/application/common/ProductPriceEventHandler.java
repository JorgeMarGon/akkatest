package com.akka.product.price.application.common;

import com.akka.product.price.application.get_product_price.GetProductPriceByBrandInDateQuery;
import com.akka.product.price.domain.models.ProductPrice;

public interface ProductPriceEventHandler {

  ProductPrice handle(GetProductPriceByBrandInDateQuery query);
}
