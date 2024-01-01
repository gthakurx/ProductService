package com.productservice.productservice.thirdPartyClients.fakestoreclient;

import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String getsingleproducturl ="https://fakestoreapi.com/products/{id}";
    private String getGenericProducturl ="https://fakestoreapi.com/products";

    FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    public FakeStoreProductDto getProductById(Long Id) throws ProductNotFoundException {
        //Implement the FakeStore API here .
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =restTemplate.getForEntity(getsingleproducturl, FakeStoreProductDto.class,Id);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        if(fakeStoreProductDto==null){
            throw new ProductNotFoundException("Product Id "+Id+" Not found");
        }
        return fakeStoreProductDto;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
//        ResponseEntity<List<FakeStoreProductDto>> responseEntity =
//                restTemplate.getForEntity(getAllProducturl,List<FakeStoreProductDto>.class);
        ResponseEntity<FakeStoreProductDto[]> responseEntity=
                restTemplate.getForEntity(getGenericProducturl, FakeStoreProductDto[].class);
       return List.of(responseEntity.getBody());


    }


    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity=
                restTemplate.postForEntity(getGenericProducturl,genericProductDto , FakeStoreProductDto.class);

        return responseEntity.getBody();
    }


    public FakeStoreProductDto deleteProductById(Long Id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        //delete product in Fake store website is returning Object which is getting deleted
        //but resttemplate.deletemethod is returning void
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity=
                restTemplate.execute(getsingleproducturl, HttpMethod.DELETE, requestCallback, responseExtractor, Id);
        return responseEntity.getBody();
    }


    public FakeStoreProductDto updateProductById(Long Id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        //delete product in Fake store website is returning Object which is getting deleted
        //but resttemplate.deletemethod is returning void
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity=
                restTemplate.execute(getsingleproducturl, HttpMethod.POST, requestCallback, responseExtractor, Id);
        return responseEntity.getBody();
    }
}
