package org.obs.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.obs.model.ShoppingCart;

@ApplicationScoped
public class ShoppingCartRepository implements PanacheRepository<ShoppingCart> {
}
