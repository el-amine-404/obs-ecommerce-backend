package org.obs.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.obs.dto.ProductCreateDto;
import org.obs.dto.ProductResponseDto;
import org.obs.service.ProductService;

import java.net.URI;

@Path("/api/v1/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Context
    private UriInfo uriInfo;
    
    private final ProductService productService;

    @Inject
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GET
    @PermitAll
    public Response getAll(@QueryParam("category") String category, @QueryParam("status") String status){
        return Response.ok(productService.getAllProducts(category, status)).build();
    }

    @GET
    @Path("/{id:[0-9]+}")
    @PermitAll
    public Response getById(Long id){
        return Response.ok(productService.getProductById(id)).build();
    }

    @POST
    public Response create(@Valid ProductCreateDto productCreateDto){
        ProductResponseDto createdProduct = productService.createProduct(productCreateDto);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(createdProduct.getId())).build();
        return Response.created(uri).build();
    }

    @DELETE
    @Path("/{id:[0-9]+}")
    public Response delete(@PathParam("id") Long id){
        productService.deleteProductById(id);
        return Response.noContent().build();
    }

    @DELETE
    public Response deleteAll(){
        productService.deleteAllProducts();
        return Response.noContent().build();
    }
}
