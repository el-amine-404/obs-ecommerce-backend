package org.obs.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product", schema = "obs_ecommerce")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "sku", nullable = false)
    private String sku; // Stock Keeping Unit (provided by the manufacturer)

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "seller", nullable = false)
    private String seller;

    @Column(name = "quantity_stock", nullable = false)
    private int quantityStock;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private ProductCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status;

//    @OneToOne(mappedBy = "product")
//    private ShoppingCartItem shoppingCartItem;

}
