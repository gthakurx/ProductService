package com.productservice.productservice.Controllers;


import com.productservice.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    @GetMapping("/Products/{id}")
    public String getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }
}
