package org.obs.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.AllArgsConstructor;
import org.obs.dto.*;
import org.obs.service.Impl.CompanyServiceImpl;

import java.net.URI;

@Path("/api/v1/company")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class CompanyController {

    @Context
    private UriInfo uriInfo;

    private final CompanyServiceImpl companyService;

    @GET()
    public Response getAll(){
        return Response.ok(companyService.getAllCompanies()).build();
    }

    @GET()
    @Path("/{id}")
    public Response getById(Long id){
        return Response.ok(companyService.getCompanyById(id)).build();
    }

    @POST
    public Response create(@Valid CompanyCreateDto companyCreateDto){
        CompanyResponseDto createdCompany = companyService.createCompany(companyCreateDto);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(createdCompany.getId())).build();
        return Response.created(uri).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid CompanyUpdateDto companyUpdateDto){
        return Response.ok(companyService.updateCompany(id, companyUpdateDto)).build();
    }

    @POST
    @Path("/{id}/agent")
    public Response addAgent(@PathParam("id") Long id, @Valid AgentCreateDto agentCreateDto){
        AgentResponseDto createdAgent = companyService.addAgentToCompany(id, agentCreateDto);
        return Response.created(URI.create("/company/" + id + "/agent/" + createdAgent.getId())).build();
    }

    @POST
    @Path("/{id}/address")
    public Response addAddress(@PathParam("id") Long id, @Valid AddressCreateDto addressCreateDto){
        AddressResponseDto createdAddress = companyService.addAddressToCompany(id, addressCreateDto);
        return Response.created(URI.create("/company/" + id + "/address/" + createdAddress.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        companyService.deleteCompanyById(id);
        return Response.noContent().build();
    }

    @DELETE
    public Response deleteAll(){
        companyService.deleteAllCompanies();
        return Response.noContent().build();
    }
}
