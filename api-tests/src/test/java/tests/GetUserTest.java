package tests;

import clients.UsersClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utils.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetUserTest {

    private final UsersClient usersClient = new UsersClient();

    @Test
    void shouldReturnUserWhenIdExists() {

        RestAssured.baseURI = Config.BASE_URL;

        Response response = usersClient.getUserById(1);

        assertSuccessfulUserResponse(response);
    }

    @Test
    void shouldReturn404WhenUserDoesNotExist() {

        RestAssured.baseURI = Config.BASE_URL;

        Response response = usersClient.getUserById(9999);

        assertThat(response.getStatusCode(), is(404));
    }

    private void assertSuccessfulUserResponse(Response response) {
        assertStatusOk(response);
        assertJsonContentType(response);
        assertValidUserPayload(response);
    }

    private void assertStatusOk(Response response) {
        assertThat(response.getStatusCode(), is(200));
    }

    private void assertJsonContentType(Response response) {
        assertThat(response.getHeader("Content-Type"), containsString("application/json"));
    }

    private void assertValidUserPayload(Response response) {
        assertThat(response.jsonPath().getInt("id"), is(1));
        assertThat(response.jsonPath().getString("name"), notNullValue());
        assertThat(response.jsonPath().getString("email"), notNullValue());
    }
}