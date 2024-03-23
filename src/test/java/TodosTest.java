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
    public void paramQueryTestForSimpleTaskIfItMatches() {

        SimpleTask simpleTask = new SimpleTask(6, "Позвонить другу");

        boolean expected = true;
        boolean actual = simpleTask.matches("Позвонить");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void paramQueryTestForSimpleTaskIfItNoMatch() {

        SimpleTask simpleTask = new SimpleTask(6, "Позвонить другу");

        boolean expected = false;
        boolean actual = simpleTask.matches("Позвонить брату");

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void paramQueryTestForMeetingIfItMatchesTopic() {
        Meeting meeting = new Meeting(12, "Встреча в парке", "Романтика", "8 марта");

        boolean expected = true;
        boolean actual = meeting.matches("Встреча в парке");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void paramQueryTestForMeetingIfItNoMatchTopic() {
        Meeting meeting = new Meeting(12, "Встреча в парке", "Романтика", "8 марта");

        boolean expected = false;
        boolean actual = meeting.matches("Встреча в офисе");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void paramQueryTestForMeetingIfItMatchesProject() {
        Meeting meeting = new Meeting(12, "Встреча в парке", "Романтика", "8 марта");

        boolean expected = true;
        boolean actual = meeting.matches("Романтика");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void paramQueryTestForMeetingIfItNoMatchProject() {
        Meeting meeting = new Meeting(12, "Встреча в парке", "Романтика", "8 марта");

        boolean expected = false;
        boolean actual = meeting.matches("Работа");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void paramQueryTestForEpicIfItMatches() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        boolean expected = true;
        boolean actual = epic.matches("Яйца");

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void paramQueryTestForEpicIfItNoMatch() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        boolean expected = false;
        boolean actual = epic.matches("Пельмени");

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void thereAreSeveralTasksTest() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить Молоко");

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


        Task[] expected = {simpleTask, epic};
        Task[] actual = todos.search("Молоко");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void thereIsOneTaskTest() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить Молоко");

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


        Task[] expected = {meeting};
        Task[] actual = todos.search("Выкатка");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void thereAreNoTasksTest() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить Молоко");

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


        Task[] expected = { };
        Task[] actual = todos.search("Пельмени");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void getTitleTest() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        simpleTask.getTitle();

        String expected = "Позвонить родителям";
        String actual = simpleTask.getTitle();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void getSubtasksTest() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        epic.getSubtasks();

        String[] expected = {"Молоко", "Яйца", "Хлеб"};
        String[] actual = epic.getSubtasks();

        Assertions.assertArrayEquals(expected, actual);
    }


}