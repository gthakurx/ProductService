package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.dtos.GenericProductDto;

public interface ProductService {
    GenericProductDto getProductById(Long Id);
}
