package com.ivan.junittests.tests;

import com.ivan.junittests.api.TodoApi;
import com.ivan.junittests.models.Creds;
import com.ivan.junittests.models.Todo;
import com.ivan.junittests.steps.TodoSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static com.ivan.junittests.models.Creds.getAdminCreds;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected TodoApi todoApi;
    protected Creds admin = getAdminCreds();
    protected Todo testTodo;
    protected TodoSteps todoSteps;

    @BeforeAll
    void initSteps() {
        todoApi = new TodoApi();
        todoSteps = new TodoSteps(todoApi);
    }

    @AfterEach
    void clear() {
        var todos = todoApi.getTodo().jsonPath().getList(".", Todo.class);
        todos.forEach(t -> todoApi.deleteTodo(t.getId(), admin));
    }

}
