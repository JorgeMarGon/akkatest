package com.akka.product.price.infrastructure.adapters.get_product_price;

import com.akka.common.domain.exceptions.CustomException;
import com.akka.product.price.application.get_product_price.ProductPriceRepository;
import com.akka.product.price.domain.errors.ProductPriceErrorCode;
import com.akka.product.price.domain.models.ProductPrice;
import com.akka.product.price.infrastructure.mappers.ProductPriceMapper;
import com.akka.product.price.infrastructure.persistence.dao.ProductPriceRepositoryJpa;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductPriceRepositoryAdapter implements ProductPriceRepository {

  private final ProductPriceRepositoryJpa productPriceRepositoryJpa;
  private final ProductPriceMapper productPriceMapper;

  @Override
  public List<ProductPrice> findAllByIdAndBrandId(ProductPrice productPrice) {
    return Optional.ofNullable(
            productPriceRepositoryJpa.findByProductIdAndBrand_Id(
                productPrice.getId(), productPrice.getBrand().getId()))
        .map(
            list -> list.stream().map(productPriceMapper::jpaToDomain).collect(Collectors.toList()))
        .filter(list -> !list.isEmpty())
        .orElseThrow(() -> new CustomException(ProductPriceErrorCode.PRICE_NOT_FOUND));
  }
}
