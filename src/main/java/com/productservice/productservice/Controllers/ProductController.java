package com.productservice.productservice.Controllers;


import com.productservice.productservice.dtos.ExceptionDto;
import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import com.productservice.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Products")
public class ProductController {

    private ProductService productService;
 /*   ProductController(ProductService productService){ //this is not Correct since there is no Object
        //of productService Identified by the spring , On making the FakeStoreProductService annotate with @service it will create beans for
        //productservice as well
        this.productService=productService;
    }*/
    ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);
    }

    @DeleteMapping("/{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") Long Id){
        return productService.deleteProductById(Id);
    }

    @PostMapping("/{id}")
    public GenericProductDto updateProductById(Long Id){
        return productService.updateProductById(Id);
    }

//    @ExceptionHandler
//    private String handleProductNotFoundException(ProductNotFoundException productNotFoundException){
//        //return "Exception handled";
//    }
//    @ExceptionHandler 1 way of handling exception
//    private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
//        ExceptionDto exceptionDto=new ExceptionDto();
//        exceptionDto.setMessage(productNotFoundException.getMessage());
//        exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
//        ResponseEntity responseEntity=new ResponseEntity(exceptionDto,HttpStatus.NOT_FOUND);
//        return responseEntity;
//    }
}
