package org.obs.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import org.obs.model.Product;
import org.obs.model.ProductCategory;
import org.obs.model.ProductStatus;

import java.util.List;

@ApplicationScoped
@AllArgsConstructor
public class ProductRepository implements PanacheRepository<Product> {

    public List<Product> findByCategory(ProductCategory category) {
        return list("category", category);
    }

    public List<Product> findByStatus(ProductStatus productStatus) {
        return list("status", productStatus);
    }

    public List<Product> findByCategoryAndStatus(ProductCategory productCategory, ProductStatus productStatus) {
        return list("category = ?1 and status = ?2", productCategory, productStatus);
    }
}
