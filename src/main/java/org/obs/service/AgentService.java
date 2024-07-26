package org.obs.service;

import org.obs.dto.ShoppingCartResponseDto;

public interface AgentService {
    public ShoppingCartResponseDto addShoppingCartToAgent(long agentId);
}
