package org.obs.dto;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.obs.model.Address;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressCreateDto {

    @NotNull(message = "Street number is required")
    private int streetNumber;

    @NotBlank(message = "Street name is required")
    private String streetName;

    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "Zip code is required")
    private int zipCode;

    @Enumerated(EnumType.STRING)
    private CountryCode country;

    public static AddressCreateDto ofEntity(Address entity){
        return AddressCreateDto.builder()
                .streetNumber(entity.getStreetNumber())
                .streetName(entity.getStreetName())
                .city(entity.getCity())
                .zipCode(entity.getZipCode())
                .country(entity.getCountry())
                .build();
    }

    public static Address toEntity(AddressCreateDto dto){
        return Address.builder()
                .streetNumber(dto.getStreetNumber())
                .streetName(dto.getStreetName())
                .city(dto.getCity())
                .zipCode(dto.getZipCode())
                .country(dto.getCountry())
                .build();
    }

}
