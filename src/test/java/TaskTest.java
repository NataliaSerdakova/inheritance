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
        public void shouldReturnCorrectTitle() {
        String expected = "Позвонить родителям";
            SimpleTask task = new SimpleTask(1, expected);
            String actual = task.getTitle();
            Assertions.assertEquals(expected, actual);
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
    public void shouldReturnCorrectSubtasks() {
        String[] expected = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(1, expected);
        String[] actual = epic.getSubtasks();

        Assertions.assertEquals(expected, actual);
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

    @Test
    public void shouldReturnCorrectTopic() {
        String expected = "Обсуждение проекта";
        Meeting meeting = new Meeting(1, expected, "НетоБанк", "Понедельник утро");
        String actual = meeting.getTopic();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectProject() {
        String expected = "НетоБанк";
        Meeting meeting = new Meeting(2, "Обсуждение проекта", expected, "Понедельник утро");
        String actual = meeting.getProject();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectStart() {
        String expected = "Понедельник утро";
        Meeting meeting = new Meeting(3, "Обсуждение проекта", "НетоБанк", expected);
        String actual = meeting.getStart();
        Assertions.assertEquals(expected, actual);
    }
}
