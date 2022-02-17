package artemis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void createTask() {
        Todo t = new Todo("test1");
        Event e = new Event("test2", "home");
       // Deadline d = new Deadline("test3", "monday");
        assertEquals("[T][ ] test1", t.toString());
        assertEquals("[E][ ] test2 (at: home)", e.toString());
       // assertEquals("[D][ ] test3 (by: monday)", d.toString());
    }
    /* @Test
    public void parseDate() {
       assertEquals("Oct 25 2012", Parser.convertDate("2012-10-25"));
        assertEquals("May 03 2012", Parser.convertDate("2012-05-03"));
        assertEquals("Jun 25 2012", Parser.convertDate("2012-06-25"));
        assertEquals("testdate", Parser.convertDate("testdate"));
    }
     */
}
