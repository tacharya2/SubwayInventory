package com.subwayInventory.subway.Controller;

import com.subwayInventory.subway.model.ProductDto;
import com.subwayInventory.subway.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController implements ProductApi{

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<ProductDto> createProduct(ProductDto productDto) {
        ProductDto createdProductDto = productService.createProduct(productDto);
        return new ResponseEntity<>(createdProductDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductDto> getProductById(Long id) {
        ProductDto productDto = productService.getProductById(id);
        if(productDto != null){
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ProductDto> updateProduct(Long id, ProductDto productDto) {
        ProductDto updatedProductDto = productService.updateProduct(id, productDto);
        if(updatedProductDto != null){
            return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ProductDto> deleteProduct(Long id) {
        boolean   deletd = productService.deleteProduct(id);
        if(deletd){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}