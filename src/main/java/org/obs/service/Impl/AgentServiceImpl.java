package org.obs.service.Impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.obs.dto.ShoppingCartResponseDto;
import org.obs.model.Agent;
import org.obs.model.ShoppingCart;
import org.obs.repository.AgentRepository;
import org.obs.service.AgentService;

@ApplicationScoped
public class AgentServiceImpl implements AgentService {

    @Inject
    AgentRepository agentRepository;

    @Override
    public ShoppingCartResponseDto addShoppingCartToAgent(long agentId) {
        Agent agent = agentRepository.findByIdOptional(agentId).orElseThrow(() -> new RuntimeException("Agent not found"));

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setAgent(agent);

        agent.getShoppingCarts().add(shoppingCart);
        return ShoppingCartResponseDto.ofEntity(shoppingCart);
    }
}
