package org.obs.service.Impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.obs.dto.ShoppingCartItemResponseDto;
import org.obs.dto.ShoppingCartResponseDto;
import org.obs.exception.ProductQuantityException;
import org.obs.exception.ProductStatusException;
import org.obs.model.Product;
import org.obs.model.ProductStatus;
import org.obs.model.ShoppingCart;
import org.obs.model.ShoppingCartItem;
import org.obs.repository.ProductRepository;
import org.obs.repository.ShoppingCartItemRepository;
import org.obs.repository.ShoppingCartRepository;
import org.obs.service.ShoppingCartService;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;


    @Override
    public ShoppingCartResponseDto getCartDetails(long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByIdOptional(id).orElseThrow(() -> new RuntimeException("ShoppingCart not found"));
        return ShoppingCartResponseDto.ofEntity(shoppingCart);
    }

    @Transactional
    @Override
    public ShoppingCartResponseDto createShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartRepository.persist(shoppingCart);
        return ShoppingCartResponseDto.ofEntity(shoppingCart);
    }

    @Transactional
    @Override
    public void deleteShoppingCart(long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByIdOptional(id).orElseThrow(() -> new RuntimeException("ShoppingCart not found"));
//        ShoppingCart shoppingCart = shoppingCartRepository.findByIdOptional(id).orElseThrow(() -> new Resourcenot);
        shoppingCartRepository.delete(shoppingCart);
    }

    @Transactional
    @Override
    public ShoppingCartItemResponseDto addItemToShoppingCart(long shoppingCartId, long productId, int quantity) {

        ShoppingCart shoppingCart = shoppingCartRepository.findByIdOptional(shoppingCartId).orElseThrow(() -> new RuntimeException("ShoppingCart not found"));
         
        Product product = productRepository.findByIdOptional(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStatus() == ProductStatus.UNAVAILABLE) {
            throw new ProductStatusException("Product is not available");
        } else if (product.getStatus() == ProductStatus.ONDEMAND) {
            throw new ProductStatusException("Product is on demand");
        }

        if (quantity > product.getQuantityStock()) {
            throw new ProductQuantityException("Not enough stock");
        }


        // Check if the product already exists in the shopping cart
        Optional<ShoppingCartItem> existingItemOptional = shoppingCartItemRepository.findByShoppingCartIdAndProductId(shoppingCartId, productId);
        ShoppingCartItem shoppingCartItem = null;
        if (existingItemOptional.isPresent()) {
            // If the product already exists in the shopping cart, increase the quantity
            ShoppingCartItem existingItem = existingItemOptional.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            existingItem.setPrice(existingItem.getPrice() + product.getPrice() * quantity);
        } else {
            shoppingCartItem = new ShoppingCartItem();
            shoppingCartItem.setProduct(product);
            shoppingCartItem.setShoppingCart(shoppingCart);
            shoppingCartItem.setQuantity(quantity);
            shoppingCartItem.setPrice(product.getPrice() * quantity);

            shoppingCartItemRepository.persist(shoppingCartItem);
            shoppingCart.getShoppingCartItems().add(shoppingCartItem);
        }

        product.setQuantityStock(product.getQuantityStock() - quantity);

        shoppingCart.setTotalPrice(shoppingCart.getShoppingCartItems().stream().mapToDouble(ShoppingCartItem::getPrice).sum());

        return ShoppingCartItemResponseDto.ofEntity(existingItemOptional.orElse(shoppingCartItem));
    }

    public List<ShoppingCartResponseDto> getCartsByAgentId(Long agentId) {
        return shoppingCartRepository.findByAgentId(agentId).stream().map(ShoppingCartResponseDto::ofEntity).toList();
    }
}
