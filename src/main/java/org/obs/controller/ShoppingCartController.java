package org.obs.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.jboss.logging.annotations.Pos;
import org.obs.dto.AddressResponseDto;
import org.obs.dto.ShoppingCartItemResponseDto;
import org.obs.service.Impl.ShoppingCartServiceImpl;

import java.net.URI;

@Path("/api/v1/shopping-cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartServiceImpl shoppingCartService;

    @POST
    @Path("/{id:[0-9]*}/add-item")
    public Response createItem(@PathParam("id") Long id,
                                                  @QueryParam("quantity") int quantity,
                                                  @QueryParam("productId") long productId){
        ShoppingCartItemResponseDto shoppingCartItemResponseDto = shoppingCartService.addItemToShoppingCart(id, productId, quantity);
        return Response.created(URI.create("/shopping-cart-item/" + shoppingCartItemResponseDto.getId())).build();
    }


}
