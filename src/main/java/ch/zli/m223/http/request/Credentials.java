package ch.zli.m223.http.request;

import java.util.AbstractMap.SimpleEntry;

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

    public SimpleEntry<String, String> getSelectionCondition() {
        if (username != null) {
            return new SimpleEntry<String, String>("username", username);
        }
        return new SimpleEntry<String,String>("email", email.toString());
    }

    public CharSequence getEmail() {
        return email;
    }

    public void setEmail(CharSequence email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Schema(hidden = true)
    @AssertTrue(message = "Either a username or email need to be provided")
    private boolean hasEitherUsernameOrEmail() {
        return email != null || username != null;
    }
}
