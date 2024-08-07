package org.obs.service;

import org.obs.dto.AgentResponseDto;
import org.obs.dto.ShoppingCartResponseDto;
import org.obs.model.Agent;

import java.util.Optional;

public interface AgentService {
    AgentResponseDto getAgentById(long agentId);
    ShoppingCartResponseDto addShoppingCartToAgent(long agentId);
    boolean checkUserCredentials(String username, String password);
    Agent findByUsername(String username);
    String generateJwtToken(Agent agent);
}
