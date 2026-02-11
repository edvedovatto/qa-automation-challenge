package tests;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

import clients.UsersClient;
import io.restassured.response.Response;
import models.UserRequest;

public class CreateUserTest {

    private final UsersClient usersClient = new UsersClient();

    @Test
    void shouldCreateUserSuccessfully() {

        String dynamicName = "user-" + UUID.randomUUID().toString().substring(0, 6);
        String dynamicJob = "QA-" + UUID.randomUUID().toString().substring(0, 6);

        UserRequest payload = new UserRequest(dynamicName, dynamicJob);

        Response response = usersClient.createUser(payload);

        assertThat(response.getStatusCode(), is(201));
        assertThat(response.getHeader("Content-Type"), containsString("application/json"));

        assertThat(response.jsonPath().getString("name"), is(dynamicName));
        assertThat(response.jsonPath().getString("job"), is(dynamicJob));
        assertThat(response.jsonPath().getInt("id"), greaterThan(0));
    }

    @Test
    void shouldFailWhenUsingInvalidMethod() {

        Response response = usersClient.postOnUserWithId();

        assertThat(response.getStatusCode(), is(404));
        assertThat(response.getBody().asString(), is("{}"));
    }
}