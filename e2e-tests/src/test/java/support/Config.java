package support;

public class Config {

    private static final String ENV = System.getProperty("env", "qa");

    private Config() {
    }

    public static String getBaseUrl() {
        return switch (ENV.toLowerCase()) {
            case "dev" -> "https://www.saucedemo.com";
            case "prod" -> "https://www.saucedemo.com";
            default -> "https://www.saucedemo.com";
        };
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("chromeHeadless", "false"));
    }
}