package artemis;

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
    * Parse a line of command
     *
     * @param cmd a line of user input
     * @return a Command object containing the user input
    */
    public static Command parser(String cmd) {
        String[] cmdArr = parseCommand(cmd);
        if (cmdArr.length < 2) {
            return new Command(cmdArr[0]);
        } else {
            return new Command(cmdArr[0], cmdArr[1]);
        }
    }

    /**
     * Parse a line of command into 2 section. Artemis command and the description.
     *
     * @param input a line of user input
     * @return an array of string containing command and the description
     */
    public static String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    /**
     * Parse a user input for command 'event' into description and date information.
     *
     * @param input a line of String containing task description and date
     * @return an array of string containing the task description and date information
     */
    public static String[] parseDateAt(String input) {
        return input.split(" /at ", 2);
    }

    /**
     * Parse a user input for command 'deadline' into description and date information.
     *
     * @param input a line of String containing task description and date
     * @return an array of string containing the task description and date information
     */
    public static String[] parseDateBy(String input) {
        return input.split(" /by ", 2);
    }


    /**
     * Checks if the input String is valid date (and is in correct format).
     *
     * @param date String date input
     * @return true if it is a valid date, false otherwise
     */
    public static boolean isValidDate(String date) {
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
     * Convert String input of date to LocalDate.
     *
     * @param input String date input in the format of yyyy-mm-dd
     * @return LocalDate object containing date information in the format of yyyy-mm-dd
     */
    public static LocalDate convertDate(String input) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(input, format);
    }

    /**
     * Convert date in the format yyyy-MM-dd to MMM dd yyyy in String
     *
     * @param date Local date
     * @return reformatted date input in String
     */
    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
