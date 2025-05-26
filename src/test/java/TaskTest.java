import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void simpleTaskMatchesTitleContainsQuery() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить маме");

        boolean expected = true;
        boolean actual = simpleTask.matches("Позвонить");
        Assertions.assertEquals(expected, actual );

        boolean expected1 = false;
        boolean actual1 = simpleTask.matches("Покупка");
        Assertions.assertEquals(expected1, actual1 );
    }

    @Test
    void epicMatchesSubtaskContainsQuery() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(2, subtasks);

        boolean expected = true;
        boolean actual = epic.matches("Молоко");
        Assertions.assertEquals(expected, actual);

        boolean expected1 = false;
        boolean actual1 = epic.matches("Ягоды");
        Assertions.assertEquals(expected1, actual1);
    }

    @Test
    void meetingMatchesTopicOrProjectContainsQuery() {
        Meeting meeting = new Meeting(3, "Обсуждение проекта", "НетоБанка", "Понедельник утро");

        boolean expected = true;
        boolean actual = meeting.matches("Обсуждение");
        Assertions.assertEquals(expected, actual);

        boolean expected1 = true;
        boolean actual1 = meeting.matches("НетоБанка");
        Assertions.assertEquals(expected1, actual1);

        boolean expected2 = false;
        boolean actual2 = meeting.matches("Пятница");
        Assertions.assertEquals(expected2, actual2);
    }
}
