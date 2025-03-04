package ru.netology.javaqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TasksTest {

    @ParameterizedTest
    @CsvSource({        // id, topic, project, start, expected, query
            "555, Молоко, Приложение НетоБанка, Во вторник после обеда, Молоко, true",
            "555, Молоко, Приложение НетоБанка, Во вторник после обеда, Масло, false"
    })
    public void matchMeetingPositiveAndNegativeTest(int id, String topic, String project, String start, String query, boolean expected) {

        Meeting meeting = new Meeting(id, topic, project, start);

        boolean actual = meeting.matches(query);

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({        // id, title, query, expected
            "5, Позвонить родителям, Позвонить родителям, true",
            "5, Позвонить родителям, Молоко, false"
    })
    public void matchSimleTaskPositiveTestAndNegativeTest(int id, String title, String query, boolean expected) {
        SimpleTask simpleTask = new SimpleTask(id, title);

        boolean actual = simpleTask.matches(query);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMatchEpic() {

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        boolean expected = true;
        boolean actual = epic.matches("Яйца");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindMatchEpic() {

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        boolean expected = false;
        boolean actual = epic.matches("Масло");

        Assertions.assertEquals(expected, actual);
    }
}
