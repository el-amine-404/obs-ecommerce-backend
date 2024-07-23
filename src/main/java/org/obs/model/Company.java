package org.obs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "company", schema = "obs_ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private String description;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private List<Addresse> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private List<Agent> agents = new ArrayList<>();

}

