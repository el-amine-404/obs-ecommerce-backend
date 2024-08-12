package org.obs.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.obs.dto.LoginDto;
import org.obs.model.Agent;
import org.obs.service.AgentService;

import java.util.Map;

@Path("/auth")
@AllArgsConstructor
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {

    private final AgentService agentService;

    @POST
    @Path("/login")
    @PermitAll
    public Response login(@Valid final LoginDto loginDto) {
        if (agentService.checkUserCredentials(loginDto.getUsername(), loginDto.getPassword())) {
            Agent agent = agentService.findByUsername(loginDto.getUsername());
            String token = agentService.generateJwtToken(agent);
            return Response
                    .ok()
                    .entity(Map.of("access-token", token))
                    .build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
        }
    }


}
