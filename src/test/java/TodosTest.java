import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTasksMatchingQuery_SimpleTask() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(2, subtasks);
        Meeting meeting = new Meeting(
                3,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        String query = "Яйца";

        Task[] expected = {epic};
        Task[] actual = todos.search(query);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTasksMatchingQuery_Meeting() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(2, subtasks);
        Meeting meeting1 = new Meeting(
                3,
                "Обсуждение проекта",
                "Проект А",
                "Понедельник"
        );
        Meeting meeting2 = new Meeting(
                4,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting1);
        todos.add(meeting2);

        String query = "НетоБанка";

        Task[] expected = {meeting2};
        Task[] actual = todos.search(query);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMatchSimpleTask_TitleContainsQuery() {
        SimpleTask task = new SimpleTask(10, "Позвонить маме");

        String query1 = "мам";

        boolean expected1 = true;

        boolean actual1 = task.matches(query1);

        Assertions.assertEquals(expected1, actual1);


        String query2 = "папа";
        boolean expected2 = false;
        boolean actual2 = task.matches(query2);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    public void shouldMatchEpic_SubtaskContainsQuery() {
        String[] subtasks = {"Купить молоко", "Купить хлеб", "Купить яйца"};
        Epic epic = new Epic(20, subtasks);

        String query1 = "хлеб";
        boolean expected1 = true;
        boolean actual1 = epic.matches(query1);
        Assertions.assertEquals(expected1, actual1);

        String query2 = "мясо";
        boolean expected2 = false;
        boolean actual2 = epic.matches(query2);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    public void shouldMatchMeeting_TopicOrProjectContainsQuery() {
        Meeting meeting = new Meeting(30, "Обсуждение проекта", "Проект Б", "Понедельник");

        String query1 = "Проект Б";
        boolean expected1 = true;
        boolean actual1 = meeting.matches(query1);
        Assertions.assertEquals(expected1, actual1);

        String query2 = "Обсуждение";
        boolean expected2 = true;
        boolean actual2 = meeting.matches("Обсуждение");
        Assertions.assertEquals(expected2, actual2);

        String query3 = "Вторник";
        boolean expected3 = false;
        boolean actual3 = meeting.matches("Вторник");
        Assertions.assertEquals(expected3, actual3);
    }
}




