package org.obs.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.obs.model.Company;

@ApplicationScoped
public class CompanyRepository implements PanacheRepository<Company> {
}
