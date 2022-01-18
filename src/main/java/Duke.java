import java.util.Scanner;

public class Duke {
    static String greeting = "Hello! I'm Duke \n What can I do for you?";
    static String ending = "Bye. Hope to see you again soon!";

    public Duke() {
    }
    public static void main(String[] args) {
        System.out.println(greeting);
        new Duke().run();
    }

    public static void run() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while(!exit) {
            String input;
            input = sc.nextLine();

            if(input.equals("bye")) {
                System.out.println(ending);
                exit = true;
            } else {
                System.out.println(input);
            }
        }
    }
}
