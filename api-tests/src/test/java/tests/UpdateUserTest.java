package tests;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
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

        Response response = usersClient.updateUser(2, updatePayload());

        assertSuccessfulUpdateUserResponse(response);
    }

    private Map<String, Object> updatePayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "Eduardo Vedovatto");
        payload.put("job", "Senior QA");
        return payload;
    }

    private void assertSuccessfulUpdateUserResponse(Response response) {
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.jsonPath().getString("job"), is("Senior QA"));
    }
}