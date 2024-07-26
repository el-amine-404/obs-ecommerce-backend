package org.obs.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.obs.dto.ShoppingCartResponseDto;
import org.obs.service.Impl.AgentServiceImpl;

import java.net.URI;

@Path("/api/v1/agent")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class AgentController {

    private final AgentServiceImpl agentService;

    @POST
    @Path("/{id}/shopping-cart")
    public Response addShoppingCart(@PathParam("id") Long id) {
        ShoppingCartResponseDto shoppingCartResponseDto = agentService.addShoppingCartToAgent(id);
        return Response.created(URI.create("/agent/" + id + "/shopping-cart/" + shoppingCartResponseDto.getId())).build();
    }


}
