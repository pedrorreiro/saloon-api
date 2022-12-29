package com.app.store.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CategoryDto {
    
    @JsonProperty("key")
    private long id;
    private String name;
    private Boolean active;
}
