package org.obs.model;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adresse", schema = "obs_ecommerce")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addresse_id")
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
