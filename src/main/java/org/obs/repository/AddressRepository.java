package org.obs.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.obs.model.Address;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {
}
