import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> tasks = new ArrayList<>();

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task t) {
         tasks.add(t);
    }

    public void remove(int index) {
         tasks.remove(index);
    }





}
