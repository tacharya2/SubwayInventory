package com.subwayInventory.subway.service;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository);
    }
    @Test
    void getProductById_IdExists_ReturnsProductDto() throws ProductNotFoundException {
        // Mock productEntity
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName("Dummy");
        productEntity.setPrice("16.25");
        productEntity.setCategory("Refrigerator");
        productEntity.setLocation("Aisle M");

        // Mock productRepository
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));

        // Call the getProductById method
        String traceId = "12345"; // Replace with your desired traceId value
        ProductDto productDto = productService.getProductById(1L, traceId);

        // Assertions
        assertNotNull(productDto);
        assertEquals("Dummy", productDto.getName());
        assertEquals("16.25", productDto.getPrice());
        assertEquals("Refrigerator", productDto.getCategory());
        assertEquals("Aisle M", productDto.getLocation());
    }

    @Test
    void createProduct_NonExistingId_ThrowsNotFoundException(){
        String traceId = "12345";
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, ()->productService.getProductById(1L, traceId));
    }
    @Test
    void createProduct_ValidProduct_ReturnCreatedProductDto(){
        //trying to insert this into the db
        ProductDto productDto = new ProductDto();
        productDto.setName("Dummy");
        productDto.setPrice("16.25");
        productDto.setCategory("Refrigerator");
        productDto.setLocation("Aisle M");

        //Expect this inserted as an entity
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName("Dummy");
        productEntity.setPrice("16.25");
        productEntity.setCategory("Refrigerator");
        productEntity.setLocation("Aisle M");

        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        //we create through ProductService. so, we pass ProductDto via it.

        String traceId = "12345";
        ProductDto createdProductDto = productService.createProduct(productDto, traceId);

        //Compare and see
        assertNotNull(createdProductDto);

        assertEquals("Dummy", createdProductDto.getName());
        assertEquals("16.25", createdProductDto.getPrice());
        assertEquals("Refrigerator", createdProductDto.getCategory());
        assertEquals("Aisle M", createdProductDto.getLocation());
    }

    @Test
    void createProduct_InvalidProduct_ThrowsIllegalArgumentException(){
        ProductDto productDto = new ProductDto();
        productDto.setName(null); //invalid product
        productDto.setPrice("16.25");
        productDto.setCategory("Refrigerator");
        productDto.setLocation("Aisle M");

        String traceId = "12345";
        assertThrows(IllegalArgumentException.class, ()->productService.createProduct(productDto, traceId));

        verify(productRepository, never()).save(any(ProductEntity.class));
    }

    @Test
    void updateProduct_ExistingIdAndValidProduct_ReturnsUpdatedProduct(){

        //to update to
        ProductDto productDto = new ProductDto();
        productDto.setName("Dummy");
        productDto.setPrice("16.25");
        productDto.setCategory("Refrigerator");
        productDto.setLocation("Aisle M");

        //To be updated to
        ProductEntity existingProduct = new ProductEntity();
        existingProduct.setId(1L);
        existingProduct.setName("Dummy");
        existingProduct.setPrice("16.25");
        existingProduct.setCategory("Refrigerator");
        existingProduct.setLocation("Aisle M");


        //Expected after an update
        ProductEntity updatedProduct = new ProductEntity();
        updatedProduct.setId(1L);
        updatedProduct.setName("Dummy");
        updatedProduct.setPrice("16.25");
        updatedProduct.setCategory("Refrigerator");
        updatedProduct.setLocation("Aisle M");

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(ProductEntity.class))).thenReturn(updatedProduct);

        assertNotNull(updatedProduct);

        assertEquals("Dummy", updatedProduct.getName());
        assertEquals("16.25", updatedProduct.getPrice());
        assertEquals("Refrigerator", updatedProduct.getCategory());
        assertEquals("Aisle M", updatedProduct.getLocation());
    }

    @Test
    //unhappy path
    void updateProduct_NonExistingId_ReturnNull(){
        String traceId = "12345";
        ProductDto productDto = new ProductDto();
        productDto.setName("Dummy");
        productDto.setPrice("16.25");
        productDto.setCategory("Refrigerator");
        productDto.setLocation("Aisle M");

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ProductDto updatedProductDto = productService.updateProduct(1L, productDto, traceId);

        assertNull(updatedProductDto);
    }
    @Test
    void deleteProduct_ExistingId_ReturnsTrue() {
        String traceId = "12345";
        when(productRepository.findById(1L)).thenReturn(Optional.of(new ProductEntity()));

        boolean deleted = productService.deleteProduct(1L, traceId);

        assertTrue(deleted);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteProduct_NonExistingId_ReturnsFalse(){
        String traceId = "12345";
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        boolean deleted = productService.deleteProduct(1L, traceId);

        assertFalse(deleted);

        verify(productRepository, never()).deleteById(1L);
    }
}