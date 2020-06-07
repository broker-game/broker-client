package io.github.jabrena.broker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BrokerClientConfigTests {

    @Test
    public void given_Object_when_useDefaultConstructor_then_loadProperties() {

        BrokerClientConfig config = new BrokerClientConfig();

        then(config.getBroker()).isNotNull();
        then(config.getApplication()).isNotNull();
        then(config.getNode()).isNotNull();
        then(config.getFullName()).isNotNull();
        then(config.getEmail()).isNotNull();
        then(config.getUser()).isNotNull();
        then(config.getPassword()).isNotNull();
    }

    @Test
    public void given_Object_when_useConstructor_and_specifyConfigFile_then_loadProperties() {

        BrokerClientConfig config = new BrokerClientConfig("other_brokerclient.properties");

        then(config.getBroker()).isNotNull();
        then(config.getApplication()).isNotNull();
        then(config.getNode()).isNotNull();
        then(config.getFullName()).isNotNull();
        then(config.getEmail()).isNotNull();
        then(config.getUser()).isNotNull();
        then(config.getPassword()).isNotNull();
    }

    @Test
    public void given_Object_when_useManualConstructor_then_loadProperties() {

        BrokerClientConfig config = new BrokerClientConfig(
            "https://github.com/broker-game/broker-dev-environment",
            "PINGPONG",
            "PING-NODE",
            "Juan Antonio Breña Moral",
            "bren@juanantonio.info",
            "XXX",
            "YYY");

        then(config.getBroker()).isNotNull();
        then(config.getApplication()).isNotNull();
        then(config.getNode()).isNotNull();
        then(config.getFullName()).isNotNull();
        then(config.getEmail()).isNotNull();
        then(config.getUser()).isNotNull();
        then(config.getPassword()).isNotNull();
    }

    @Test
    public void given_Object_when_useConstructor_and_multipleConfigurations_and_nonVerbose_then_loadProperties() {

        BrokerClientConfig config = new BrokerClientConfig("application2.properties", "one");

        then(config.getBroker()).isNotNull();
        then(config.getApplication()).isNotNull();
        then(config.getNode()).isNotNull();
        then(config.getFullName()).isNotNull();
        then(config.getEmail()).isNotNull();
        then(config.getUser()).isNotNull();
        then(config.getPassword()).isNotNull();

        BrokerClientConfig config2 = new BrokerClientConfig("application2.properties", "two");

        then(config2.getBroker()).isNotNull();
        then(config2.getApplication()).isNotNull();
        then(config2.getNode()).isNotNull();
        then(config2.getFullName()).isNotNull();
        then(config2.getEmail()).isNotNull();
        then(config2.getUser()).isNotNull();
        then(config2.getPassword()).isNotNull();
    }

    @Test
    public void given_Object_when_constructor_and_multipleConfigurations_and_nonVerboses_and_missingFields_then_load() {

        Exception exception = assertThrows(RuntimeException.class, () -> {
            new BrokerClientConfig("application3.properties", "one");
        });

        String expectedMessage = "Not found key: user";
        String actualMessage = exception.getMessage();

        then(actualMessage.contains(expectedMessage));
    }

}
