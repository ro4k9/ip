package duke;
public class Parser {
    public String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    public String[] parseDateAt(String input) {
        return input.split(" /at", 2);
    }

    public String[] parseDateBy(String input) {
        return input.split(" /by", 2);
    }
}
