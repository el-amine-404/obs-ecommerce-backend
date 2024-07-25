package org.obs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "shopping_cart_item", schema = "obs_ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_item_id")
    private Long id;
    
    @Column(name = "quantity", nullable = false, length = 50)
    private int quantity;

    @Column(name = "price", nullable = false, length = 50)
    private double price;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
}
