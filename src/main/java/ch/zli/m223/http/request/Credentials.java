package ch.zli.m223.http.request;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class Credentials {

    @Email
    private CharSequence email;

    private String username;

    @NotBlank // Only works for Strings
    private String password;

    @Schema(hidden = true)
    @AssertTrue(message = "Either a username or email need to be provided")
    private boolean hasEitherUsernameOrEmail() {
        return email != null || username != null;
    }
}
