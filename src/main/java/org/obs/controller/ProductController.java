package org.obs.controller;

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
import java.util.List;

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
    public Response getAll(@QueryParam("category") String category, @QueryParam("status") String status){
        String baseUri = uriInfo.getBaseUri().toString();
        String imageFolder = "images/products/";
        List<ProductResponseDto> productResponseDtoList = productService.getAllProducts(category, status);

        productResponseDtoList.forEach(e -> e.setImage(baseUri + imageFolder + e.getImage()));

        return Response.ok(productResponseDtoList).build();
    }

    @GET
    @Path("/{id:[0-9]+}")
    public Response getById(Long id){
        String baseUri = uriInfo.getBaseUri().toString();
        String imageFolder = "images/products/";
        ProductResponseDto product = productService.getProductById(id);
        product.setImage(baseUri + imageFolder + product.getImage());
        return Response.ok(product).build();
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

    @GET
    @Path("/category-count")
    public Response getCategoryCounts(){
        return Response.ok(productService.getCategoryCounts()).build();
    }
}
