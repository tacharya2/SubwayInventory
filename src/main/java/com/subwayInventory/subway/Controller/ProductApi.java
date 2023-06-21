package com.subwayInventory.subway.Controller;

import com.subwayInventory.subway.model.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/products")
public interface ProductApi {
    @Operation(summary = "Create a product record", tags = "Product Api")
    @PostMapping()
    ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto);


    @Operation(summary = "retrieve a product record", tags = "Product Api")
    @GetMapping("/{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable Long id);

    @Operation(summary = "Update a product record", tags = "Product Api")
    @PutMapping("/{id}")
    ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto);

    @Operation(description = "Remove a product record", summary = "Remove a product record", tags = "Product Api")
    @DeleteMapping("/{id}")
    ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id);

    @Operation(summary = "Ping", tags = "Product Api")
    @GetMapping(value = "/ping")
    ResponseEntity<String> ping();
}
