package org.obs.repository;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.obs.model.Agent;

import java.util.Optional;

@ApplicationScoped
public class AgentRepository implements PanacheRepository<Agent> {

    @Override
    public void persist(Agent agent) {
        agent.setPassword(BcryptUtil.bcryptHash(agent.getPassword()));
        PanacheRepository.super.persist(agent);
    }

    public Optional<Agent> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }
    
}
