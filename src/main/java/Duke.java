import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String line = "--------------";

    private static final String greeting = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String listMsg = "Here are the tasks in your list:";
    private static final String markMsg = "Nice! I've marked this task as done:";
    private static final String unmarkMsg = "OK, I've marked this task as not done yet:";
    private static final String ending = "Bye. Hope to see you again soon!";

    private static int totalTask = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        // start the program
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);
        run();
    }

    public static void run()  {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while(!exit) {
            String input;
            input = sc.nextLine();

            String[] cmd = input.split(" ", 2);

            System.out.println(line);

            if (cmd[0].equals("bye")) {
                exit = true;
                System.out.println(ending);
            } else if (cmd[0].equals("list")) {
                printList();
            } else if (cmd[0].equals("mark")) {
                try {
                    if(cmd.length < 2) {
                        throw new EmptyDescriptionException("mark");
                    }
                    markTask(Integer.parseInt(cmd[1]));

                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            } else if (cmd[0].equals("unmark")) {
                try {
                    if(cmd.length < 2){
                        throw new EmptyDescriptionException("unmark");
                    }
                    unmarkTask(Integer.parseInt(cmd[1]));

                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            } else if(cmd[0].equals("todo") || cmd[0].equals("deadline") || cmd[0].equals("event") ) {
                try {
                    if(cmd.length < 2) {
                        throw new EmptyDescriptionException(cmd[0]);
                    }
                    addTask(cmd[0], cmd[1]);
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            } else if(cmd[0].equals("delete")) {
                try {
                    if(cmd.length < 2){
                        throw new EmptyDescriptionException("delete");
                    } else if(Integer.parseInt(cmd[1]) > totalTask) {
                        throw new ListOutOfBound(Integer.parseInt(cmd[1]));
                    }
                    deleteTask(Integer.parseInt(cmd[1]));

                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                } catch (ListOutOfBound e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    throw new UnknownCmdException();
                } catch (UnknownCmdException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(line);
        }
    }

    public static void printList() {
        System.out.println(listMsg);
        for(int i=1; i <= totalTask; i++) {
            //Task temp = tasks[i-1];
            System.out.println(i+"."+ tasks.get(i-1));
        }
    }

    public static void markTask(int num) {
        try {
            if(num > totalTask) {
                throw new ListOutOfBound(num);
            } else {
                System.out.println(markMsg);
                tasks.get(num-1).markAsDone();
                System.out.println(tasks.get(num-1).toString());
            }
        } catch (ListOutOfBound e){
            System.out.println(e.getMessage());
        }
    }

    public static void unmarkTask(int num) {
        try {
            if(num > totalTask) {
                throw new ListOutOfBound(num);
            } else {
                System.out.println(unmarkMsg);
                tasks.get(num-1).markAsNotDone();
                System.out.println(tasks.get(num-1).toString());
            }
        } catch (ListOutOfBound e){
            System.out.println(e.getMessage());
        }
    }

    public static void addTask(String cmd, String task) {
        // String[] cmd = input.split(" ", 2);
        System.out.println("Got it. I've added this task:");
        Task t;

        if(cmd.equals("todo")) {
            t = new Todo(task);
        } else if(cmd.equals("event")) {
            String[] splitByDate = task.split("/at",2);
            if(splitByDate.length < 2) {
                t = new Event(splitByDate[0], " nil");
            } else {
                t = new Event(splitByDate[0], splitByDate[1]);
            }
        } else {
            String[] splitByDate = task.split("/by",2);
            if(splitByDate.length < 2) {
                t = new Deadline(splitByDate[0], " nil");
            } else {
                t = new Deadline(splitByDate[0], splitByDate[1]);
            }
        }
        tasks.add(t);
        System.out.println(t);
        totalTask++;
        System.out.println("Now you have " + (totalTask) + " task in the list." );
    }

    public static void deleteTask(int num) {
        System.out.println("Noted! I've removed this task:");
        tasks.remove(num-1);
        totalTask--;
        System.out.println("Now you have " + (totalTask) + " task in the list." );
    }
}
