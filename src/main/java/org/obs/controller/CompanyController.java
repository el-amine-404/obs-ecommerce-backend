package org.obs.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.obs.dto.*;
import org.obs.service.Impl.CompanyServiceImpl;

import java.net.URI;

@Path("/api/v1/company")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class CompanyController {

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
        CompanyDto createdCompany = companyService.createCompany(companyCreateDto);
        return Response.created(URI.create("/company/" + createdCompany.getId())).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid CompanyUpdateDto companyUpdateDto){
        return Response.ok(companyService.updateCompany(id, companyUpdateDto)).build();
    }

    @POST
    @Path("/{id}/agent")
    public Response addAgent(@PathParam("id") Long id, @Valid AgentCreateDto agentCreateDto){
        return Response.ok(companyService.addAgentToCompany(id, agentCreateDto)).build();
    }

    @POST
    @Path("/{id}/address")
    public Response addAddress(@PathParam("id") Long id, @Valid AdresseDto adresseDto){
        return Response.ok(companyService.addAddressToCompany(id, adresseDto)).build();
    }
}
