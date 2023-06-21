package com.subwayInventory.subway.service;

import com.subwayInventory.subway.exception.ProductNotFoundException;
import com.subwayInventory.subway.mapper.ProductMapper;
import com.subwayInventory.subway.model.ProductDto;
import com.subwayInventory.subway.repository.ProductRepository;
import com.subwayInventory.subway.repository.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductDto createProduct(ProductDto productDto){
        if(productDto.getName() == null || productDto.getName().isEmpty()){
            throw new IllegalArgumentException("Product cannot be empty");
        }else{
            ProductEntity productEntity = new ProductEntity();
            productEntity.setId(productDto.getId());
            productEntity.setName(productDto.getName());
            productEntity.setPrice(productDto.getPrice());
            productEntity.setCategory(productDto.getCategory());
            productEntity.setLocation(productDto.getLocation());

            try {
                ProductEntity savedProductEntity = productRepository.save(productEntity);
                return ProductMapper.toDto(savedProductEntity);
            }catch (DataIntegrityViolationException e){
                throw new RuntimeException("Failed to create product: " + e.getLocalizedMessage());
            }
        }
    }

    public ProductDto getProductById(Long id){
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        ProductEntity productEntity = optionalProductEntity.orElseThrow(()-> new ProductNotFoundException("Product Not found with: "+ id));
        return ProductMapper.toDto(productEntity);
    }

    public ProductDto updateProduct(Long id, ProductDto productDto){
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        if(optionalProductEntity.isPresent()){
            ProductEntity productEntity = optionalProductEntity.get();
            productEntity.setName(productDto.getName());
            productEntity.setPrice(productDto.getPrice());
            productEntity.setCategory(productDto.getCategory());
            productEntity.setLocation(productDto.getLocation());

            ProductEntity updatedProductEntity = productRepository.save(productEntity);

            return ProductMapper.toDto(updatedProductEntity);
        }else{
            //Logger
            return null;
        }
    }

    public boolean deleteProduct(Long id){
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);

        if(optionalProductEntity.isPresent()){
            productRepository.deleteById(id);
            //log
            return true;
        }else{
            return false;
        }
    }
}