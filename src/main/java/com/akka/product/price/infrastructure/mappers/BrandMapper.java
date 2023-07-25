package com.akka.product.price.infrastructure.mappers;

import com.akka.product.price.domain.models.Brand;
import com.akka.product.price.infrastructure.persistence.jpa.BrandJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandMapper {

  Brand jpaToDomain(BrandJpa brandJpa);
}
