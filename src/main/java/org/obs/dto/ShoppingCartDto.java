package org.obs.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.obs.model.Agent;
import org.obs.model.ShoppingCartItem;
import org.obs.model.ShoppingCartStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDto {

    @NotNull(message = "Id is required")
    private Long id;

    private LocalDateTime creationDate;

    private LocalDateTime confirmationDate;

    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    private List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

}
