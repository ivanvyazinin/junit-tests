package com.ivan.junittests.tests;

import com.ivan.junittests.models.Todo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateTest extends BaseTest {

    @Test
    void testCreateNewTodo() {
        testTodo = Todo.getRandomTodo();

        var response = todoApi.createTodo(testTodo);

        assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @Test
    void testCreateDuplicateTodo() {
        testTodo = todoSteps.getCreatedTodo();

        var response = todoApi.createTodo(testTodo);
        assertThat(response.getStatusCode()).isEqualTo(400);
    }
}
