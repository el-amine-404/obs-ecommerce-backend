package org.obs.service;

import org.obs.dto.AgentResponseDto;
import org.obs.dto.ShoppingCartResponseDto;

public interface AgentService {
    AgentResponseDto getAgentById(long agentId);
    ShoppingCartResponseDto addShoppingCartToAgent(long agentId);
}
