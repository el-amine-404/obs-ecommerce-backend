package org.obs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company", schema = "obs_ecommerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @Column(name = "phone_number", nullable = false, length = 50)
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private List<Addresse> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private List<Agent> agents = new ArrayList<>();

}

