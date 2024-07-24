package org.obs.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.obs.model.Company;
import org.obs.model.Gender;
import org.obs.model.Role;
import org.obs.model.ShoppingCart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgentDto {

    @NotNull(message = "Id is required")
    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "FirstName is required")
    private String firstName;

    @NotBlank(message = "LastName is required")
    private String lastName;

    @NotNull(message = "Age is required")
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    private LocalDateTime createdOn;

    @Enumerated(EnumType.STRING)
    private Role role;

    private List<ShoppingCartDto> shoppingCarts = new ArrayList<>();
}
