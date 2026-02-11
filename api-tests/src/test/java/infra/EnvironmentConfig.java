package infra;

import java.util.Map;

public final class EnvironmentConfig {

    private static final Map<String, String> ENV_URLS = Map.of(
        "dev", "https://jsonplaceholder.typicode.com",
        "qa", "https://jsonplaceholder.typicode.com",
        "prod", "https://jsonplaceholder.typicode.com"
    );

    private EnvironmentConfig() {}

    public static String getBaseUrl() {
        String env = System.getProperty("env", "dev").toLowerCase();
        return ENV_URLS.getOrDefault(env, ENV_URLS.get("dev"));
    }
}