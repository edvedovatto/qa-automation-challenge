package tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

import clients.UsersClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.Config;

public class GetUserTest {

    private final UsersClient usersClient = new UsersClient();

    @Test
    void shouldReturnUserWhenIdExists() {

        RestAssured.baseURI = Config.BASE_URL;

        Response response = usersClient.getUserById(2);

        assertSuccessfulGetUserResponse(response);
    }

    @Test
    void shouldReturnNotFoundWhenUserDoesNotExist() {

        RestAssured.baseURI = Config.BASE_URL;

        Response response = usersClient.getUserById(9999);

        assertUserNotFoundResponse(response);
    }

    private void assertSuccessfulGetUserResponse(Response response) {
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getHeader("Content-Type"), containsString("application/json"));
    }

    private void assertUserNotFoundResponse(Response response) {
        assertThat(response.getStatusCode(), is(404));
    }
}
