package ch.zli.m223.utils;

import java.time.Duration;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.eclipse.microprofile.jwt.Claims;

import ch.zli.m223.model.Customer;
import io.smallrye.jwt.build.Jwt;

public class TokenGenerationUtil {

    public static String generateTokenFromCustomer(Customer customer) {
        HashSet<String> groups = new HashSet<>(customer.getRoles().stream().map(role -> role.toString()).collect(Collectors.toList()));
        return Jwt
                .issuer("https://zli.example.com/")
                .upn(customer.getEmail())
                .groups(groups)
                .claim(Claims.family_name, customer.getLastName())
                .claim(Claims.given_name, customer.getFirstName())
                .claim(Claims.sub, customer.getUsername())
                .claim(Claims.email, customer.getEmail())
                .expiresIn(Duration.ofHours(12))
                .sign();
    }
}
