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
    public void execute(TaskList tasks, Ui ui, Storage s) throws
            EmptyDescriptionException, UnknownCmdException, IOException {
        if (firstArg.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        if (firstArg.equals("bye")) {
            ui.farewell();
            isExit = true;
        } else if (firstArg.equals("list")) {
            showList(ui);
        } else if (firstArg.equals("mark")) {
            markTask(secondArg, tasks, s, ui);
        } else if (firstArg.equals("unmark")) {
            unmarkTask(secondArg, tasks, s, ui);
        } else if (firstArg.equals("todo")) {
            addTodo(secondArg, tasks, s, ui);
        } else if (firstArg.equals("event")) {
            addEvent(secondArg, tasks, s, ui);
        } else if (firstArg.equals("deadline")) {
            addDeadline(secondArg, tasks, s, ui);
        } else if (firstArg.equals("delete")) {
            deleteTask(secondArg, tasks, s, ui);
        } else if (firstArg.equals("find")) {
            findTask(secondArg, tasks, ui);
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
    public void showList(Ui ui) { //throws IOException {
        ui.lists();
    }


    /**
     * A method to mark the task complete.
     *
     * @param taskIndex index of the task to mark as complete
     * @param tasks     list of task
     * @param s         storage to deal with making changes to text file
     * @param ui        display functionality of duke
     */
    public void markTask(String taskIndex, TaskList tasks, Storage s, Ui ui) {
        try {
            if (taskIndex == null) {
                throw new EmptyDescriptionException("mark");
            }

            int i = Integer.parseInt(taskIndex);
            if (i > tasks.getSize()) {
                throw new ListOutOfBound(i);
            }

            tasks.markTask(i);

            s.changeMarkInFile(i - 1, true);
            ui.mark(tasks.getTask(i - 1).toString());
        } catch (ListOutOfBound | EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("The second argument of the mark must be an integer.");
        } catch (IOException e) {
            e.printStackTrace();
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
    public void unmarkTask(String taskIndex, TaskList tasks, Storage s, Ui ui) {
        try {
            if (taskIndex == null) {
                throw new EmptyDescriptionException("mark");
            }
            int i = Integer.parseInt(taskIndex);
            if (i > tasks.getSize()) {
                throw new ListOutOfBound(i);
            }
            tasks.unmarkTask(i);
            // change status in txt file
            s.changeMarkInFile(i - 1, false);
            ui.unmark(tasks.getTask(i - 1).toString());
        } catch (ListOutOfBound | EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("The second argument of the unmark must be an integer.");
        } catch (IOException e) {
            e.printStackTrace();
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
    public void addTodo(String taskDescription, TaskList tasks, Storage s, Ui ui) {
        try {
            if (taskDescription == null) {
                throw new EmptyDescriptionException("todo");
            }
            Todo t;
            t = new Todo(taskDescription);
            tasks.addTask(t);
            ui.tasks(t.toString(), tasks.getSize());
            s.appendToFile(t.format());
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
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
    public void addEvent(String taskDescription, TaskList tasks, Storage s, Ui ui) {
        try {
            if (taskDescription == null) {
                throw new EmptyDescriptionException("event");
            }

            Event e;
            String[] temp = Parser.parseDateAt(taskDescription);

            if (temp.length < 2) {
                throw new EmptyDescriptionException("time for command event");
            } else {
                e = new Event(temp[0], Parser.convertDate(temp[1]));
            }
            tasks.addTask(e);
            s.appendToFile(e.format());
            ui.tasks(e.toString(), tasks.getSize());
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
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
    public void addDeadline(String taskDescription, TaskList tasks, Storage s, Ui ui) {
        try {
            if (taskDescription == null) {
                throw new EmptyDescriptionException("deadline");
            }
            Deadline t;
            String[] temp = Parser.parseDateBy(taskDescription);

            if (temp.length < 2) {
                t = new Deadline(temp[0], " nil");
            } else {
                t = new Deadline(temp[0], Parser.convertDate(temp[1]));
            }
            tasks.addTask(t);
            s.appendToFile(t.format());
            ui.tasks(t.toString(), tasks.getSize());
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
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
    public void deleteTask(String taskDescription, TaskList tasks, Storage s, Ui ui) throws IOException {
        try {
            if (taskDescription == null) {
                throw new EmptyDescriptionException("delete");
            }
            int num = Integer.parseInt(taskDescription);

            if (num > tasks.getSize()) {
                throw new ListOutOfBound(num);
            }
            ui.delete(tasks.getTask(num - 1), tasks.getSize() - 1);
            tasks.removeTask(num - 1);
            s.deleteLineInFile(num - 1);
        } catch (ListOutOfBound | EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("The second argument of the delete must be an integer.");
        }
    }

    /**
     * A method to delete an existing task in tasks list.
     *
     * @param term  term to search for in the list of task
     * @param tasks list of task
     * @param ui    display functionality of duke
     */
    public void findTask(String term, TaskList tasks, Ui ui) {
        try {
            if (term == null) {
                throw new EmptyDescriptionException("find");
            }
            List<Task> lst = tasks.findMatchingTask(term);
            ui.matchLists(lst);
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }
}
