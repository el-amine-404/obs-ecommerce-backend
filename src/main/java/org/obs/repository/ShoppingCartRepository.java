package org.obs.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.obs.model.ShoppingCart;

import java.util.List;

@ApplicationScoped
public class ShoppingCartRepository implements PanacheRepository<ShoppingCart> {
    public List<ShoppingCart> findByAgentId(Long agentId) {
        return list("agent.id", agentId);
    }
}
