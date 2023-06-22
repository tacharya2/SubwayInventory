package com.subwayInventory.subway.service;

import com.subwayInventory.subway.exception.ProductNotFoundException;
import com.subwayInventory.subway.mapper.ProductMapper;
import com.subwayInventory.subway.model.ProductDto;
import com.subwayInventory.subway.repository.ProductRepository;
import com.subwayInventory.subway.repository.model.ProductEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductDto createProduct(ProductDto productDto, String traceId){
        if(productDto.getName() == null || productDto.getName().isEmpty()){
            throw new IllegalArgumentException("Product cannot be empty");
        }else{
            ProductEntity productEntity = new ProductEntity();
//            productEntity.setId(productDto.getId());
            productEntity.setName(productDto.getName());
            productEntity.setPrice(productDto.getPrice());
            productEntity.setCategory(productDto.getCategory());
            productEntity.setLocation(productDto.getLocation());
            productEntity.setEventId(productDto.getEventId());

            try {
                ProductEntity savedProductEntity = productRepository.save(productEntity);
                logger.info("product saved successfully:  {}, with a trace id {}", productDto.getName(), traceId);
                return ProductMapper.toDto(savedProductEntity);
            }catch (DataIntegrityViolationException e){
                logger.info("product {} could not be saved! Trace id: {}", productDto.getName() , traceId);
                throw new RuntimeException("Failed to create product: " + e.getLocalizedMessage() + ". Trace id: " + traceId);
            }
        }
    }

    public ProductDto getProductById(Long id, String traceId ){
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        ProductEntity productEntity = optionalProductEntity.orElseThrow(()-> new ProductNotFoundException("Product Not found with: " + id + " Trace id" + traceId));
        logger.info("product retrieved for id {} . Trace id: {}", id, traceId);
        return ProductMapper.toDto(productEntity);
    }

    public ProductDto updateProduct(Long id, ProductDto productDto, String traceId){
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        if(optionalProductEntity.isPresent()){
            ProductEntity productEntity = optionalProductEntity.get();
            productEntity.setName(productDto.getName());
            productEntity.setPrice(productDto.getPrice());
            productEntity.setCategory(productDto.getCategory());
            productEntity.setLocation(productDto.getLocation());
            //Wanted to keep same eventId for lifelong event tracking
//            productEntity.setEventId(productDto.getEventId());

            ProductEntity updatedProductEntity = productRepository.save(productEntity);
            logger.info("product updated for id {}. Trace id: {}", id, traceId);
            return ProductMapper.toDto(updatedProductEntity);
        }else{
            logger.info("product with id {} does not exists. Trace Id {}", id, traceId);
            return null;
        }
    }

    public boolean deleteProduct(Long id, String traceId){
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);

        if(optionalProductEntity.isPresent()){
            productRepository.deleteById(id);
            logger.info("product removed for id {}. Trace Id: {}", id, traceId);
            return true;
        }else{
            logger.info("product with id {} does not exists. Trace Id: {}", id, traceId);
            return false;
        }
    }
}