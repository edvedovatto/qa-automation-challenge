package clients;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

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

    public Response updateUser(int id, Object payload) {
        return given()
                .accept("application/json")
                .contentType("application/json")
                .body(payload)
            .when()
                .put("/users/" + id);
    }
}