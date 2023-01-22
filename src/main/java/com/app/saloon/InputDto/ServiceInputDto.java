package com.app.saloon.InputDto;

import lombok.Data;

@Data
public class ServiceInputDto {

    private String name;
    private double price;
    private int duration;
    private double comission;
    private int saloonId;
    private int[] functionariesId = new int[0];
}
