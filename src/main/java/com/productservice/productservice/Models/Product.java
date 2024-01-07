package com.productservice.productservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    //private int id;
    private String title;
    private String Description;
    private int price;
    private String imageurl;
    @ManyToOne
    private Category category;

}
