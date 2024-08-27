package org.obs.service;

import org.obs.dto.CategoryCountDto;
import org.obs.dto.ProductCreateDto;
import org.obs.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto getProductById(Long id);
    List<ProductResponseDto> getAllProducts(String category, String status);
    ProductResponseDto createProduct(ProductCreateDto productCreateDto);
    void deleteProductById(Long id);
    void deleteAllProducts();
    long getProductsCount();

    List<CategoryCountDto> getCategoryCounts();
}
