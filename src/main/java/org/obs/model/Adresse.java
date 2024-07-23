package org.obs.model;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "adresse", schema = "obs_ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "street_number", nullable = false, length = 50)
    private int streetNumber;

    @Column(name = "street_name", nullable = false, length = 50)
    private String streetName;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "zip_code", nullable = false, length = 50)
    private int zipCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "country", nullable = false, length = 50)
    private CountryCode country;
}
