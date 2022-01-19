import java.util.Scanner;

public class Duke {
    static String line = "--------------";
    static String greeting = "Hello! I'm Duke \nWhat can I do for you? \n";
    static String listMsg = "Here are the tasks in your list: ";
    static String markMsg = "Nice! I've marked this task as done: ";
    static String unmarkMsg = "OK, I've marked this task as not done yet: ";

    static String ending = "Bye. Hope to see you again soon!";
    static int totalTask = 0;
    static Task[] tasks= new Task[100];

    public Duke() {
    }

    public static void main(String[] args) {
        // start the program
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);
        new Duke().run();
    }

    public static void run() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while(!exit) {
            String input;
            input = sc.nextLine();
            String[] cmd = input.split(" ");

            System.out.println(line);

            if(cmd[0].equals("bye")) {
                exit = true;
                System.out.println(ending);
            } else if(cmd[0].equals("list")) {
                printList();
            } else if(cmd[0].equals("mark")) {
                markTask(Integer.parseInt(cmd[1]));

            } else if(cmd[0].equals("unmark")) {
                unmarkTask(Integer.parseInt(cmd[1]));

            } else {
                addTask(input);
            }

            System.out.println(line);
        }
    }

    public static void printList() {
        System.out.println(listMsg);
        for(int i=1; i <= totalTask; i++) {
            Task temp = tasks[i-1];
            System.out.println(i+"."+ temp.toString());
        }
    }

    public static void markTask(int num) {
        System.out.println(markMsg);
        tasks[num-1].markAsDone();
        System.out.println(tasks[num-1].toString());
    }

    public static void unmarkTask(int num) {
        System.out.println(unmarkMsg);
        tasks[num-1].markAsNotDone();
        System.out.println(tasks[num-1].toString());

    }

    public static void addTask(String input) {
        String[] cmd = input.split(" ", 2);
        System.out.println("Got it. I've added this task: \n");

        if(cmd[0].equals("todo")) {
            tasks[totalTask] = new Todo(cmd[1]);

        } else if(cmd[0].equals("event")) {
            String[] splitByDate = cmd[1].split("/at",2);

            tasks[totalTask] = new Event(splitByDate[0], splitByDate[1]);
        } else if(cmd[0].equals("deadline")){
            String[] splitByDate = cmd[1].split("/by",2);
            tasks[totalTask] = new Deadline(splitByDate[0], splitByDate[1]);
        }
        System.out.println(tasks[totalTask]);
        totalTask++;
        System.out.println("Now you have " + (totalTask) + " task in the list." );


        /* System.out.println("added: "+ cmd);
        tasks[totalTask] = new Task(cmd);
        totalTask++;*/
    }

}

class Task {
    String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone? "[X] ":"[ ] ");
    }

    public void markAsDone() { isDone = true;}

    public void markAsNotDone() {
        isDone = false;
    }

   //  @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}

class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}

class Todo extends Task {
   //  protected String by;

    public Todo(String description) {
        super(description);
        // this.by = by;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}