package artemis.storage;

import artemis.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

/**
 * A class for loading and saving tasks information in local disk.
 *
 * @author Rosa Kang
 */
public class Storage {
    /**
     * For storing the path to txt file/
     */
    protected String path;
    protected File f;
    protected TaskList tasks;

    /**
     * Constructor for Duke.
     *
     * @param path  path to txt file in local disk
     * @param tasks a list of task
     */
    public Storage(String path, TaskList tasks) {
        this.tasks = tasks;
        this.path = path;
    }

    /**
     * If the text file exist at give path, create a file object to represent the file.
     * If not create the text file.
     */
    public void loadTextFile() {
        f = new File(path);
        f.getParentFile().mkdirs();

        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load up tasks from previous session from txt file to TaskList task.
     */
    public void load() throws IOException {
        List<String> texts = Files.readAllLines(Paths.get(path), StandardCharsets.US_ASCII);

        for (int i = 0; i < texts.size(); i++) {
            String[] input = texts.get(i).split("\\|");
            boolean isDone = input[1].equals("1");
            if (input[0].equals("T")) {
                tasks.addTask(new Todo(input[2], isDone));
            } else if (input[0].equals("E")) {
                tasks.addTask(new Event(input[2], input[3], isDone));
            } else {
                LocalDate dueDate = Parser.convertDate(input[3]);
                tasks.addTask(new Deadline(input[2], dueDate, isDone));
            }
        }
    }

    /**
     * Delete a line given by n in txt file.
     *
     * @param n line number to be deleted
     */
    public void deleteLineInFile(int n) throws IOException {
        List<String> texts = Files.readAllLines(Paths.get(path), StandardCharsets.US_ASCII);
        texts.remove(n);
        Files.write(Paths.get(path), texts, StandardCharsets.US_ASCII);
    }

    /**
     * Change the completion status for task in txt file given in line n.
     *
     * @param n line number that needs to be edited
     */
    public void changeMarkInFile(int n, boolean isDone) throws IOException {
        List<String> texts = Files.readAllLines(Paths.get(path), StandardCharsets.US_ASCII);
        String temp = texts.get(n);
        String[] arr = temp.split("\\|");
        // String data = temp.replace(temp.substring(2,3), (isDone? "1":"0"));
        if (arr.length > 3) {
            texts.set(n, arr[0] + (isDone ? "|1" : "|0") + "|" + arr[2] + "|" + arr[3]);
        } else {
            texts.set(n, arr[0] + (isDone ? "|1" : "|0") + "|" + arr[2]);
        }
        Files.write(Paths.get(path), texts, StandardCharsets.US_ASCII);
    }

    /**
     * Append to txt file.
     *
     * @param textToAppend String of text to append to the txt file
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(path, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }
}
