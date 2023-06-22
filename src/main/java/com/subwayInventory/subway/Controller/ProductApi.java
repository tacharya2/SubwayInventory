package com.subwayInventory.subway.Controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.subwayInventory.subway.model.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/products")
public interface ProductApi {
    @Operation(summary = "Create a product record", tags = "Product Api")
    @PostMapping()
    ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, @RequestHeader(value = "trace-id", required = false) final String traceId);


    @Operation(summary = "retrieve a product record", tags = "Product Api")
    @GetMapping("/{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable Long id, @RequestHeader(value = "trace-id", required = false) final String traceId);

    @Operation(summary = "Update a product record", tags = "Product Api")
    @PutMapping("/{id}")
    ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto, @RequestHeader(value = "trace-id", required = false) final String traceId);

    @Operation(description = "Remove a product record", summary = "Remove a product record", tags = "Product Api")
    @DeleteMapping("/{id}")
    ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id, @RequestHeader(value = "trace-id", required = false) final String traceId);

    @Operation(summary = "Ping", tags = "Product Api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response from API",
                    content = {@Content (schema = @Schema(implementation = String.class))})
            })
    @GetMapping(value = "/ping")
    ResponseEntity<String> ping();
}
