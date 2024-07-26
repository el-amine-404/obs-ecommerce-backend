package org.obs.service;

import org.obs.dto.AgentResponseDto;
import org.obs.dto.ShoppingCartResponseDto;

public interface AgentService {
    public AgentResponseDto getAgentById(long agentId);
    public ShoppingCartResponseDto addShoppingCartToAgent(long agentId);
}
