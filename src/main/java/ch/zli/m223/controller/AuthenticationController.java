package ch.zli.m223.controller;

import javax.validation.constraints.Null;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.http.response.AsyncResponseModel;

@Path("/auth")
public class AuthenticationController {

    @POST
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Authenticate user", description = "Returns a session if user is valid")
    public AsyncResponseModel<Null> token() {
        // TODO: Implement some logic
        return AsyncResponseModel.<Null>createResponse(null, false);
    }
}