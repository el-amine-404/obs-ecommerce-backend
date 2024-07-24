package org.obs.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.obs.dto.AgentDto;
import org.obs.model.Agent;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class AgentMapper {

    private final ShoppingCartMapper shoppingCartMapper;

    public AgentMapper(ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @Transactional
     public Agent toEntity(AgentDto agentDto){
         if (agentDto == null) {
             return null;
         }
         Agent agent = new Agent();
         agent.setId(agentDto.getId());
         agent.setFirstName(agentDto.getFirstName());
         agent.setLastName(agentDto.getLastName());
         agent.setAge(agentDto.getAge());
         agent.setEmail(agentDto.getEmail());
         agent.setGender(agentDto.getGender());
         agent.setRole(agentDto.getRole());
         agent.setCreatedOn(agentDto.getCreatedOn());
         agent.setPassword(agentDto.getPassword());
         agent.setUsername(agentDto.getUsername());
         agent.setShoppingCarts(shoppingCartMapper.toEntityList(agentDto.getShoppingCarts()));


         return agent;
     }

     @Transactional
     public AgentDto toDto(Agent agent){
         if (agent == null) {
             return null;
         }
         AgentDto agentDto = new AgentDto();
         agentDto.setId(agent.getId());
         agentDto.setFirstName(agent.getFirstName());
         agentDto.setLastName(agent.getLastName());
         agentDto.setAge(agent.getAge());
         agentDto.setEmail(agent.getEmail());
         agentDto.setGender(agent.getGender());
         agentDto.setRole(agent.getRole());
         agentDto.setCreatedOn(agent.getCreatedOn());
         agentDto.setPassword(agent.getPassword());
         agentDto.setUsername(agent.getUsername());
         agentDto.setShoppingCarts(shoppingCartMapper.toDtoList(agent.getShoppingCarts()));

         return agentDto;
     }

     public List<Agent> toEntityList(List<AgentDto> agentDtoList) {
         if (agentDtoList == null) {
             return Collections.emptyList();
         }

         return agentDtoList.stream()
                 .map(this::toEntity)
                 .toList();
     }

     public List<AgentDto> toDtoList(List<Agent> agentEntityList) {
         if (agentEntityList == null) {
             return Collections.emptyList();
         }

         return agentEntityList.stream()
                 .map(this::toDto)
                 .toList();
     }

}
