package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDto;

public interface ProductService {
    FakeStoreProductDto getProductById(Long Id);
}
