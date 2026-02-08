package tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import utils.Config;

public class GetUserTest {

    @Test
    void shouldReturnUserWhenIdExists() {

        RestAssured.baseURI = Config.BASE_URL;

        given()
            .accept("application/json")
        .when()
            .get(Config.USERS_ENDPOINT + "/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("name", notNullValue())
            .body("email", notNullValue());
    }

    @Test
    void shouldReturn404WhenUserDoesNotExist() {

        RestAssured.baseURI = Config.BASE_URL;

        given()
            .accept("application/json")
        .when()
            .get(Config.USERS_ENDPOINT + "/9999")
        .then()
            .statusCode(404);
    }
}