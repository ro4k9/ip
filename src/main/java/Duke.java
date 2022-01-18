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
            System.out.println(i+"."+ temp.getStatusIcon() + " " + temp.getTask());
        }
    }

    public static void markTask(int num) {
        System.out.println(markMsg);
        tasks[num-1].markAsDone();
        System.out.println(tasks[num-1].getStatusIcon() + " " + tasks[num-1].getTask());
    }

    public static void unmarkTask(int num) {
        System.out.println(unmarkMsg);
        tasks[num-1].markAsNotDone();
        System.out.println(tasks[num-1].getStatusIcon() + " " + tasks[num-1].getTask());

    }

    public static void addTask(String cmd) {
        System.out.println("added: "+ cmd);
        tasks[totalTask] = new Task(cmd);
        totalTask++;
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
        return (isDone? "[X]":"[ ]");
    }
    public String getTask() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }
}
