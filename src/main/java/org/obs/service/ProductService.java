package org.obs.service;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import org.obs.repository.ProductRepository;

@ApplicationScoped
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;





}
