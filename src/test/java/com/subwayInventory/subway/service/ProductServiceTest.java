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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductController productController;

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
    void createProduct_NonExistingId_ThrowsNotFoundException(){
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, ()->productService.getProductById(1L));
    }
    @Test
    void createProduct_ValidProduct_ReturnCreatedProductDto(){
        //trying to insert this into the db
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
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

        ProductDto createdProductDto = productService.createProduct(productDto);

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
        productDto.setId(1L);
        productDto.setName(null); //invalid product
        productDto.setPrice("16.25");
        productDto.setCategory("Refrigerator");
        productDto.setLocation("Aisle M");

        assertThrows(IllegalArgumentException.class, ()->productService.createProduct(productDto));

        verify(productRepository, never()).save(any(ProductEntity.class));
    }

    @Test
    void updateProduct_ExistingIdAndValidProduct_ReturnsUpdatedProduct(){

        //to update to
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
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
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Dummy");
        productDto.setPrice("16.25");
        productDto.setCategory("Refrigerator");
        productDto.setLocation("Aisle M");

        when(productRepository.findById(1l)).thenReturn(Optional.empty());

        ProductDto updatedProductDto = productService.updateProduct(1L, productDto);

        assertNull(updatedProductDto);
    }
    @Test
    void deleteProduct_ExistingId_ReturnsTrue() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(new ProductEntity()));

        boolean deleted = productService.deleteProduct(1L);

        assertTrue(deleted);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteProduct_NonExistingId_ReturnsFalse(){
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        boolean deleted = productService.deleteProduct(1L);

        assertFalse(deleted);

        verify(productRepository, never()).deleteById(1L);
    }
}