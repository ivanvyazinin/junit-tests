package com.ivan.junittests.tests;

import com.ivan.junittests.models.Todo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GetTest extends BaseTest {

    @Test
    void testGetTodo() {
        testTodo = todoSteps.getCreatedTodo();

        var todos = todoApi.getTodo().jsonPath().getList(".", Todo.class);
        assertThat(todos).contains(testTodo);
    }
}
