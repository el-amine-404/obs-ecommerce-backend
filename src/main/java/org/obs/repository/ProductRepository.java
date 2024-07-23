package org.obs.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import org.obs.model.Product;

@ApplicationScoped
@AllArgsConstructor
public class ProductRepository implements PanacheRepository<Product> {
    
}
