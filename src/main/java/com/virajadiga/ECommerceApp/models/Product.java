package com.virajadiga.ECommerceApp.models;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    private double price;

    private double weight;

    private String description;

    private String imageName;
}
