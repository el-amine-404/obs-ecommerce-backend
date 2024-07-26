package org.obs.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.obs.model.Agent;
import org.obs.model.Gender;
import org.obs.model.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgentResponseDto {

    @NotNull(message = "Id is required")
    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Username is required")
    private String username;

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

    @Builder.Default
    private List<ShoppingCartDto> shoppingCarts = new ArrayList<>();

    public static Agent toEntity(AgentResponseDto dto){
        return Agent.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .age(dto.getAge())
                .gender(dto.getGender())
                .createdOn(dto.getCreatedOn())
                .role(dto.getRole())
                .shoppingCarts(dto.getShoppingCarts().stream().map(ShoppingCartDto::toEntity).toList())
                .build();
    }

    public static AgentResponseDto ofEntity(Agent entity){
        return AgentResponseDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .age(entity.getAge())
                .gender(entity.getGender())
                .createdOn(entity.getCreatedOn())
                .role(entity.getRole())
                .shoppingCarts(entity.getShoppingCarts().stream().map(ShoppingCartDto::ofEntity).toList())
                .build();
    }
}
