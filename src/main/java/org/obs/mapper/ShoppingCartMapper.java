package org.obs.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.obs.dto.ShoppingCartDto;
import org.obs.model.ShoppingCart;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ShoppingCartMapper {

    public ShoppingCart toEntity(ShoppingCartDto shoppingCartDto){
        if (shoppingCartDto == null) {
            return null;
        }
        return ShoppingCart.builder()
                .id(shoppingCartDto.getId())
                .status(shoppingCartDto.getStatus())
                .totalPrice(shoppingCartDto.getTotalPrice())
                .confirmationDate(shoppingCartDto.getConfirmationDate())
                .creationDate(shoppingCartDto.getCreationDate())
                .build();
    }

    public ShoppingCartDto toDto(ShoppingCart shoppingCart){
        if (shoppingCart == null) {
            return null;
        }
        return ShoppingCartDto.builder()
                .id(shoppingCart.getId())
                .status(shoppingCart.getStatus())
                .totalPrice(shoppingCart.getTotalPrice())
                .confirmationDate(shoppingCart.getConfirmationDate())
                .creationDate(shoppingCart.getCreationDate())
                .build();
    }

    public List<ShoppingCart> toEntityList(List<ShoppingCartDto> shoppingCartDtoList) {
        if (shoppingCartDtoList == null) {
            return Collections.emptyList();
        }

        return shoppingCartDtoList.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<ShoppingCartDto> toDtoList(List<ShoppingCart> shoppingCartList) {
        if (shoppingCartList == null) {
            return Collections.emptyList();
        }

        return shoppingCartList.stream()
                .map(this::toDto)
                .toList();
    }

}
