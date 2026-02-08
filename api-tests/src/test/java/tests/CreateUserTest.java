package tests;

import clients.UsersClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utils.Config;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateUserTest {

    private final UsersClient usersClient = new UsersClient();

    @Test
    void shouldCreateUserSuccessfully() {

        RestAssured.baseURI = Config.BASE_URL;

        Map<String, String> payload = new HashMap<>();
        payload.put("name", "Eduardo Vedovatto");
        payload.put("job", "QA Engineer");

        Response response = usersClient.createUser(payload);

        assertSuccessfulCreateUserResponse(response);
    }

    private void assertSuccessfulCreateUserResponse(Response response) {
        assertStatusCreated(response);
        assertJsonContentType(response);
        assertCreatedUserPayload(response);
    }

    private void assertStatusCreated(Response response) {
        assertThat(response.getStatusCode(), is(201));
    }

    private void assertJsonContentType(Response response) {
        assertThat(response.getHeader("Content-Type"), containsString("application/json"));
    }

    private void assertCreatedUserPayload(Response response) {
        assertThat(response.jsonPath().getString("name"), equalTo("Eduardo Vedovatto"));
        assertThat(response.jsonPath().getString("job"), equalTo("QA Engineer"));
        assertThat(response.jsonPath().getString("id"), notNullValue());
    }
}