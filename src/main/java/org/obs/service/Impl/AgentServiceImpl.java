package org.obs.service.Impl;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;
import org.obs.dto.AgentResponseDto;
import org.obs.dto.ShoppingCartResponseDto;
import org.obs.model.Agent;
import org.obs.model.ShoppingCart;
import org.obs.repository.AgentRepository;
import org.obs.service.AgentService;
import org.obs.service.ShoppingCartService;

@ApplicationScoped
public class AgentServiceImpl implements AgentService {

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;

    @ConfigProperty(name = "jwt.expiration.time")
    long jwtExpirationTime;

    private final AgentRepository agentRepository;
    private final ShoppingCartService shoppingCartService;

    public AgentServiceImpl(AgentRepository agentRepository, ShoppingCartService shoppingCartService) {
        this.agentRepository = agentRepository;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public AgentResponseDto getAgentById(long agentId) {
        Agent agent = agentRepository.findByIdOptional(agentId).orElseThrow(() -> new RuntimeException("Agent not found"));
        return AgentResponseDto.ofEntity(agent);
    }

    @Transactional
    @Override
    public ShoppingCartResponseDto addShoppingCartToAgent(long agentId) {
        Agent agent = agentRepository.findByIdOptional(agentId).orElseThrow(() -> new RuntimeException("Agent not found"));

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setAgent(agent);

        ShoppingCartResponseDto shoppingCartResponseDto = shoppingCartService.createShoppingCart(shoppingCart);

        agent.getShoppingCarts().add(shoppingCart);
        return shoppingCartResponseDto;
    }

    public boolean checkUserCredentials(String username, String password) {
        final Agent agent = this.findByUsername(username);
        return BcryptUtil.matches(password, agent.getPassword());
    }

    @Override
    public Agent findByUsername(String username) {
        return agentRepository.findByUsername(username).orElseThrow(() -> new RuntimeException(String.format("Agent '%s' not found", username)));
    }

    @Override
    public String generateJwtToken(final Agent agent) {

        return Jwt.issuer(issuer)
                .subject(agent.getUsername())
                .scope(agent.getRole().toString())
                .expiresIn(jwtExpirationTime)
                .claim(Claims.email_verified.name(), agent.getEmail())
                .claim(Claims.family_name, agent.getLastName())
                .claim("agentId", agent.getId())
                .sign();
    }


}
