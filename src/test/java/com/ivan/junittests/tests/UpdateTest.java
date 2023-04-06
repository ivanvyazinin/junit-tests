package com.ivan.junittests.tests;

import com.ivan.junittests.models.Todo;
import org.junit.jupiter.api.Test;

import static com.ivan.junittests.models.Todo.getRandomTodo;
import static com.ivan.junittests.utils.RandomGenerator.getRandomId;
import static org.assertj.core.api.Assertions.assertThat;

public class UpdateTest extends BaseTest {

    @Test
    void testUpdateTodo() {
        testTodo = todoSteps.getCreatedTodo();

        var todoToUpdate = testTodo.setCompleted(true).setText("newText");
        var r = todoApi.updateTodo(testTodo.getId(), todoToUpdate);
        assertThat(r.getStatusCode()).isEqualTo(200);

        todoSteps.checkThatTodoInList(todoApi.getTodo(), todoToUpdate);
    }

    @Test
    void testUpdateTodoDuplicate() {
        testTodo = todoSteps.getCreatedTodo();

        var todoToCreateSecond = getRandomTodo();
        todoApi.createTodo(todoToCreateSecond);

        var todoToUpdate = todoToCreateSecond.setId(testTodo.getId());
        var r = todoApi.updateTodo(todoToCreateSecond.getId(), todoToUpdate);
        assertThat(r.getStatusCode()).isEqualTo(400);
    }

    @Test
    void testUpdateTodoNotExists() {
        var todo = Todo.getRandomTodo();

        var r = todoApi.updateTodo(getRandomId(), todo);
        assertThat(r.getStatusCode()).isEqualTo(404);
    }
}
