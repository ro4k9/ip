package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class contains the list of task
 *
 * @author Rosa Kang
 */
public class TaskList {
    protected List<Task> tasks = new ArrayList<>();

    /**
     * The method provide the length of the List.
     *
     * @return size of the TaskList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * The method gets Task object at certain index of the List
     *
     * @param index the index of Task object in the list
     * @return Task object located at index of the list.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * The method adds task to the list.
     *
     * @param t the task to end to the list
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * The method removes task to the list.
     *
     * @param index the index of TaskList containing the task to be deleted
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * The method marks task at index i as complete.
     *
     * @param i the index of TaskList containing the task that needs to be edited (completion status)
     */
    public void markTask(int i) {
        tasks.get(i - 1).markAsDone();
    }

    /**
     * The method marks task at i as incomplete.
     *
     * @param i the index of TaskList containing the task that needs to be edited (completion status)
     */
    public void unmarkTask(int i) {
        tasks.get(i - 1).markAsNotDone();
    }

    public List<Task> findMatchingTask(String term) {
        List<Task> matching = new ArrayList<Task>();
        for (Task t : tasks) {
            if (t.getDescription().contains(term)) {
                matching.add(t);
            }
        }
        return matching;
    }
}
