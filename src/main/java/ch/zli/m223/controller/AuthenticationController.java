package ch.zli.m223.controller;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import ch.zli.m223.http.request.Credentials;
import ch.zli.m223.http.response.AsyncResponseModel;
import ch.zli.m223.http.response.JwtResponseBody;
import ch.zli.m223.http.response.VerifyResponseBody;
import ch.zli.m223.service.AuthenticationService;

@Path("/auth")
@RequestScoped
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
@PermitAll
public class AuthenticationController {

    @Inject
    AuthenticationService authenticationService;

    @Inject
    @Claim(standard = Claims.family_name)
    String lastName;

    @Inject
    @Claim(standard = Claims.given_name)
    String firstName;

    @Inject
    @Claim(standard = Claims.sub)
    String sub;

    @Inject
    @Claim(standard = Claims.email)
    String email;

    @POST
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Authenticate user", description = "Returns a session if user is valid")
    public Response token(@Valid Credentials credentials) {
        String jwtToken = authenticationService.authenticate(credentials);
        if (jwtToken == null) {
            AsyncResponseModel<Null> responseBody = AsyncResponseModel.<Null>createResponse(null, false);
            return Response.status(401).entity(responseBody).build();
        }
        AsyncResponseModel<JwtResponseBody> responseBody = AsyncResponseModel
                .<JwtResponseBody>createResponse(new JwtResponseBody(jwtToken), true);
        return Response.ok(responseBody).build();
    }

    @GET
    @Path("/verify")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Verifies user", description = "Verifies user session and returns information")
    @RolesAllowed({ "ADMINISTRATOR", "USER" })
    public Response verify() {
        AsyncResponseModel<VerifyResponseBody> responseBody = AsyncResponseModel
                .<VerifyResponseBody>createResponse(new VerifyResponseBody(email, sub, lastName, firstName), true);
        return Response.ok(responseBody).build();
    }
}