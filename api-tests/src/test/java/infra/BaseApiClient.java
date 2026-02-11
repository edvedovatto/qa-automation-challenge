package infra;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApiClient {

    protected RequestSpecification baseRequest() {
        return RestAssured
                .given()
                .baseUri(EnvironmentConfig.getBaseUrl())
                .accept("application/json")
                .contentType("application/json");
    }
}