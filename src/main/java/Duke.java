import java.util.Scanner;

public class Duke {
    public static void printLines() {
        System.out.println("____________________________________________________________");
    }

    public static void printLinesWithText(String response) {
        //Prints horizontal lines in between response
        printLines();
        System.out.println(response);
        printLines();
        System.out.print(System.lineSeparator());
    }

    private static void printWelcomeMessage() {
        String logo = "  _  _     ___    _____   _____    ___   \n"
                + " | \\| |   /   \\  |_   _| |_   _|  / _ \\  \n"
                + " | .` |   | - |    | |     | |   | (_) | \n"
                + " |_|\\_|   |_|_|   _|_|_   _|_|_   \\___/  \n"
                + "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n"
                + "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' \n";
        System.out.println(logo);
        printLinesWithText("Top of the mornin' to ya! Name's Natto" + System.lineSeparator()
                + "Need a pint, two, or somethin' else?");
    }

    private static void printGoodbyeMessage() {
        //Prints goodbye string after breaking out of loop
        printLinesWithText("What, so everyone's supposed to sleep every night now?" + System.lineSeparator()
                + "You realize that nighttime makes up half of all time?");
    }

    private static void listTasks(Task[] tasks) {
        if (Task.getTaskCount() == 0) {
            //Checks if tasks is empty
            printLinesWithText("Got nothing for you, stop asking me");
        } else {
            //Prints all tasks
            printLines();
            for (int i = 0; i < Task.getTaskCount(); i++) {
                System.out.println(i+1 + ". " + tasks[i].toString());
            }
            printLines();
            System.out.print(System.lineSeparator());
        }
    }

    private static void addTask(Task[] tasks, String userCommand) {
        String[] userInputArr = userCommand.split(" ", 2);
        Task task;
        switch(userInputArr[0].toLowerCase()) {
        case "todo":
            task = new ToDo(userInputArr[1]);
            tasks[Task.getTaskCount()-1] = task;
            break;
        case "deadline":
            String[] deadlineInput = userInputArr[1].split(" /by ");
            task = new Deadline(deadlineInput[0], deadlineInput[1]);
            tasks[Task.getTaskCount()-1] = task;
            break;
        case "event":
            String[] eventInput = userInputArr[1].split(" /at ");
            task = new Event(eventInput[0], eventInput[1]);
            tasks[Task.getTaskCount()-1] = task;
            break;
        default:
            task = new Task(userCommand);
            tasks[Task.getTaskCount()-1] = task;
            break;
        }
        printLinesWithText("Added that one to the list: " + task);
    }

    private static void completeTask(Task[] tasks, String userCommand) {
        //Marking tasks as done
        int taskNumber = Integer.parseInt(userCommand.split(" ")[1])-1;
        tasks[taskNumber].setDone();
        printLinesWithText("Well aren't you Mr Productive! Checked it off for you:"
                + System.lineSeparator() + tasks[taskNumber].toString());
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        String userCommand = in.nextLine();

        while (!userCommand.equals("bye")) {
            if (userCommand.toLowerCase().equals("list")) {
                listTasks(tasks);
            } else if (userCommand.equals("")) {
                //Checks if user inputs an empty string
                printLinesWithText("Doing nothing is hard, you never know when you're done");
            } else if (userCommand.toLowerCase().startsWith("done")) {
                completeTask(tasks, userCommand);
            } else {
                addTask(tasks, userCommand);
            }
            userCommand = in.nextLine();
        }
        printGoodbyeMessage();
    }
}
