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

        String[] reminderList = new String[100];
        int reminderCount = 0;
        Scanner in = new Scanner(System.in);
        String userCommand = in.nextLine();
        while (!userCommand.equals("bye")) {
            if (userCommand.toLowerCase().equals("list")) {
                //Checks if reminderList is empty
                if (reminderCount == 0) {
                    printHorizontalLinesWithText("Doing nothing is hard, you never know when you're done");
                }
                //Prints reminders
                else {
                    printHorizontalLines();
                    for (int i = 0; i < reminderCount; i++) {
                        System.out.println(i+1 + ". " + reminderList[i]);
                    }
                    printHorizontalLines();
                    System.out.print(System.lineSeparator());
                }
            }
            else {
                printHorizontalLinesWithText("Added: " + userCommand);
                reminderList[reminderCount++] = userCommand;
            }
            userCommand = in.nextLine();
        }
        //prints goodbye string after user enters "bye"
        printHorizontalLinesWithText("What, so everyone's supposed to sleep every night now?"
                + System.lineSeparator() + "You realize that nighttime makes up half of all time?");
    }
}
