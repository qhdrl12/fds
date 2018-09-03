package com.kakaopay.fds.core.config;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class RuleConfigLoader {
    private static Properties props = new Properties();

    static {
        try {
            String filename = "rule.properties";
            InputStream inputStream = RuleConfigLoader.class.getClassLoader().getResourceAsStream(filename);
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long getLongValue(String key) {
        String value = props.getProperty(key);
        return Long.parseLong(value);
    }

    public static int getIntValue(String key) {
        String value = props.getProperty(key);
        return Integer.parseInt(value);
    }
}
