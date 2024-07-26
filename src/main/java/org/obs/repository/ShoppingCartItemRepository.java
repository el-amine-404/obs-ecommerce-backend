package org.obs.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.obs.model.ShoppingCartItem;

import java.util.Optional;

@ApplicationScoped
public class ShoppingCartItemRepository implements PanacheRepository<ShoppingCartItem> {

    public Optional<ShoppingCartItem> findByShoppingCartIdAndProductId(Long shoppingCartId, Long productId) {
        return find("shoppingCart.id = ?1 and product.id = ?2", shoppingCartId, productId).firstResultOptional();
    }

}
