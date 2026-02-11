package tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

import clients.UsersClient;
import io.restassured.response.Response;

public class GetUserTest {

    private final UsersClient usersClient = new UsersClient();

    @Test
    void shouldReturnUserWhenIdExists() {
        Response response = usersClient.getUserById(2);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getHeader("Content-Type"), containsString("application/json"));
        assertThat(response.jsonPath().getInt("id"), is(2));
    }

    @Test
    void shouldReturnNotFoundWhenUserDoesNotExist() {
        Response response = usersClient.getUserById(9999);

        assertThat(response.getStatusCode(), is(404));
    }
}