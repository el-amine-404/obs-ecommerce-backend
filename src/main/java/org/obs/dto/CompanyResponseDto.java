package org.obs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.obs.model.Company;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponseDto {

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

    @Builder.Default
    private List<AddressResponseDto> addresses = new ArrayList<>();

    @Builder.Default
    private List<AgentResponseDto> agents = new ArrayList<>();

    public static CompanyResponseDto ofEntity(Company entity){
        return CompanyResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .addresses(entity.getAddresses().stream().map(AddressResponseDto::ofEntity).toList())
                .agents(entity.getAgents().stream().map(AgentResponseDto::ofEntity).toList())
                .build();
    }

    public static Company toEntity(CompanyResponseDto dto){
        return Company.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .addresses(dto.getAddresses().stream().map(AddressResponseDto::toEntity).toList())
                .agents(dto.getAgents().stream().map(AgentResponseDto::toEntity).toList())
                .build();
    }

}
