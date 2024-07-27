package org.obs.service.Impl;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.obs.dto.ProductCreateDto;
import org.obs.dto.ProductResponseDto;
import org.obs.model.Product;
import org.obs.repository.ProductRepository;
import org.obs.service.ProductService;

import java.util.List;

@ApplicationScoped
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findByIdOptional(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductResponseDto.ofEntity(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.listAll().stream().map(ProductResponseDto::ofEntity).toList();
    }

    @Transactional
    @Override
    public ProductResponseDto createProduct(ProductCreateDto productCreateDto) {
        Product product = ProductCreateDto.toEntity(productCreateDto);
        productRepository.persist(product);
        return ProductResponseDto.ofEntity(product);
    }

    @Transactional
    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findByIdOptional(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);

    }

    @Transactional
    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public long getProductsCount() {
        return productRepository.count();
    }
}
