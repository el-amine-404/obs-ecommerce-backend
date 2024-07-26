package org.obs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.obs.model.Company;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyCreateDto {

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Phone number is required")
    private String phoneNumber;

    @Email(message = "Please provide a valid email address")
    private String email;

    public static Company toEntity(CompanyCreateDto dto){
        return Company.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .build();
    }

    public static CompanyCreateDto ofEntity(Company entity){
        return CompanyCreateDto.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .build();
    }

}
