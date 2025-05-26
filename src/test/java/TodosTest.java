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
    void shouldFindTasksMatchingQuery() {
        SimpleTask simpleTask1 = new SimpleTask(1, "Позвонить маме");
        SimpleTask simpleTask2 = new SimpleTask(2, "Купить молоко");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic1 = new Epic(3, subtasks);

        Meeting meeting = new Meeting(
                4,
                "Обсуждение проекта",
                "НетоБанка",
                "Понедельник утро");

        Todos todos = new Todos();
        todos.add(simpleTask1);
        todos.add(simpleTask2);
        todos.add(epic1);
        todos.add(meeting);

        // Поиск по слову "Молоко" должен вернуть simpleTask2 и epic1
        Task[] expected = {simpleTask2, epic1};
        Task[] actual = todos.search("Молоко");
        Assertions.assertArrayEquals(expected, actual);

        // Поиск по слову "Проекта" — только meeting
        Task[] expected1 = {meeting};
        Task[] actual1 = todos.search("Обсуждение");
        Assertions.assertArrayEquals(expected1, actual1);

        // Поиск по несуществующему слову — пустой массив
        Task[] expected2 = {};
        Task[] actual2 = todos.search("Кофе");
        Assertions.assertArrayEquals(expected2, actual2);
    }

    @Test
    public void shouldReturnEmptyArrayWhenNoMatchingTasks() {
        SimpleTask task1 = new SimpleTask(1, "Позвонить маме");
        Epic epic = new Epic(2, new String[]{"Молоко", "Яйца"});
        Meeting meeting = new Meeting(3, "Обсуждение проекта", "НетоБанка", "2023-10-10 14:00");

        Todos todos = new Todos();
        todos.add(task1);
        todos.add(epic);
        todos.add(meeting);

        // Поиск по несуществующему слову
        Task[] result = todos.search("Кофе");
        Assertions.assertEquals(0, result.length);
    }

    @Test
    public void shouldMatchPartOfTextInSimpleTask() {
        SimpleTask task = new SimpleTask(1, "Позвонить родителям");
        Todos todos = new Todos();
        todos.add(task);

        // Поиск по части строки
        Task[] result = todos.search("родителям");
        Assertions.assertArrayEquals(new Task[]{task}, result);
    }

    @Test
    public void shouldMatchPartOfTextInEpicSubtask() {
        Epic epic = new Epic(2, new String[]{"Молоко", "Яйца"});
        Todos todos = new Todos();
        todos.add(epic);

        // Поиск по подзадаче эпика
        Task[] expected = new Task[]{epic};
        Task[] actual = todos.search("Яйца");

        Assertions.assertArrayEquals(expected, actual);

    }
    }


