package org.obs.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.obs.model.ShoppingCart;
import org.obs.model.ShoppingCartItem;
import org.obs.model.ShoppingCartStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartResponseDto {

    @NotNull(message = "Id is required")
    private Long id;

    private LocalDateTime creationDate;

    private LocalDateTime confirmationDate;

    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    @Builder.Default
    private List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

    public static ShoppingCart toEntity(ShoppingCartResponseDto dto){
        return ShoppingCart.builder()
                .id(dto.getId())
                .creationDate(dto.getCreationDate())
                .confirmationDate(dto.getConfirmationDate())
                .totalPrice(dto.getTotalPrice())
                .status(dto.getStatus())
                .shoppingCartItems(dto.getShoppingCartItems())
                .build();
    }

    public static ShoppingCartResponseDto ofEntity(ShoppingCart entity){
        return ShoppingCartResponseDto.builder()
                .id(entity.getId())
                .creationDate(entity.getCreationDate())
                .confirmationDate(entity.getConfirmationDate())
                .totalPrice(entity.getTotalPrice())
                .status(entity.getStatus())
                .shoppingCartItems(entity.getShoppingCartItems())
                .build();
    }

}
