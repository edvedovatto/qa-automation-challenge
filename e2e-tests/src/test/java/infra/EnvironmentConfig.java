package infra;

import java.util.Map;

public final class EnvironmentConfig {

    private static final Map<String, String> ENV_URLS = Map.of(
            "dev", "https://www.saucedemo.com/",
            "qa", "https://www.saucedemo.com/",
            "prod", "https://www.saucedemo.com/"
    );

    private EnvironmentConfig() {}

    public static String getBaseUrl() {
        String env = System.getProperty("env", "dev").toLowerCase();
        return ENV_URLS.getOrDefault(env, ENV_URLS.get("dev"));
    }
}