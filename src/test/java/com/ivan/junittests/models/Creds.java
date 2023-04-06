package com.ivan.junittests.models;

import com.ivan.junittests.config.TodoConfig;
import lombok.Data;
import lombok.experimental.Accessors;

import static com.ivan.junittests.config.Configuration.getTodoConfig;

@Data
@Accessors(chain = true)
public class Creds {
    private String userName;
    private String password;

    public static Creds getAdminCreds() {
        TodoConfig config = getTodoConfig();

        return new Creds()
                .setUserName(config.getUser())
                .setPassword(config.getPassword());
    }
}
