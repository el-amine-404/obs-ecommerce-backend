package org.obs.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.obs.dto.ShoppingCartResponseDto;
import org.obs.service.AgentService;

import java.net.URI;

@Path("/api/v1/agent")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AgentController {

    private final AgentService agentService;

    @Inject
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @POST
    @Path("/{id:[0-9]+}/shopping-cart")
    public Response addShoppingCart(@PathParam("id") Long id) {
        ShoppingCartResponseDto shoppingCartResponseDto = agentService.addShoppingCartToAgent(id);
        return Response.created(URI.create("/agent/" + id + "/shopping-cart/" + shoppingCartResponseDto.getId())).build();
    }


}
