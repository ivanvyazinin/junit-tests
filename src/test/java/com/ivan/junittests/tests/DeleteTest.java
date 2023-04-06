package com.ivan.junittests.tests;

import com.ivan.junittests.models.Creds;
import com.ivan.junittests.models.Todo;
import org.junit.jupiter.api.Test;

import static com.ivan.junittests.utils.RandomGenerator.getRandomId;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTest extends BaseTest {

    @Test
    void testDeleteTodo() {
        testTodo = todoSteps.getCreatedTodo();

        var r = todoApi.deleteTodo(testTodo.getId(), admin);
        assertThat(r.getStatusCode()).isEqualTo(204);

        var todos = todoApi.getTodo().jsonPath().getList(".", Todo.class);
        assertThat(todos).doesNotContain(testTodo);
    }

    @Test
    void testDeleteTodoUnAuth() {
        testTodo = todoSteps.getCreatedTodo();

        var r = todoApi.deleteTodo(testTodo.getId(), new Creds());
        assertThat(r.getStatusCode()).isEqualTo(401);

        todoSteps.checkThatTodoInList(todoApi.getTodo(), testTodo);
    }

    @Test
    void testDeleteTodoNotExists() {
        var r = todoApi.deleteTodo(getRandomId(), admin);
        assertThat(r.getStatusCode()).isEqualTo(404);
    }
}
