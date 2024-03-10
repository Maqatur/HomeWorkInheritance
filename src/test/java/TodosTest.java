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
        simpleTask.matches("Позвонить другу");

        boolean expected = true;
        boolean actual = simpleTask.matches("Позвонить другу");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void paramQueryTestForSimpleTaskIfItNoMatch() {

        SimpleTask simpleTask = new SimpleTask(6, "Позвонить другу");
        simpleTask.matches("Позвонить брату");

        boolean expected = false;
        boolean actual = simpleTask.matches("Позвонить брату");
        ;

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void paramQueryTestForMeetingIfItMatchesTopic() {
        Meeting meeting = new Meeting(12, "Встреча в парке", "Романтика", "8 марта");
        meeting.matches("Встреча в парке");

        boolean expected = true;
        boolean actual = meeting.matches("Встреча в парке");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void paramQueryTestForMeetingIfItNoMatchTopic() {
        Meeting meeting = new Meeting(12, "Встреча в парке", "Романтика", "8 марта");
        meeting.matches("Встреча в офисе");

        boolean expected = false;
        boolean actual = meeting.matches("Встреча в офисе");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void paramQueryTestForMeetingIfItMatchesProject() {
        Meeting meeting = new Meeting(12, "Встреча в парке", "Романтика", "8 марта");
        meeting.matches("Романтика");

        boolean expected = true;
        boolean actual = meeting.matches("Романтика");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void paramQueryTestForMeetingIfItNoMatchProject() {
        Meeting meeting = new Meeting(12, "Встреча в парке", "Романтика", "8 марта");
        meeting.matches("Работа");

        boolean expected = false;
        boolean actual = meeting.matches("Работа");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void paramQueryTestForEpicIfItMatches() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        epic.matches("Яйца");
        boolean expected = true;
        boolean actual = epic.matches("Яйца");

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void paramQueryTestForEpicIfItNoMatch() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        epic.matches("Пельмени");
        boolean expected = false;
        boolean actual = epic.matches("Пельмени");

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void todosSimpleTaskQueryPositiveTest() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.search("Позвонить родителям");

        Task[] expected = {simpleTask};
        Task[] actual = todos.search("Позвонить родителям");
        Assertions.assertArrayEquals(expected, actual);


    }

    @Test
    public void todosEpicQueryPositiveTest() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Todos todos = new Todos();
        todos.add(epic);
        todos.search("Молоко");

        Task[] expected = {epic};
        Task[] actual = todos.search("Молоко");
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void todosMeetingQueryPositiveTestP1() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(meeting);
        todos.search("Выкатка 3й версии приложения");

        Task[] expected = {meeting};
        Task[] actual = todos.search("Выкатка 3й версии приложения");
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void todosMeetingQueryPositiveTestP2() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(meeting);
        todos.search("Приложение НетоБанка");

        Task[] expected = {meeting};
        Task[] actual = todos.search("Приложение НетоБанка");
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void todosSimpleTaskQueryNegativeTest() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.search("Позвонить другу");

        boolean expected = false;
        boolean actual = simpleTask.matches("Позвонить другу");
        Assertions.assertEquals(expected, actual);


    }

    @Test
    public void todosEpicQueryNegativeTest() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Todos todos = new Todos();
        todos.add(epic);
        todos.search("Milk");

        boolean expected = false;
        boolean actual = epic.matches("Milk");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void todosMeetingQueryNegativeTestP1() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(meeting);
        todos.search("Выкатка третьей версии приложения");

        boolean expected = false;
        boolean actual = meeting.matches("Выкатка третьей версии приложения");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void todosMeetingQueryNegativeTestP2() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(meeting);
        todos.search("Приложение Не тоБанка");

        boolean expected = false;
        boolean actual = meeting.matches("Приложение Не тоБанка");

        Assertions.assertEquals(expected, actual);
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