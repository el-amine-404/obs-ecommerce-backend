package org.obs.service;

import org.obs.dto.ShoppingCartItemResponseDto;
import org.obs.dto.ShoppingCartResponseDto;
import org.obs.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCartResponseDto getShoppingCartById(long id);
    ShoppingCartResponseDto createShoppingCart(ShoppingCart shoppingCart);
    void deleteShoppingCart(long id);
    ShoppingCartItemResponseDto addItemToShoppingCart(long shoppingCartId, long productId, int quantity);
}
