package ch.zli.m223.model;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ch.zli.m223.enums.ApplicationRole;

import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id;

    @Email
    @NotBlank
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    // FIXME: Doesn't work yet
    @Column(nullable = false)
    @ColumnDefault(value="user")
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<ApplicationRole> roles;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    @Fetch(FetchMode.JOIN)
    private Set<Entry> entries;

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<ApplicationRole> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<ApplicationRole> roles) {
        this.roles = roles;
    }

    public Set<Entry> getEntries() {
        return this.entries;
    }

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }

}
