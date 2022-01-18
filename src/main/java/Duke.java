import java.util.Scanner;

public class Duke {
    static String line = "--------------";
    static String greeting = "Hello! I'm Duke \nWhat can I do for you? \n";
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

            System.out.println(line);
            if(input.equals("bye")) {
                exit = true;
                System.out.println(ending);
            } else if(input.equals("list")) {
                for(int i=1; i <= totalTask; i++) {
                    System.out.println(i+". "+ tasks[i-1].getTask());
                }
            } else {
                    System.out.println("added: "+ input);
                    tasks[totalTask] = new Task(input);
                    totalTask++;
            }
            System.out.println(line);
        }
    }
}

class Task {
    String task;
    public Task(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }
}
