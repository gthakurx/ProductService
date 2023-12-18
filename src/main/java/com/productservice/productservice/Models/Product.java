package com.productservice.productservice.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    //private int id;
    private String title;
    private String Description;
    private int price;
    private String imageurl;
    private Category category;
    private double rating;

}
