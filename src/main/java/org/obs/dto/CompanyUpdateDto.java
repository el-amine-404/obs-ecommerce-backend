package org.obs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.obs.model.Address;
import org.obs.model.Agent;
import org.obs.model.Company;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUpdateDto {

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Phone number is required")
    private String phoneNumber;

    @Email(message = "Please provide a valid email address")
    private String email;

    private List<Address> addresses = new ArrayList<>();

    private List<Agent> agents = new ArrayList<>();

    public static CompanyUpdateDto ofEntity(Company entity){
        return CompanyUpdateDto.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .addresses(entity.getAddresses())
                .agents(entity.getAgents())
                .build();
    }

    public static Company toEntity(CompanyUpdateDto dto){
        return Company.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .addresses(dto.getAddresses())
                .agents(dto.getAgents())
                .build();
    }


}
