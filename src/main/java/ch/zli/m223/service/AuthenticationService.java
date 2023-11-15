package ch.zli.m223.service;

import java.util.AbstractMap.SimpleEntry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import ch.zli.m223.http.request.Credentials;
import ch.zli.m223.model.Customer;
import ch.zli.m223.utils.TokenGenerationUtil;

@ApplicationScoped
public class AuthenticationService {

    @Inject
    EntityManager entityManager;
    
    @Transactional
    public String authenticate(Credentials credentials) {
        SimpleEntry<String, String> selectionContition = credentials.getSelectionCondition();
        String jql = String.format("FROM Customer WHERE %s = :value", selectionContition.getKey());
        TypedQuery<Customer> query = entityManager.createQuery(jql, Customer.class).setParameter("value", selectionContition.getValue());
        // Can throw exception
        Customer customer = query.getSingleResult();
        if (!customer.getPassword().equals(credentials.getPassword())) {
            return null;
        }
        return TokenGenerationUtil.generateTokenFromCustomer(customer);
    }
}
