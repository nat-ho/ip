package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    private static void printDeleteTaskSuccess(Task task) {
        printLines();
        System.out.println("You've removed the task but not my disappointment:" + System.lineSeparator()
                + task.toString()  + "  -------->" + "deleted");
        System.out.println("You've got " + Task.getTaskCount() + " more tasks to delete instead of complete");
        printLines();
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

    private static void listTasks(ArrayList<Task> tasks) {
        if (Task.getTaskCount() == 0) {
            //Checks if tasks is empty
            printLinesWithText("Got nothing for you, stop asking me");
        } else {
            //Prints all tasks
            printLines();
            for (Task task : tasks) {
                System.out.println(tasks.indexOf(task)+1 + ". " + task.toString());
            }
            printLines();
            System.out.print(System.lineSeparator());
        }
    }

    private static void addTask(ArrayList<Task> tasks, String userCommand) {
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

    private static void addTodo(ArrayList<Task> tasks, String[] userInputArray) {
        try {
            Task task = new ToDo(userInputArray[1]);
            tasks.add(task);
            printAddTaskSuccess(task);
        } catch (IndexOutOfBoundsException e) {
            //User enters todo without task description
            printLinesWithText("How about giving that task a name?" + System.lineSeparator() +
                    printTodoFormat());
        }
    }

    private static void addDeadline(ArrayList<Task> tasks, String[] userInputArray) {
        try {
            String[] deadlineInput = userInputArray[1].split(" /by ");
            Task task = new Deadline(deadlineInput[0], deadlineInput[1]);
            tasks.add(task);
            printAddTaskSuccess(task);
        } catch (IndexOutOfBoundsException e) {
            printLinesWithText("Unless you get me another pint, I only recognize:" + System.lineSeparator() +
                    printDeadlineFormat());
        }
    }

    private static void addEvent(ArrayList<Task> tasks, String[] userInputArray) {
        try {
            String[] eventInput = userInputArray[1].split(" /at ");
            Task task = new Event(eventInput[0], eventInput[1]);
            tasks.add(task);
            printAddTaskSuccess(task);
        } catch (IndexOutOfBoundsException e) {
            printLinesWithText("Unless you download more ram, it's:" + System.lineSeparator() +
                    printEventFormat());

        }
    }

    private static void completeTask(ArrayList<Task> tasks, String userCommand) {
        String[] userInputArray = userCommand.split(" ");
        try {
            if (userInputArray.length !=  2) {
                //User enter wrong format
                throw new DukeException("How about you take it one at a time. Try: \"done <task number>\"");
            }
            int taskNumber = Integer.parseInt(userInputArray[1])-1;
            tasks.get(taskNumber).setDone();
            printLinesWithText("Well aren't you Mr Productive! Checked it off for you:"
                    + System.lineSeparator() + tasks.get(taskNumber).toString());
        } catch (NumberFormatException e) {
            //User enters string after done
            printLinesWithText("I'd prefer if you gave me a number");
        } catch (IndexOutOfBoundsException e) {
            //User enters non-existing task
            printLinesWithText("How about you tell me a task that actually exists?");
        } catch (DukeException e) {
            printLinesWithText(e.getMessage());
        }
    }

    private static void deleteTask(ArrayList<Task> tasks, String userCommand) {
        String[] userInputArray = userCommand.split(" ");
        try {
            if (userInputArray.length !=  2) {
                //User enter wrong format
                throw new DukeException("How about you take it one at a time. Try: \"delete <task number>\"");
            }
            int taskNumber = Integer.parseInt(userInputArray[1])-1;
            Task taskToRemove = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            Task.reduceTaskCount();
            printDeleteTaskSuccess(taskToRemove);
        } catch (NumberFormatException e) {
            //User enters string after done
            printLinesWithText("I'd prefer if you gave me a number");
        } catch (IndexOutOfBoundsException e) {
            //User enters non-existing task
            printLinesWithText("How about you tell me a task that actually exists?");
        } catch (DukeException e) {
            printLinesWithText(e.getMessage());
        }
    }

    private static void saveTasksToFile(ArrayList<Task> tasks) {
        String fileName = "data/TaskList.txt";
        String directoryName = "data/";
        File taskFile = new File(fileName);
        File directoryFile = new File(directoryName);

        if (directoryFile.mkdir()) {
            printLinesWithText("Directory to store save file created");
        }
        try {
            if (taskFile.createNewFile()){
               printLinesWithText("Save file for tasks created");
            }
            FileWriter fileWriter = new FileWriter(fileName);
            writeToFile(tasks, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred - Writing");
        }
    }

    private static void writeToFile(ArrayList<Task> tasks, FileWriter fileWriter) throws IOException {
        for (Task task : tasks) {
            String textToAdd = generateTextToAddString(task);
            fileWriter.write(textToAdd + System.lineSeparator());
        }
    }

    private static String generateTextToAddString(Task task) {
        switch(task.getType()) {
        case 'T':
            return task.getType() + " | " + task.isDone() + " | " + task.getDescription();
        case 'D':
            Deadline d = (Deadline) task;
            return d.getType() + " | " + d.isDone() + " | " + d.getDescription() + " | " + d.getBy();
        case 'E':
            Event e = (Event) task;
            return e.getType() + " | " + e.isDone() + " | " + e.getDescription() + " | " + e.getAt();
        default:
            return "Unrecognized type!";
        }
    }

    private static void loadSaveData(ArrayList<Task> tasks) {
        String fileName = "data/TaskList.txt";
        File taskFile = new File(fileName);

        try {
            if (taskFile.exists()) {
                Scanner reader = new Scanner(taskFile);
                readSaveData(tasks, reader);
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }

    private static void addSavedTask(ArrayList<Task> tasks, Task t, String status) {
        if (status.equals("true")) {
            t.setDone();
        }
        tasks.add(t);
    }

    private static void readSaveData(ArrayList<Task> tasks, Scanner reader) {
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] taskInfoArray = data.split(" \\| ");

            switch (taskInfoArray[0]) {
            case "T":
                ToDo t = new ToDo(taskInfoArray[2]);
                addSavedTask(tasks, t, taskInfoArray[1]);
                break;
            case "D":
                Deadline d = new Deadline(taskInfoArray[2], taskInfoArray[3]);
                addSavedTask(tasks, d, taskInfoArray[1]);
                break;
            case "E":
                Event e = new Event(taskInfoArray[2], taskInfoArray[3]);
                addSavedTask(tasks, e, taskInfoArray[1]);
                break;
            default:
                System.out.println("Unrecognized type");
                break;
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        loadSaveData(tasks);

        printWelcomeMessage();

        Scanner in = new Scanner(System.in);
        String userCommand = in.nextLine();

        while (!userCommand.equals("bye")) {
            if (userCommand.toLowerCase().equals("list")) {
                listTasks(tasks);
            } else if (userCommand.toLowerCase().startsWith("done")) {
                completeTask(tasks, userCommand);
                saveTasksToFile(tasks);
            } else if (userCommand.toLowerCase().startsWith("delete")) {
                deleteTask(tasks, userCommand);
                saveTasksToFile(tasks);

            } else {
                addTask(tasks, userCommand);
                saveTasksToFile(tasks);
            }
            userCommand = in.nextLine();
        }
        printGoodbyeMessage();
    }
}