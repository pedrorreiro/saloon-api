package com.app.store.Dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductDto {
    
    @JsonProperty("key")
    private long id;
    private String name;
    private String price;
    private String img;

    List<String> categories;
}
