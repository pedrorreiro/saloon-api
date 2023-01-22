package com.app.saloon.InputDto;

import lombok.Data;

@Data
public class FunctionaryInputDto {

    private int id;
    private int userId;
    private int saloonId;
    private String name;
    private int[] servicesId;
}
