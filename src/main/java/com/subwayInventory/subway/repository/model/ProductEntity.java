package com.subwayInventory.subway.repository.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "category")
    private String category;

    @Column(name = "location")
    private String location;
}
