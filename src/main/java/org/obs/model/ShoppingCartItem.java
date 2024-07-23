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
    @GeneratedValue(generator = "UUID")
    @Column(name = "shopping_cart_item_id", nullable = false)
    private UUID id;


    @Column(name = "quantity", nullable = false, length = 50)
    private int quantity;

    @Column(name = "price", nullable = false, length = 50)
    private double price;
    
}
