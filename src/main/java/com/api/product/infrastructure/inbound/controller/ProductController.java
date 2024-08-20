package com.api.product.infrastructure.inbound.controller;

import com.api.product.domain.model.ProductDTO;
import com.api.product.domain.model.entity.ProductEntity;
import com.api.product.mapper.ProductMapper;
import com.api.product.domain.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductController(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @GetMapping
    private List<ProductDTO> getProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(productEntity -> productMapper.productEntityToProductDTO(productEntity))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    private ProductDTO getProductById(@PathVariable Long id) {
        Optional<ProductEntity> productOptional = productRepository
                .findById(id);
        if(productOptional.isPresent()){
            return productOptional.map(productEntity -> productMapper.productEntityToProductDTO(productEntity)).get();
        }
        return null;
    }


    @PostMapping
    private ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        ProductEntity productEntity = this.productRepository.save(
                productMapper.productDTOToProductEntity(productDTO)
        );
        return productMapper.productEntityToProductDTO(productEntity);
    }

}
