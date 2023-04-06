package com.ivan.junittests.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

import static com.ivan.junittests.utils.RandomGenerator.getRandomId;
import static com.ivan.junittests.utils.RandomGenerator.getRandomText;

@Data
@Accessors(chain = true)
public class Todo {
    BigInteger id;
    String text;
    Boolean completed;

    public static Todo getRandomTodo() {
        return new Todo().setId(getRandomId()).setText(getRandomText()).setCompleted(false);
    }
}
