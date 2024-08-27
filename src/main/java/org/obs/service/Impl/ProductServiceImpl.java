package org.obs.service.Impl;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.obs.dto.CategoryCountDto;
import org.obs.dto.ProductCreateDto;
import org.obs.dto.ProductResponseDto;
import org.obs.model.Product;
import org.obs.model.ProductCategory;
import org.obs.model.ProductStatus;
import org.obs.repository.ProductRepository;
import org.obs.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<ProductResponseDto> getAllProducts(String category, String status) {
        if (category == null && status == null) {
            return productRepository.listAll().stream().map(ProductResponseDto::ofEntity).toList();
        } else if (category == null) {
            if (!EnumUtils.isValidEnum(ProductStatus.class, status.toUpperCase())) {
                throw new RuntimeException("Invalid status");
            }
            ProductStatus productStatus = ProductStatus.valueOf(status.toUpperCase());
            List<Product> products = productRepository.findByStatus(productStatus);
            return products.stream().map(ProductResponseDto::ofEntity).toList();
        } else if (status == null) {
            if (!EnumUtils.isValidEnum(ProductCategory.class, category.toUpperCase())) {
                throw new RuntimeException("No category is found");
            }
            ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());
            List<Product> products = productRepository.findByCategory(productCategory);
            return products.stream().map(ProductResponseDto::ofEntity).toList();
        } else {
            if (!EnumUtils.isValidEnum(ProductCategory.class, category.toUpperCase())) {
                throw new RuntimeException("No category is found");
            }
            if (!EnumUtils.isValidEnum(ProductStatus.class, status.toUpperCase())) {
                throw new RuntimeException("Invalid status");
            }
            ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());
            ProductStatus productStatus = ProductStatus.valueOf(status.toUpperCase());
            List<Product> products = productRepository.findByCategoryAndStatus(productCategory, productStatus);
            return products.stream().map(ProductResponseDto::ofEntity).toList();
        }

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
    public long getProductsCount() {
        return productRepository.count();
    }

    @Override
    public List<CategoryCountDto> getCategoryCounts() {
        Map<ProductCategory, Long> categoryCounts = productRepository.getCategoryCounts();
        return categoryCounts.entrySet().stream()
                .map(entry -> new CategoryCountDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }


}
