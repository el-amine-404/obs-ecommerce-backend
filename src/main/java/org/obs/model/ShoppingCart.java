package org.obs.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_cart", schema = "obs_ecommerce")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

    @Id        
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    private Long id;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "confirmation_date")
    private LocalDateTime confirmationDate;

    @Column(name = "total_price", nullable = false)
    private double totalPrice = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private ShoppingCartStatus status = ShoppingCartStatus.NEW;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @Builder.Default
    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();
    
}
