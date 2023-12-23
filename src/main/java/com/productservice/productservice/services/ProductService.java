package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long Id);
    List<GenericProductDto> getAllProducts();
    GenericProductDto createProduct(GenericProductDto genericProductDto);

    GenericProductDto deleteProductById(Long Id);

    GenericProductDto updateProductById(Long Id);
}
