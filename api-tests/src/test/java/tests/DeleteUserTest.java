package tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

import clients.UsersClient;
import io.restassured.response.Response;

public class DeleteUserTest {

    private final UsersClient usersClient = new UsersClient();

    @Test
    void shouldDeleteUserSuccessfully() {
        Response response = usersClient.deleteUser(2);

        assertThat(response.getStatusCode(), is(200));
    }
}