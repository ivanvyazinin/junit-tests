package com.ivan.junittests.steps;

import com.ivan.junittests.api.TodoApi;
import com.ivan.junittests.models.Todo;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class TodoSteps {

    public TodoSteps(TodoApi todoApi) {
        this.todoApi = todoApi;
    }

    TodoApi todoApi;

    public Todo getCreatedTodo() {
        var todoToCreate = Todo.getRandomTodo();
        todoApi.createTodo(todoToCreate);
        return todoToCreate;
    }

    public void checkThatTodoInList(Response response, Todo expected) {
        var todos = response.jsonPath().getList(".", Todo.class);
        assertThat(todos).contains(expected);
    }
}
