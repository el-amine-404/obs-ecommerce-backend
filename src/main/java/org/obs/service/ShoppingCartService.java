package org.obs.service;

import org.obs.dto.ShoppingCartItemResponseDto;
import org.obs.dto.ShoppingCartResponseDto;
import org.obs.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCartResponseDto getCartDetails(long id);
    ShoppingCartResponseDto createShoppingCart(ShoppingCart shoppingCart);
    void deleteShoppingCart(long id);
    ShoppingCartItemResponseDto addItemToShoppingCart(long shoppingCartId, long productId, int quantity);
    List<ShoppingCartResponseDto> getCartsByAgentId(Long agentId);
}
