package com.github.broker;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 *
 */
@Slf4j
@Data
public class BrokerClientConfig {

    //Node
    private final String node;

    //Credentials
    private final String broker;
    private final String user;
    private final String password;
    private final String fullName;
    private final String email;

    //Branch
    final String application;

    /**
     * Constructor
     *
     * @param broker broker
     * @param application application
     * @param node node
     * @param fullName fullName
     * @param email email
     * @param user user
     * @param password password
     */
    public BrokerClientConfig(String broker, String application, String node,
                              String fullName, String email, String user, String password) {
        this.broker = broker;
        this.node = node;
        this.fullName = fullName;
        this.email = email;
        this.user = user;
        this.password = password;
        this.application = application;
    }

    /**
     * Constructor
     *
     * @param propFileName Property file name
     */
    public BrokerClientConfig(String propFileName) {

        var properties = loadConfigurationFromProperties(propFileName);
        this.broker =       properties.getProperty("BROKER");
        this.application =  properties.getProperty("APPLICATION");
        this.node =         properties.getProperty("NODE");
        this.fullName =     properties.getProperty("FULLNAME");
        this.email =        properties.getProperty("EMAIL");
        this.user =         properties.getProperty("USER");
        this.password =     properties.getProperty("PASSWORD");
    }

    private static String DEFAULT_BROKER_CLIENT_CONFIG_FILE = "config.properties";

    public BrokerClientConfig() {
        this(DEFAULT_BROKER_CLIENT_CONFIG_FILE);
    }

    private Properties loadConfigurationFromProperties(String propFileName) {

        LOGGER.debug("Loading configuration from: {}", propFileName);

        Properties prop = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {

            if (Objects.nonNull(inputStream)) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }

        return prop;
    }
}
