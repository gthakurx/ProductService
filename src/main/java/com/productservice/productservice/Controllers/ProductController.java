package com.productservice.productservice.Controllers;


import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.dtos.GenericProductDto;
import com.productservice.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public GenericProductDto getProductById(@PathVariable("id") Long id){
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
}
