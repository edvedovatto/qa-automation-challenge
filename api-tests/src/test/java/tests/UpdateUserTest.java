package tests;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

import clients.UsersClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.Config;

public class UpdateUserTest {

    private final UsersClient usersClient = new UsersClient();

    @Test
    void shouldUpdateUserSuccessfully() {

        RestAssured.baseURI = Config.BASE_URL;

        Map<String, String> payload = new HashMap<>();
        payload.put("name", "Eduardo Vedovatto");
        payload.put("job", "Senior QA Engineer");

        Response response = usersClient.updateUser(1, payload);

        assertSuccessfulUpdateUserResponse(response);
    }

    private void assertSuccessfulUpdateUserResponse(Response response) {
        assertStatusOk(response);
        assertJsonContentType(response);
        assertUpdatedUserPayload(response);
    }

    private void assertStatusOk(Response response) {
        assertThat(response.getStatusCode(), is(200));
    }

    private void assertJsonContentType(Response response) {
        assertThat(response.getHeader("Content-Type"), containsString("application/json"));
    }

    private void assertUpdatedUserPayload(Response response) {
        assertThat(response.jsonPath().getString("name"), equalTo("Eduardo Vedovatto"));
        assertThat(response.jsonPath().getString("job"), equalTo("Senior QA Engineer"));
    }
}