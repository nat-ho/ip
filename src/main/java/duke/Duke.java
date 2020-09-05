package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;

public class Duke {
    public static void printLines() {
        System.out.println("_________________________________________________________________________");
    }

    public static void printLinesWithText(String response) {
        //Prints horizontal lines in between response
        printLines();
        System.out.println(response);
        printLines();
        System.out.print(System.lineSeparator());
    }

    private static void printWelcomeMessage() {
        String logo = "  _  _     ___    _____   _____    ___\n"
                + " | \\| |   /   \\  |_   _| |_   _|  / _ \\\n"
                + " | .` |   | - |    | |     | |   | (_) |\n"
                + " |_|\\_|   |_|_|   _|_|_   _|_|_   \\___/\n"
                + "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|\n"
                + "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\n";
        System.out.println(logo);
        printLinesWithText("Top of the mornin' to ya! Name's Natto" + System.lineSeparator()
                + "Need a pint, two, or somethin' else?");
    }

    private static void printGoodbyeMessage() {
        //Prints goodbye string after breaking out of loop
        printLinesWithText("What, so everyone's supposed to sleep every night now?" + System.lineSeparator()
                + "You realize that nighttime makes up half of all time?");
    }

    private static void printAddTaskSuccess(Task task) {
        printLinesWithText("Added that one to the list: " + task);
    }

    private static String printTodoFormat() {
        return "todo <todo information>";
    }

    private static String printDeadlineFormat() {
        return "deadline <deadline name> /by <deadline information>";
    }

    private static String printEventFormat() {
        return "event <event name> /at <event information>";
    }

    private static String printAllTaskFormat() {
        return printTodoFormat() + System.lineSeparator() + printDeadlineFormat() + System.lineSeparator() +
                printEventFormat();
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
        String[] userInputArray = userCommand.split(" ", 2);
        String taskType = userInputArray[0].toLowerCase();

        switch(taskType) {
        case "todo":
            addTodo(tasks, userInputArray);
            break;
        case "deadline":
            addDeadline(tasks, userInputArray);
            break;
        case "event":
            addEvent(tasks, userInputArray);
            break;
        default:
            if (userCommand.equals("")) {
                //Empty line
                printLinesWithText("Doing nothing is hard, you never know when you're done");
            } else {
                //Unrecognised command
                printLinesWithText("If only I could add \"Read instruction manual\". Regardless, here it is: "
                        + System.lineSeparator() +  printAllTaskFormat());
            }
            break;
        }
    }

    private static void addTodo(Task[] tasks, String[] userInputArray) {
        try {
            Task task = new ToDo(userInputArray[1]);
            tasks[Task.getTaskCount()-1] = task;
            printAddTaskSuccess(task);
        } catch (IndexOutOfBoundsException e) {
            //User enters todo without task description
            printLinesWithText("How about giving that task a name?" + System.lineSeparator() +
                    printTodoFormat());
        }
    }

    private static void addDeadline(Task[] tasks, String[] userInputArray) {
        try {
            String[] deadlineInput = userInputArray[1].split(" /by ");
            Task task = new Deadline(deadlineInput[0], deadlineInput[1]);
            tasks[Task.getTaskCount()-1] = task;
            printAddTaskSuccess(task);
        } catch (IndexOutOfBoundsException e) {
            printLinesWithText("Unless you get me another pint, I only recognize:" + System.lineSeparator() +
                    printDeadlineFormat());
        }
    }

    private static void addEvent(Task[] tasks, String[] userInputArray) {
        try {
            String[] eventInput = userInputArray[1].split(" /at ");
            Task task = new Event(eventInput[0], eventInput[1]);
            tasks[Task.getTaskCount()-1] = task;
            printAddTaskSuccess(task);
        } catch (IndexOutOfBoundsException e) {
            printLinesWithText("Unless you download more ram, it's:" + System.lineSeparator() +
                    printEventFormat());

        }
    }

    private static void completeTask(Task[] tasks, String userCommand) {
        String[] userInputArray = userCommand.split(" ");
        try {
            if (userInputArray.length !=  2) {
                //User enter wrong format
                throw new DukeException("How about you take it one at a time. Try: \"done <task number>\"");
            }
            int taskNumber = Integer.parseInt(userInputArray[1])-1;
            tasks[taskNumber].setDone();
            printLinesWithText("Well aren't you Mr Productive! Checked it off for you:"
                    + System.lineSeparator() + tasks[taskNumber].toString());
        } catch (NumberFormatException e) {
            //User enters string after done
            printLinesWithText("I'd prefer if you gave me a number");
        } catch (NullPointerException e) {
            //User enters non-existing task
            printLinesWithText("How about you tell me a task that actually exists?");
        } catch (IndexOutOfBoundsException e) {
            //User enters number beyond 100 task limit
            printLinesWithText("Being delusional is thinking you could ever have more than a hundred tasks");
        } catch (DukeException e) {
            printLinesWithText(e.getMessage());
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        String userCommand = in.nextLine();

        while (!userCommand.equals("bye")) {
            if (userCommand.toLowerCase().equals("list")) {
                listTasks(tasks);
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
