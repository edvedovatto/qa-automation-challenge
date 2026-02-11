package tests;

import java.util.UUID;

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

        String dynamicName = "user-" + UUID.randomUUID().toString().substring(0, 6);
        String dynamicJob = "SeniorQA-" + UUID.randomUUID().toString().substring(0, 6);

        UserRequest payload = new UserRequest(dynamicName, dynamicJob);

        Response response = usersClient.updateUser(2, payload);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.jsonPath().getString("name"), is(dynamicName));
        assertThat(response.jsonPath().getString("job"), is(dynamicJob));
    }
}