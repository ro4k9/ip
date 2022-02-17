package artemis;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import artemis.exception.EmptyDescriptionException;
import artemis.exception.InvalidDateException;
import artemis.exception.ListOutOfBound;
import artemis.exception.UnknownCmdException;
import artemis.storage.Storage;
import artemis.storage.TaskList;

/**
 * Command class represents a command input given by a user.
 *
 * @author Rosa Kang
 */
public class Command {
    /**
     * Stores first argument of the command.
     */
    protected String firstArg;
    /**
     * Stores second argument of the command.
     */
    protected String secondArg;

    /**
     * Constructor for Command.
     *
     * @param firstArg first argument of the command
     */
    public Command(String firstArg) {
        this.firstArg = firstArg;
    }

    /**
     * Constructor for Command.
     *
     * @param firstArg first argument of the command
     * @param secondArg second argument of the command
     */
    public Command(String firstArg, String secondArg) {
        this.firstArg = firstArg;
        this.secondArg = secondArg;
    }

    /**
     * Execute command based on input given by the user.
     *
     * @param tasks list of task
     * @param ui display functionality of artemis
     * @param s storage to deal with making changes to text file
     * @return String representation of the output of the user command
     * @throws EmptyDescriptionException deals with empty arguments
     * @throws UnknownCmdException deals with unknown command
     * @throws IOException deals with exception related to file amend
     */
    public String execute(TaskList tasks, Ui ui, Storage s) throws
            EmptyDescriptionException, UnknownCmdException, IOException {
        if (firstArg.isEmpty()) {
            throw new EmptyDescriptionException();
        } else if (firstArg.equals("bye")) {
            return ui.farewell();
        } else if (firstArg.equals("list")) {
            return showList(ui);
        } else if (firstArg.equals("mark")) {
            return markTask(secondArg, tasks, s, ui);
        } else if (firstArg.equals("unmark")) {
            return unmarkTask(secondArg, tasks, s, ui);
        } else if (firstArg.equals("todo")) {
            return addTodo(secondArg, tasks, s, ui);
        } else if (firstArg.equals("event")) {
            return addEvent(secondArg, tasks, s, ui);
        } else if (firstArg.equals("deadline")) {
            return addDeadline(secondArg, tasks, s, ui);
        } else if (firstArg.equals("delete")) {
            return deleteTask(secondArg, tasks, s, ui);
        } else if (firstArg.equals("find")) {
            return findTask(secondArg, tasks, ui);
        } else if (firstArg.equals("reminder")) {
            return getReminder(tasks, ui);
        } else {
            throw new UnknownCmdException();
        }
    }

    /**
     * Display a list of task.
     *
     * @param ui display functionality of Artemis
     * @return String representation of the output of the command list
     */
    public String showList(Ui ui) { //throws IOException {
        return ui.lists();
    }

    /**
     * Mark the task as complete.
     *
     * @param taskIndex index of the task to mark as complete
     * @param tasks     list of task
     * @param s         storage to deal with making changes to text file
     * @param ui        display functionality of Artemis
     * @return String representation of the output of the command mark
     */
    public String markTask(String taskIndex, TaskList tasks, Storage s, Ui ui) {
        try {
            if (taskIndex == null) {
                throw new EmptyDescriptionException("mark");
            }
            assert taskIndex != null;
            int idx = Integer.parseInt(taskIndex);
            if (idx > tasks.getSize()) {
                throw new ListOutOfBound(idx);
            }
            assert idx <= tasks.getSize();
            tasks.markTask(idx);
            s.changeMarkInFile(idx - 1, true);
            return ui.mark(tasks.getTask(idx - 1).toString());
        } catch (ListOutOfBound | EmptyDescriptionException | IOException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "The second argument of the mark must be an integer.";
        }
    }

    /**
     * Mark the task as incomplete.
     *
     * @param taskIndex index of the task to mark as complete
     * @param tasks     list of task
     * @param s         storage to deal with making changes to text file
     * @param ui        display functionality of Artemis
     * @return String representation of the output of the command unmark
     */
    public String unmarkTask(String taskIndex, TaskList tasks, Storage s, Ui ui) {
        try {
            if (taskIndex == null) {
                throw new EmptyDescriptionException("mark");
            }
            assert taskIndex != null;

            int idx = Integer.parseInt(taskIndex);
            if (idx > tasks.getSize()) {
                throw new ListOutOfBound(idx);
            }
            assert idx <= tasks.getSize();
            tasks.unmarkTask(idx);
            s.changeMarkInFile(idx - 1, false);
            return ui.unmark(tasks.getTask(idx - 1).toString());
        } catch (ListOutOfBound | EmptyDescriptionException | IOException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "The second argument of the unmark must be an integer.";
        }
    }

    /**
     * Add a new todo into tasks list.
     *
     * @param taskDescription description of the task
     * @param tasks           list of task
     * @param s               storage to deal with making changes to text file
     * @param ui              display functionality of Artemis
     * @return String representation of the output of the user command todo
     */
    public String addTodo(String taskDescription, TaskList tasks, Storage s, Ui ui) {
        try {
            if (taskDescription == null) {
                throw new EmptyDescriptionException("todo");
            }
            assert taskDescription != null;
            Todo todo;
            todo = new Todo(taskDescription);
            tasks.addTask(todo);
            s.appendToFile(todo.format());
            return ui.tasks(todo.toString(), tasks.getSize());
        } catch (EmptyDescriptionException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Add a new event into tasks list.
     *
     * @param taskDescription description of the task
     * @param tasks           list of task
     * @param s               storage to deal with making changes to text file
     * @param ui              display functionality of Artemis
     * @return String representation of the output of the command event
     */
    public String addEvent(String taskDescription, TaskList tasks, Storage s, Ui ui) {
        try {
            if (taskDescription == null) {
                throw new EmptyDescriptionException("event");
            }
            assert taskDescription != null;
            Event event;
            String[] description = Parser.parseDateAt(taskDescription);
            if (description.length < 2) {
                throw new EmptyDescriptionException("time for command event");
            } else {
                event = new Event(description[0], description[1]);
            }
            assert description.length >= 2;
            tasks.addTask(event);
            s.appendToFile(event.format());
            return ui.tasks(event.toString(), tasks.getSize());
        } catch (EmptyDescriptionException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Add a new deadline into tasks list.
     *
     * @param taskDescription description of the task
     * @param tasks           list of task
     * @param s               storage to deal with making changes to text file
     * @param ui              display functionality of Artemis
     * @return String representation of the output of the command deadline
     */
    public String addDeadline(String taskDescription, TaskList tasks, Storage s, Ui ui) {
        try {
            if (taskDescription == null) {
                throw new EmptyDescriptionException("deadline");
            }
            assert taskDescription != null;

            Deadline deadline;
            String[] description = Parser.parseDateBy(taskDescription);
            if (description.length < 2) {
                throw new EmptyDescriptionException("time for command deadline");
            }
            assert description.length >= 2;

            String dueDateInString = description[1];
            if (!Parser.isValidDate(dueDateInString)) {
                throw new InvalidDateException();
            }
            assert Parser.isValidDate(dueDateInString);

            LocalDate dueDate = Parser.convertDate(description[1]);
            deadline = new Deadline(description[0], dueDate);
            tasks.addTask(deadline);
            s.appendToFile(deadline.format());

            return ui.tasks(deadline.toString(), tasks.getSize());
        } catch (EmptyDescriptionException | IOException | InvalidDateException e) {
            return e.getMessage();
        }
    }

    /**
     * Delete an existing task in tasks list.
     *
     * @param taskDescription description of the task
     * @param tasks           list of task
     * @param s               storage to deal with making changes to text file
     * @param ui              display functionality of Artemis
     * @return String representation of the output of the command delete
     */
    public String deleteTask(String taskDescription, TaskList tasks, Storage s, Ui ui) throws IOException {
        try {
            if (taskDescription == null) {
                throw new EmptyDescriptionException("delete");
            }
            assert taskDescription != null;
            int i = Integer.parseInt(taskDescription);
            if (i > tasks.getSize()) {
                throw new ListOutOfBound(i);
            }
            assert i <= tasks.getSize();

            Task taskToDelete = tasks.getTask(i - 1);
            int prevSize = tasks.getSize() - 1;

            tasks.removeTask(i - 1);
            s.deleteLineInFile(i - 1);

            return ui.delete(taskToDelete, prevSize);
        } catch (ListOutOfBound | EmptyDescriptionException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "The second argument of the delete must be an integer.";
        }
    }

    /**
     * Find an existing task matching the term given by the user.
     *
     * @param term  term to search for in the list of task
     * @param tasks list of task
     * @param ui    display functionality of Artemis
     * @return String representation of the output of the command find
     */
    public String findTask(String term, TaskList tasks, Ui ui) {
        try {
            if (term == null) {
                throw new EmptyDescriptionException("find");
            }
            assert term != null;
            List<Task> lst = tasks.findMatchingTask(term);
            return ui.matchList(lst);
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        }
    }

    /**
     * A method to get tasks with impending due or event date in the task list.
     *
     ** @param tasks           list of task
     * @param ui              display functionality of Artemis
     * @return String representation of the output of the command reminder
     */
    public String getReminder(TaskList tasks, Ui ui) {
        List<Deadline> lst = tasks.findDeadlineToRemind();
        return ui.reminderList(lst);
    }
}
