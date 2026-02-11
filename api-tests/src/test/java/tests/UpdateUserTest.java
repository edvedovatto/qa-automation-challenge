package tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

import clients.UsersClient;
import io.restassured.response.Response;
import models.UserRequest;

public class UpdateUserTest {

    private final UsersClient usersClient = new UsersClient();

    @Test
    void shouldUpdateUserSuccessfully() {
        UserRequest payload = new UserRequest("Eduardo Vedovatto", "Senior QA");
        Response response = usersClient.updateUser(2, payload);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.jsonPath().getString("job"), is(payload.getJob()));
    }
}