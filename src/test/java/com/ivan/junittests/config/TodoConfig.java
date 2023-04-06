package com.ivan.junittests.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:todo.properties"})
public interface TodoConfig extends Config {
    @Key("api.host")
    String getHost();

    @Key("user")
    String getUser();

    @Key("password")
    String getPassword();
}