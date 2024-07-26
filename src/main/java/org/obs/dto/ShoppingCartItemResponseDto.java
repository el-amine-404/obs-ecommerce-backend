package org.obs.dto;

import lombok.*;
import org.obs.model.Product;
import org.obs.model.ShoppingCartItem;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItemResponseDto {

    private Long id;

    private int quantity;

    private double price;

    private Product product;

    public static ShoppingCartItemResponseDto ofEntity(ShoppingCartItem entity){
        return ShoppingCartItemResponseDto.builder()
                .id(entity.getId())
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .product(entity.getProduct())
                .build();
    }

    public static ShoppingCartItem toEntity(ShoppingCartItemResponseDto dto){
        return ShoppingCartItem.builder()
                .id(dto.getId())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .product(dto.getProduct())
                .build();
    }


}
