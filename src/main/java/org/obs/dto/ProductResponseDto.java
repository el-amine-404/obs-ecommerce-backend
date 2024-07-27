package org.obs.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.obs.model.Product;
import org.obs.model.ProductCategory;
import org.obs.model.ProductStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    @NotNull(message = "Id is required")
    private long id;

    @NotBlank(message = "SKU is required")
    private String sku;
    
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Image is required")
    private String image;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private double price;

    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;

    @NotBlank(message = "Seller is required")
    private String seller;

    @NotNull(message = "Quantity stock is required")
    @Positive(message = "Quantity stock must be positive")
    private int quantityStock;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    public static ProductResponseDto ofEntity(Product entity){
        return ProductResponseDto.builder()
                .id(entity.getId())
                .sku(entity.getSku())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .image(entity.getImage())
                .price(entity.getPrice())
                .manufacturer(entity.getManufacturer())
                .seller(entity.getSeller())
                .quantityStock(entity.getQuantityStock())
                .category(entity.getCategory())
                .status(entity.getStatus())
                .build();
    }

    public static Product toEntity(ProductResponseDto dto){
        return Product.builder()
                .id(dto.getId())
                .sku(dto.getSku())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .image(dto.getImage())
                .price(dto.getPrice())
                .manufacturer(dto.getManufacturer())
                .seller(dto.getSeller())
                .quantityStock(dto.getQuantityStock())
                .category(dto.getCategory())
                .status(dto.getStatus())
                .build();
    }

}
