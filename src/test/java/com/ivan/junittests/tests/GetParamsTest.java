package com.ivan.junittests.tests;

import com.ivan.junittests.models.Todo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GetParamsTest extends BaseTest {

    List<Todo> todoList = new ArrayList<>();

    @BeforeAll
    void setUp() {
        for (int i = 0; i < 10; i++) {
            todoList.add(todoSteps.getCreatedTodo());
        }
    }

    @ParameterizedTest
    @MethodSource("provideParams")
    void testCreateNewTodoWithoutStatus(Map<String, Integer> params, int expectedSize) {
        var todos = todoApi.getTodo(params).jsonPath().getList(".", Todo.class);
        assertThat(todos).hasSize(expectedSize);
    }

    private static Stream<Arguments> provideParams() {
        return Stream.of(
                Arguments.of(Map.of("offset", 1, "limit", 10), 9),
                Arguments.of(Map.of("offset", 10, "limit", 10), 0),
                Arguments.of(Map.of("offset", 5), 5),
                Arguments.of(Map.of("limit", 5), 5),
                Arguments.of(Map.of("limit", 0), 0),
                Arguments.of(Map.of("limit", 100), 10),
                Arguments.of(Map.of("offset", 0), 10),
                Arguments.of(Map.of("offset", 10), 0),
                Arguments.of(Map.of("offset", 100), 0)
        );
    }

    @AfterAll
    void clear() {
        todoList.forEach(t -> todoApi.deleteTodo(t.getId(), admin));
    }
}
