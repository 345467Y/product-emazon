package com.api.product.mapper;

import com.api.product.domain.model.ProductDTO;
import com.api.product.domain.model.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity productDTOToProductEntity(ProductDTO product);

    ProductDTO productEntityToProductDTO(ProductEntity productEntity);

}
