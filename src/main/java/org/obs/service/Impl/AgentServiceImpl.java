package org.obs.service.Impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.obs.dto.AgentResponseDto;
import org.obs.dto.ShoppingCartResponseDto;
import org.obs.model.Agent;
import org.obs.model.ShoppingCart;
import org.obs.repository.AgentRepository;
import org.obs.service.AgentService;
import org.obs.service.ShoppingCartService;

@ApplicationScoped
@AllArgsConstructor
public class AgentServiceImpl implements AgentService {


    private final AgentRepository agentRepository;
    private final ShoppingCartService shoppingCartService;

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
}
