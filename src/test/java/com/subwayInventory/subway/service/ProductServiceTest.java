package com.subwayInventory.subway.service;

import com.subwayInventory.subway.Controller.ProductController;
import com.subwayInventory.subway.exception.ProductNotFoundException;
import com.subwayInventory.subway.model.ProductDto;
import com.subwayInventory.subway.repository.ProductRepository;
import com.subwayInventory.subway.repository.model.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductController productController;
    @Test
    void createProductTest() {

    }

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository);
    }
    @Test
    void getProductById_IdExists_ReturnsProductDto() throws ProductNotFoundException {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName("Dummy");
        productEntity.setPrice("16.25");
        productEntity.setCategory("Refrigerator");
        productEntity.setLocation("Aisle M");

        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));

        ProductDto productDto = productService.getProductById(1L);

        assertNotNull(productDto);
        assertEquals("Dummy", productDto.getName());
        assertEquals("16.25", productDto.getPrice());
        assertEquals("Refrigerator", productDto.getCategory());
        assertEquals("Aisle M", productDto.getLocation());
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}