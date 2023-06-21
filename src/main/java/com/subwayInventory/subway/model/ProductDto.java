package com.subwayInventory.subway.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductDto {

    @Schema(description = "Unique Identifier for the product", example = "1", required = true)
    private Long id;

    @Schema(description = "Name of the product", example = "Book", required = true)
    private String name;

    @Schema(description = "price of the product", example = "15.99", required = true)
    private String price;

    @Schema(description = "category of the product", example = "Dry", required = false)
    private String category;

    @Schema(description = "location of the product", example = "Aisle B", required = false)
    private String location;
}
