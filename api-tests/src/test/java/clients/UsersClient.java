package clients;

import java.util.Map;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class UsersClient {

    public Response getUserById(int id) {
        return given()
                .accept("application/json")
            .when()
                .get("/users/" + id);
    }

    public Response createUser(Map<String, Object> payload) {
        return given()
                .accept("application/json")
                .contentType("application/json")
                .body(payload)
            .when()
                .post("/users");
    }

    public Response createUserWithoutBody() {
        return given()
                .accept("application/json")
                .contentType("application/json")
            .when()
                .post("/users");
    }

    public Response updateUser(int id, Map<String, Object> payload) {
        return given()
                .accept("application/json")
                .contentType("application/json")
                .body(payload)
            .when()
                .put("/users/" + id);
    }

    public Response deleteUser(int id) {
        return given()
                .accept("application/json")
            .when()
                .delete("/users/" + id);
    }

    public Response postOnUserWithId() {
        return given()
                .accept("application/json")
                .contentType("application/json")
            .when()
                .post("/users/1");
    }
}