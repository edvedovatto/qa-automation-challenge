package tests;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

import clients.UsersClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.Config;

public class CreateUserTest {

    private final UsersClient usersClient = new UsersClient();

    @Test
    void shouldCreateUserSuccessfully() {

        RestAssured.baseURI = Config.BASE_URL;

        Response response = usersClient.createUser(validPayload());

        assertSuccessfulCreateUserResponse(response);
    }

    @Test
    void shouldFailWhenUsingInvalidMethod() {

        RestAssured.baseURI = Config.BASE_URL;

        Response response = usersClient.postOnUserWithId();

        assertMethodNotAllowedResponse(response);
    }

    private Map<String, Object> validPayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "Eduardo Vedovatto");
        payload.put("job", "QA Engineer");
        return payload;
    }

    private void assertSuccessfulCreateUserResponse(Response response) {
        assertThat(response.getStatusCode(), is(201));
        assertThat(response.getHeader("Content-Type"), containsString("application/json"));
        assertThat(response.jsonPath().getString("name"), is("Eduardo Vedovatto"));
        assertThat(response.jsonPath().getString("job"), is("QA Engineer"));
    }

    private void assertMethodNotAllowedResponse(Response response) {
        assertThat(response.getStatusCode(), is(anyOf(is(400), is(404), is(405))));
    }
}