package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@QuarkusTest
public class EntryResourceTest {

    @Test
    public void testIndexEndpoint() {
        given()
                .when().get("/entries")
                .then()
                .statusCode(200)
                .body(is("[]"));
    }

    public void testCreateEndpoint() {
        Map<String, LocalDateTime> body = new HashMap<>() {
            {
                put("checkIn", LocalDateTime.now());
                put("checkOut", LocalDateTime.now());
            }
        };
        given()
                .when()
                .post("/entries", body)
                .then()
                .statusCode(200)
                .body(is("{}")); // TODO: FIXME
    }

}