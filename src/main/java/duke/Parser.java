package duke;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parser class deals with making sense of the user command
 *
 * @author Rosa Kang
 */
public class Parser {
    /**
     * Parse a line of command into Duke command and user inputs following the command.
     *
     * @param input a line of user input
     * @return an array of string containing command and the remaining user inputs
     */
    public String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    /**
     * Parse a user input for command 'event' into description and date information.
     *
     * @param input a line of String containing task description and date
     * @return an array of string containing description and date information
     */
    public String[] parseDateAt(String input) {
        return input.split(" /at ", 2);
    }

    /**
     * Parse a user input for command 'deadline' into description and date information.
     *
     * @param input a line of String containing task description and date
     * @return an array of string containing description and date information
     */
    public String[] parseDateBy(String input) {
        return input.split(" /by ", 2);
    }


    /**
     * Checks if the input String is valid date (and is in correct format).
     *
     * @param date String date input
     * @return an array of string containing description and date information.
     */
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

    /**
     * Convert date in the format yyyy-MM-dd to MMM dd yyyy if the input is a valid date.
     * If not return the input as it is.
     *
     * @param input String date input
     * @return reformatted String if the input is in valid date format else input
     */
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
