package org.obs.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.obs.dto.ShoppingCartResponseDto;
import org.obs.model.ShoppingCart;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ShoppingCartMapper {

    public ShoppingCart toEntity(ShoppingCartResponseDto shoppingCartResponseDto){
        if (shoppingCartResponseDto == null) {
            return null;
        }
        return ShoppingCart.builder()
                .id(shoppingCartResponseDto.getId())
                .status(shoppingCartResponseDto.getStatus())
                .totalPrice(shoppingCartResponseDto.getTotalPrice())
                .confirmationDate(shoppingCartResponseDto.getConfirmationDate())
                .creationDate(shoppingCartResponseDto.getCreationDate())
                .build();
    }

    public ShoppingCartResponseDto toDto(ShoppingCart shoppingCart){
        if (shoppingCart == null) {
            return null;
        }
        return ShoppingCartResponseDto.builder()
                .id(shoppingCart.getId())
                .status(shoppingCart.getStatus())
                .totalPrice(shoppingCart.getTotalPrice())
                .confirmationDate(shoppingCart.getConfirmationDate())
                .creationDate(shoppingCart.getCreationDate())
                .build();
    }

    public List<ShoppingCart> toEntityList(List<ShoppingCartResponseDto> shoppingCartResponseDtoList) {
        if (shoppingCartResponseDtoList == null) {
            return Collections.emptyList();
        }

        return shoppingCartResponseDtoList.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<ShoppingCartResponseDto> toDtoList(List<ShoppingCart> shoppingCartList) {
        if (shoppingCartList == null) {
            return Collections.emptyList();
        }

        return shoppingCartList.stream()
                .map(this::toDto)
                .toList();
    }

}
