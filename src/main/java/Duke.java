import java.util.Scanner;

public class Duke {
    public static void printHorizontalLines () {
        System.out.println("____________________________________________________________");
    }

    public static void printHorizontalLinesWithText (String response) {
        //Wraps text in between horizontal lines and prints
        printHorizontalLines();
        System.out.println(response);
        printHorizontalLines();
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
        printHorizontalLinesWithText("Top of the mornin' to ya! Name's Natto" + System.lineSeparator()
                + "Need a pint, two, or somethin' else?");

        Task[] reminderList = new Task[100];
        Scanner in = new Scanner(System.in);
        String userCommand = in.nextLine();
        while (!userCommand.equals("bye")) {
            if (userCommand.toLowerCase().equals("list")) {
                //Checks if reminderList is empty
                if (Task.getReminderCount() == 0) {
                    printHorizontalLinesWithText("Got nothing for you, stop asking me");
                }
                //Prints reminders
                else {
                    printHorizontalLines();
                    for (int i = 0; i < Task.getReminderCount(); i++) {
                        System.out.println(i+1 + ". " + reminderList[i].getFullTaskInfo());
                    }
                    printHorizontalLines();
                    System.out.print(System.lineSeparator());
                }
            }
            else {
                if (userCommand.equals("")) {
                    printHorizontalLinesWithText("Doing nothing is hard, you never know when you're done");
                }
                else if (userCommand.toLowerCase().startsWith("done")) {
                    int reminderNumber = Integer.parseInt(userCommand.substring(5))-1;
                    reminderList[reminderNumber].setDone();
                    printHorizontalLinesWithText("Well aren't you Mr Productive! Checked it off for you:"
                            + System.lineSeparator() + reminderList[reminderNumber].getFullTaskInfo());
                }
                else {
                    Task t = new Task(userCommand);
                    printHorizontalLinesWithText("Added that one to the list: " + userCommand);
                    reminderList[Task.getReminderCount()-1] = t;
                }
            }
            userCommand = in.nextLine();
        }
        //prints goodbye string after user enters "bye"
        printHorizontalLinesWithText("What, so everyone's supposed to sleep every night now?"
                + System.lineSeparator() + "You realize that nighttime makes up half of all time?");
    }
}
