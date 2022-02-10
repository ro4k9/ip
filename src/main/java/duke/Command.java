package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDateException;
import duke.exception.ListOutOfBound;
import duke.exception.UnknownCmdException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Command {
    protected String firstArg;
    protected String secondArg;

    /**
     * Constructor for Command
     *
     * @param firstArg first argument of the command
     */
    public Command(String firstArg) {
        this.firstArg = firstArg;
    }

    /**
     * Constructor for Command
     *
     * @param firstArg first argument of the command
     * @param secondArg second argument of the command
     */
    public Command(String firstArg, String secondArg) {
        this.firstArg = firstArg;
        this.secondArg = secondArg;
    }

    /**
     *
     * @param tasks list of task
     * @param ui display functionality of duke
     * @param s storage to deal with making changes to text file
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
        } else if(firstArg.equals("reminder")) {
            return getReminder(tasks, s, ui);
        } else {
            throw new UnknownCmdException();
        }
    }

    /**
     * A method to display a list of task.
     *
     * @param ui display functionality of duke
     */
    public String showList(Ui ui) { //throws IOException {
        return ui.lists();
    }

    /**
     * A method to mark the task complete.
     *
     * @param taskIndex index of the task to mark as complete
     * @param tasks     list of task
     * @param s         storage to deal with making changes to text file
     * @param ui        display functionality of duke
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
     * A method to mark the task incomplete.
     *
     * @param taskIndex index of the task to mark as complete
     * @param tasks     list of task
     * @param s         storage to deal with making changes to text file
     * @param ui        display functionality of duke
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
     * A method to add a new todo into tasks list.
     *
     * @param taskDescription description of the task
     * @param tasks           list of task
     * @param s               storage to deal with making changes to text file
     * @param ui              display functionality of duke
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
     * A method to add a new event into tasks list.
     *
     * @param taskDescription description of the task
     * @param tasks           list of task
     * @param s               storage to deal with making changes to text file
     * @param ui              display functionality of duke
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
                // String eventDate = Parser.convertDate(description[1]);
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
     * A method to add a new deadline into tasks list.
     *
     * @param taskDescription description of the task
     * @param tasks           list of task
     * @param s               storage to deal with making changes to text file
     * @param ui              display functionality of duke
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
            if(!Parser.isValidDate(dueDateInString)) {
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
     * A method to delete an existing task in tasks list.
     *
     * @param taskDescription description of the task
     * @param tasks           list of task
     * @param s               storage to deal with making changes to text file
     * @param ui              display functionality of duke
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
     * A method to delete an existing task in tasks list.
     *
     * @param term  term to search for in the list of task
     * @param tasks list of task
     * @param ui    display functionality of duke
     */
    public String findTask(String term, TaskList tasks, Ui ui) {
        try {
            if (term == null) {
                throw new EmptyDescriptionException("find");
            }
            assert term != null;
            List<Task> lst = tasks.findMatchingTask(term);
            return ui.matchLists(lst);
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        }
    }

    /**
     * A method to get tasks with impending due/event date in the task list.
     *
     ** @param tasks           list of task
     * @param s               storage to deal with making changes to text file
     * @param ui              display functionality of duke
     */
    public String getReminder(TaskList tasks, Storage s, Ui ui) {
        List<Task> lst = tasks.findTaskToRemind();
        return "";
    }
}
