package tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

import clients.UsersClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.Config;

public class DeleteUserTest {

    private final UsersClient usersClient = new UsersClient();

    @Test
    void shouldDeleteUserSuccessfully() {

        RestAssured.baseURI = Config.BASE_URL;

        Response response = usersClient.deleteUser(2);

        assertDeleteUserResponse(response);
    }

    private void assertDeleteUserResponse(Response response) {
        assertThat(response.getStatusCode(), is(anyOf(is(200), is(204))));
    }
}