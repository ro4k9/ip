package duke;

import java.io.IOException;
import java.util.List;

public class Command {
    protected String firstArg;
    protected String secondArg;
    protected boolean isExit = false;

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
     * @throws EmptyDescriptionException
     * @throws UnknownCmdException
     * @throws IOException
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
        } else {
            throw new UnknownCmdException();
        }
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * A method to display a list of task.
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

            int i = Integer.parseInt(taskIndex);
            if (i > tasks.getSize()) {
                throw new ListOutOfBound(i);
            }
            assert i <= tasks.getSize();
            tasks.markTask(i);
            s.changeMarkInFile(i - 1, true);
            return ui.mark(tasks.getTask(i - 1).toString());
        } catch (ListOutOfBound | EmptyDescriptionException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "The second argument of the mark must be an integer.";
        } catch (IOException e) {
            return e.getMessage();
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

            int i = Integer.parseInt(taskIndex);
            if (i > tasks.getSize()) {
                throw new ListOutOfBound(i);
            }
            assert i <= tasks.getSize();
            tasks.unmarkTask(i);
            s.changeMarkInFile(i - 1, false);
            return ui.unmark(tasks.getTask(i - 1).toString());
        } catch (ListOutOfBound | EmptyDescriptionException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "The second argument of the unmark must be an integer.";
        } catch (IOException e) {
            return e.getMessage();
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
            Todo t;
            t = new Todo(taskDescription);
            tasks.addTask(t);
            s.appendToFile(t.format());
            return ui.tasks(t.toString(), tasks.getSize());
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        } catch (IOException e) {
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
            Event e;
            String[] temp = Parser.parseDateAt(taskDescription);
            if (temp.length < 2) {
                throw new EmptyDescriptionException("time for command event");
            } else {
                e = new Event(temp[0], Parser.convertDate(temp[1]));
            }
            assert temp.length>=2;
            tasks.addTask(e);
            s.appendToFile(e.format());
            return ui.tasks(e.toString(), tasks.getSize());
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        } catch (IOException e) {
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

            Deadline t;
            String[] temp = Parser.parseDateBy(taskDescription);

            if (temp.length < 2) {
                throw new EmptyDescriptionException("time for command deadline");
            } else {
                t = new Deadline(temp[0], Parser.convertDate(temp[1]));
            }
            assert temp.length>=2;

            tasks.addTask(t);
            s.appendToFile(t.format());
            return ui.tasks(t.toString(), tasks.getSize());
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        } catch (IOException e) {
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
}
