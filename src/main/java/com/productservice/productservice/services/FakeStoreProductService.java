package com.productservice.productservice.services;

import org.springframework.stereotype.Service;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    @Override
    public String getProductById(Long Id) {
        //Implement the FakeStore API here .
        return "Hello Gaurav Kumar you ID is "+Id;
    }
}
