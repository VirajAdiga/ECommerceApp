package com.virajadiga.ECommerceApp.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private Long categoryId;

    private double price;

    private double weight;

    private String description;

    private String imageName;
}
