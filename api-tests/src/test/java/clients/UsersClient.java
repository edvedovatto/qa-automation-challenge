package clients;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsersClient {

    public Response getUserById(int id) {
        return given()
                .accept("application/json")
            .when()
                .get("/users/" + id);
    }

    public Response createUser(Object payload) {
        return given()
                .accept("application/json")
                .contentType("application/json")
                .body(payload)
            .when()
                .post("/users");
    }
}