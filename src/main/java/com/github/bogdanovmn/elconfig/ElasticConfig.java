package com.github.bogdanovmn.elconfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 *
 */
public class ElasticConfig {
    private final static String MAIN_CONFIG_FILE_NAME = "main.properties";

    private final String secretConfigOptionName;
    private final Properties properties = new Properties();
    private boolean isLoaded = false;

    public ElasticConfig(String secretConfigOptionName) {
        this.secretConfigOptionName = secretConfigOptionName;
    }

    public String get(String key) throws IOException {
        this.loadProps();
        return this.properties.getProperty(key);
    }

    private void loadProps() throws IOException {
        if (!this.isLoaded) {
            this.isLoaded = true;
            this.properties.putAll(this.readMain());
            this.properties.putAll(this.readSecret());
        }
    }

    private Properties readSecret()
        throws IOException
    {
        String secretConfigFileName = System.getProperty(this.secretConfigOptionName);
        Properties result = new Properties();
        if (secretConfigFileName != null) {
            File secretFile = new File(secretConfigFileName);
            if (secretFile.canRead()) {
                try (FileInputStream stream = new FileInputStream(secretFile)) {
                    result.load(stream);
                }
            }
        }
        return result;
    }

    private Properties readMain()
        throws IOException
    {
        Properties result = new Properties();
        URL mainConfigFileURL = getClass().getClassLoader().getResource(MAIN_CONFIG_FILE_NAME);
        if (mainConfigFileURL != null) {
            try (InputStream stream = mainConfigFileURL.openStream()) {
                result.load(stream);
            }
        }
        return result;
    }
}
