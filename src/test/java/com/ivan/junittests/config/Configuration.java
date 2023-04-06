package com.ivan.junittests.config;

import org.aeonbits.owner.ConfigFactory;

public class Configuration {
    public static TodoConfig getTodoConfig() {
        return ConfigFactory.newInstance().create(TodoConfig.class);
    }
}
