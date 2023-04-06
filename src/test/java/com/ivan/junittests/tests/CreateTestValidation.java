package com.ivan.junittests.tests;

import com.ivan.junittests.models.Todo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.stream.Stream;

import static com.ivan.junittests.utils.RandomGenerator.getRandomId;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateTestValidation extends BaseTest {

    @ParameterizedTest
    @MethodSource("provideValidTodos")
    void testCreateNewTodoValidationPositive(Todo todo) {
        testTodo = todo;
        var response = todoApi.createTodo(testTodo);
        assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidTodos")
    void testCreateNewTodoValidationNegative(Todo todo) {
        testTodo = todo;
        var response = todoApi.createTodo(testTodo);
        assertThat(response.getStatusCode()).isEqualTo(400);
    }

    private static Stream<Arguments> provideInvalidTodos() {
        return Stream.of(
                Arguments.of(new Todo().setText("asd").setCompleted(false)),
                Arguments.of(new Todo().setId(getRandomId()).setCompleted(false)),
                Arguments.of(new Todo().setId(getRandomId()).setText("asd")),
                Arguments.of(new Todo().setText("asd").setCompleted(false).setId(null)),
                Arguments.of(new Todo().setId(getRandomId()).setCompleted(false).setText(null)),
                Arguments.of(new Todo().setId(getRandomId()).setText("asd").setCompleted(null))
        );
    }

    private static Stream<Arguments> provideValidTodos() {
        return Stream.of(
                Arguments.of(new Todo().setId(getRandomId()).setText("").setCompleted(false)),
                Arguments.of(new Todo().setId(BigInteger.valueOf(0)).setText("").setCompleted(false)),
                Arguments.of(new Todo().setId(BigInteger.valueOf(Integer.MAX_VALUE)).setText("").setCompleted(false)),
                Arguments.of(new Todo().setId(getRandomId()).setText("").setCompleted(true)),
                Arguments.of(new Todo().setId(getRandomId()).setText("!@#$%^&*()_").setCompleted(true))
        );
    }
}
