package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductUrl="https://fakestoreapi.com/products/1";
    FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }
    @Override
    public FakeStoreProductDto getProductById(Long Id) {
        //Implement the FakeStore API here .
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =restTemplate.getForEntity(getProductUrl, FakeStoreProductDto.class);

        return responseEntity.getBody();
    }
}
