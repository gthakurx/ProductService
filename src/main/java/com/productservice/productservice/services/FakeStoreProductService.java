package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private String getsingleproducturl ="https://fakestoreapi.com/products/{id}";
    private String getGenericProducturl ="https://fakestoreapi.com/products";
    FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    private static GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        return genericProductDto;
    }
    @Override
    public GenericProductDto getProductById(Long Id) {
        //Implement the FakeStore API here .
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =restTemplate.getForEntity(getsingleproducturl, FakeStoreProductDto.class,Id);

        return convertToGenericProductDto(responseEntity.getBody());
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
//        ResponseEntity<List<FakeStoreProductDto>> responseEntity =
//                restTemplate.getForEntity(getAllProducturl,List<FakeStoreProductDto>.class);
        ResponseEntity<FakeStoreProductDto[]> responseEntity=
                restTemplate.getForEntity(getGenericProducturl, FakeStoreProductDto[].class);
        List<GenericProductDto> listgenericProductDtos=new ArrayList<>();
        List<FakeStoreProductDto> fakeStoreProductDtos=List.of(responseEntity.getBody());
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            listgenericProductDtos.add(convertToGenericProductDto(fakeStoreProductDto));
        }
        return listgenericProductDtos;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity=
                restTemplate.postForEntity(getGenericProducturl,genericProductDto , FakeStoreProductDto.class);

        return convertToGenericProductDto(responseEntity.getBody());
    }
}
