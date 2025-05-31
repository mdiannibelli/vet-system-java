package config;

import io.github.cdimascio.dotenv.Dotenv;

public class Configuration {
    private static Configuration configInstance;
    private final Dotenv dotenv;

    private Configuration() {
        dotenv = Dotenv.load();
    }

    public static Configuration getInstance() {
        if(configInstance == null) {
            configInstance = new Configuration();
        }
        return configInstance;
    }

    public String getGmail() {
        return dotenv.get("GMAIL_USER");
    }

    public String getGmailAppPass() {
        return dotenv.get("GMAIL_APP_PASS");
    }
}
