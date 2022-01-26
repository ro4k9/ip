package duke;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    public String[] parseDateAt(String input) {
        return input.split(" /at ", 2);
    }

    public String[] parseDateBy(String input) {
        return input.split(" /by ", 2);
    }

    public boolean isValidDate(String date) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.setLenient(true);
            format.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public String convertDate(String input) {
        if (isValidDate(input)) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(input, format);
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return input;
        }
    }
}
