package org.obs.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.obs.model.Agent;
import org.obs.model.Gender;
import org.obs.model.Role;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgentCreateDto {

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

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Agent toEntity(AgentCreateDto agentCreateDto){
        return Agent.builder()
                .email(agentCreateDto.getEmail())
                .username(agentCreateDto.getUsername())
                .password(agentCreateDto.getPassword())
                .firstName(agentCreateDto.getFirstName())
                .lastName(agentCreateDto.getLastName())
                .age(agentCreateDto.getAge())
                .gender(agentCreateDto.getGender())
                .role(agentCreateDto.getRole())
                .build();
    }

    public static AgentCreateDto ofEntity(Agent agent){
        return AgentCreateDto.builder()
                .email(agent.getEmail())
                .username(agent.getUsername())
                .password(agent.getPassword())
                .firstName(agent.getFirstName())
                .lastName(agent.getLastName())
                .age(agent.getAge())
                .gender(agent.getGender())
                .role(agent.getRole())
                .build();
    }

}
