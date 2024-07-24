package org.obs.dto;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdresseDto {
    
    private Long id;

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
}
