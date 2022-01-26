import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    protected String path;
    protected File f;
    //protected List<Task> tasks;
    protected TaskList tasks;

    public Storage(String path, TaskList tasks) {
        this.tasks = tasks;
        this.path = path;
    }

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

    public void load() throws IOException {
        List<String> texts =  Files.readAllLines(Paths.get(path), StandardCharsets.US_ASCII);
        for(int i =0; i < texts.size(); i++) {
            String[] input = texts.get(i).split("\\|");
            boolean isDone = input[1].equals("1");
            if(input[0].equals("T")) {
                tasks.add(new Todo(input[2], isDone));
            } else  if(input[0].equals("E")) {
                tasks.add(new Event(input[2], input[3], isDone));
            } else {
                tasks.add(new Deadline(input[2], input[3], isDone));
            }
        }
    }

    public void deleteLineInFile(int n) throws IOException{
        List<String> texts =  Files.readAllLines(Paths.get(path), StandardCharsets.US_ASCII);
        texts.remove(n);
        Files.write(Paths.get(path), texts, StandardCharsets.US_ASCII);
    }


    public void changeMarkInFile(int n, boolean isDone) throws IOException{
        List<String> texts =  Files.readAllLines(Paths.get(path), StandardCharsets.US_ASCII);
        String temp = texts.get(n);
        String[] arr = temp.split("\\|");
        // String data = temp.replace(temp.substring(2,3), (isDone? "1":"0"));
        if(arr.length >3) {
            texts.set(n, arr[0] + (isDone ? "|1" : "|0") + "|"+ arr[2] +"|"+ arr[3]);
        } else {
            texts.set(n, arr[0] + (isDone ? "|1" : "|0") +"|"+ arr[2]);
        }
        Files.write(Paths.get(path), texts, StandardCharsets.US_ASCII);
    }

    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(path, true); // create a FileWriter in append mode
        fw.write(textToAppend+ "\n");
        fw.close();
    }

}
