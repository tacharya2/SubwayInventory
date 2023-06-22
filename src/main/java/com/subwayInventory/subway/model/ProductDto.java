package com.subwayInventory.subway.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ProductDto {

    // No id is needed in the Dto layer as it is created and managed at the Entity/data layer
  /*  @Schema(description = "Unique Identifier for the product", example = "1", required = true)
    private Long id;
  */
    @Schema(description = "Name of the product", example = "Book", required = true)
    private String name;

    @Schema(description = "price of the product", example = "15.99", required = true)
    private String price;

    @Schema(description = "category of the product", example = "Dry")
    private String category;

    @Schema(description = "location of the product", example = "Aisle B")
    private String location;

    @NotNull
    @Schema(description = "Unique id for each transaction", example = "event_id", required = true)
    private UUID eventId;
}
