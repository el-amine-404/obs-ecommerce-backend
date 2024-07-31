package org.obs.service;

import org.obs.dto.ProductCreateDto;
import org.obs.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    public ProductResponseDto getProductById(Long id);
    public List<ProductResponseDto> getAllProducts(String category, String status);
    public ProductResponseDto createProduct(ProductCreateDto productCreateDto);
    public void deleteProductById(Long id);
    public void deleteAllProducts();
    public long getProductsCount();

}
