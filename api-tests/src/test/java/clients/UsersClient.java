package clients;

import infra.BaseApiClient;
import io.restassured.response.Response;
import models.UserRequest;

public class UsersClient extends BaseApiClient {

    public Response getUserById(int id) {
        return baseRequest()
                .when()
                .get("/users/{id}", id);
    }

    public Response createUser(UserRequest payload) {
        return baseRequest()
                .body(payload)
                .when()
                .post("/users");
    }

    public Response updateUser(int id, UserRequest payload) {
        return baseRequest()
                .body(payload)
                .when()
                .put("/users/{id}", id);
    }

    public Response deleteUser(int id) {
        return baseRequest()
                .when()
                .delete("/users/{id}", id);
    }

    public Response postOnUserWithId() {
        return baseRequest()
                .when()
                .post("/users/{id}", 1);
    }
}