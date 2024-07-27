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

    @Column(name = "sku", nullable = false, length = 50)
    private String sku; // Stock Keeping Unit (provided by the manufacturer)

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @Column(name = "image", nullable = false, length = 50)
    private String image;

    @Column(name = "price", nullable = false, length = 50)
    private double price;

    @Column(name = "manufacturer", nullable = false, length = 50)
    private String manufacturer;

    @Column(name = "seller", nullable = false, length = 50)
    private String seller;

    @Column(name = "quantity_stock", nullable = false, length = 50)
    private int quantityStock;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 50)
    private ProductCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private ProductStatus status;

//    @OneToOne(mappedBy = "product")
//    private ShoppingCartItem shoppingCartItem;

}
