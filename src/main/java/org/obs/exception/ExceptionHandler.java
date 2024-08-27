package org.obs.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {


    @Override
    public Response toResponse(Exception e) {
        if(e instanceof AgentNotFoundException) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ApiError(LocalDateTime.now(), Response.Status.NOT_FOUND.getStatusCode(), Response.Status.NOT_FOUND, e.getMessage()))
                    .build();
        } else if(e instanceof ProductStatusException) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(new ApiError(LocalDateTime.now(), Response.Status.BAD_REQUEST.getStatusCode(), Response.Status.BAD_REQUEST, e.getMessage()))
                    .build();
        } else if(e instanceof ProductQuantityException) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(new ApiError(LocalDateTime.now(), Response.Status.BAD_REQUEST.getStatusCode(), Response.Status.BAD_REQUEST, e.getMessage()))
                    .build();
        } else if (e instanceof InvalidCredentialsException) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(new ApiError(LocalDateTime.now(), Response.Status.UNAUTHORIZED.getStatusCode(), Response.Status.UNAUTHORIZED, e.getMessage()))
                    .build();
        }
        return Response
                .serverError()
                .build();
}
}
