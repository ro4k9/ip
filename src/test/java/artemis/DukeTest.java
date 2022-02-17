package artemis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    public void createTask() {
        Todo t = new Todo("test1");
        Event e = new Event("test2", "home");
        Deadline d = new Deadline("test3", LocalDate.of(2020, 2, 2));
        assertEquals("[T][ ] test1", t.toString());
        assertEquals("[E][ ] test2 (at: home)", e.toString());
        assertEquals("[D][ ] test3 (by: Feb 02 2020)", d.toString());
    }

    @Test
    public void markTask() {
        Todo t = new Todo("test4");
        Event e = new Event("test5", "home");
        Deadline d = new Deadline("test6", LocalDate.of(2020, 2, 2));
        t.markAsDone();
        e.markAsDone();
        d.markAsDone();
        assertEquals("[T][X] test4", t.toString());
        assertEquals("[E][X] test5 (at: home)", e.toString());
        assertEquals("[D][X] test6 (by: Feb 02 2020)", d.toString());
    }

    @Test
    public void parseDate() {
        LocalDate firstInputDate = Parser.convertDate("2002-10-25");
        LocalDate secondInputDate = Parser.convertDate("2012-05-03");
        LocalDate thirdInputDate = Parser.convertDate("2022-06-25");
        assertEquals("Oct 25 2002", Parser.dateToString(firstInputDate));
        assertEquals("May 03 2012", Parser.dateToString(secondInputDate));
        assertEquals("Jun 25 2022", Parser.dateToString(thirdInputDate));
    }
}
