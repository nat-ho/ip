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
        System.out.println();
    }

    public static void main(String[] args) {
        String logo = "  _  _     ___    _____   _____    ___   \n"
                + " | \\| |   /   \\  |_   _| |_   _|  / _ \\  \n"
                + " | .` |   | - |    | |     | |   | (_) | \n"
                + " |_|\\_|   |_|_|   _|_|_   _|_|_   \\___/  \n"
                + "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n"
                + "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' \n";
        System.out.println(logo);
        printLinesWithText("Top of the mornin' to ya! Name's Natto" + System.lineSeparator()
                + "Need a pint, two, or somethin' else?");

        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        String userCommand = in.nextLine();

        while (!userCommand.equals("bye")) {
            if (userCommand.toLowerCase().equals("list")) {
                if (Task.getTaskCount() == 0) {
                    //Checks if tasks is empty
                    printLinesWithText("Got nothing for you, stop asking me");
                } else {
                    //Prints all tasks
                    printLines();
                    for (int i = 0; i < Task.getTaskCount(); i++) {
                        System.out.println(i+1 + ". " + tasks[i].getFullTaskInfo());
                    }
                    printLines();
                    System.out.print(System.lineSeparator());
                }
            } else {
                if (userCommand.equals("")) {
                    //Checks if user inputs an empty string
                    printLinesWithText("Doing nothing is hard, you never know when you're done");
                } else if (userCommand.toLowerCase().startsWith("done")) {
                    //Marking tasks as done
                    int reminderNumber = Integer.parseInt(userCommand.substring(5))-1;
                    tasks[reminderNumber].setDone();
                    printLinesWithText("Well aren't you Mr Productive! Checked it off for you:"
                            + System.lineSeparator() + tasks[reminderNumber].getFullTaskInfo());
                } else {
                    //Include input into tasks
                    Task t = new Task(userCommand);
                    printLinesWithText("Added that one to the list: " + userCommand);
                    tasks[Task.getTaskCount()-1] = t;
                }
            }
            userCommand = in.nextLine();
        }

        //Prints goodbye string after breaking out of loop
        printLinesWithText("What, so everyone's supposed to sleep every night now?"
                + System.lineSeparator() + "You realize that nighttime makes up half of all time?");
    }
}
