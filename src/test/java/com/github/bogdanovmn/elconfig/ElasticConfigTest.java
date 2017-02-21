package com.github.bogdanovmn.elconfig;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ElasticConfigTest {
    @Test
    public void get() throws IOException {
        final String secretFileOption = "option.name";
        System.setProperty(secretFileOption, getClass().getResource("/secret.properties").getFile());

        ElasticConfig conf = new ElasticConfig(secretFileOption);

        assertEquals("db.name", "production", conf.get("db.name"));
        assertEquals("db.port", "3306", conf.get("db.port"));
        assertEquals("bla-bla", null, conf.get("bla-bla"));
    }

}