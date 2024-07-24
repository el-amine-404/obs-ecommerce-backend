package org.obs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @NotNull(message = "Id is required")
    private long id;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Phone number is required")
    private String phoneNumber;

    @Email(message = "Please provide a valid email address")
    private String email;
    
    private List<AdresseDto> addresses = new ArrayList<>();

    private List<AgentDto> agents = new ArrayList<>();

}
