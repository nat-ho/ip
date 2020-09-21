package duke.ui;

import duke.task.Task;

import java.util.Scanner;

/**
 * Text UI of Duke
 */
public class UI {
    private final Scanner in;

    /**
     * Creates a UI object with a Scanner.
     */
    public UI() {
        in = new Scanner(System.in);
    }

    /**
     * Reads input entered by user.
     *
     * @return String containing user input
     */
    public String readUserCommand() {
        return in.nextLine().trim();
    }

    /**
     * Prints a divider line.
     */
    public void printDividerLine() {
        System.out.println(Messages.DIVIDER_LINE);
    }

    /**
     * Prints Duke response enclosed in divider lines.
     *
     * @param messages Single or multiple String responses
     */
    public void printTextWithDividerLine (String ... messages) {
        printDividerLine();
        for (String message : messages) {
            System.out.println(message);
        }
        printDividerLine();
        System.out.println(System.lineSeparator());
    }

    /**
     * Prints Duke logo and welcome message during startup.
     */
    public void printWelcomeMessage() {
        printTextWithDividerLine(Messages.DUKE_LOGO, Messages.MESSAGE_WELCOME);
    }

    /**
     * Prints goodbye message during exit.
     */
    public void printGoodbyeMessage() {
        printTextWithDividerLine(Messages.MESSAGE_GOODBYE);
    }

    /**
     * Prints success message after adding a task.
     *
     * @param task Task object that was added to Duke
     */
    public void printAddTaskSuccess(Task task) {
        printTextWithDividerLine(Messages.MESSAGE_ADD_TASK_SUCCESS + task);
    }

    /**
     * Prints success message after marking a task as completed.
     *
     * @param task Task object that was marked as done
     */
    public void printCompleteTaskSuccess(Task task) {
        printTextWithDividerLine(Messages.MESSAGE_COMPLETE_TASK_SUCCESS, task.toString());
    }

    /**
     * Prints success message after deleting a task
     *
     * @param task Task object that was deleted from the list
     * @param taskCount integer containing number of tasks remaining
     */
    public  void printDeleteTaskSuccess(Task task, int taskCount) {
        String taskLeft = "You've got " + taskCount + " more tasks to delete instead of complete";
        printTextWithDividerLine(Messages.MESSAGE_DELETE_TASK_SUCCESS, task.toString(), taskLeft);
    }

    /**
     * Prints the list of commands duke accepts.
     */
    public void printAllTaskFormat() {
        printTextWithDividerLine(Messages.TODO_FORMAT, Messages.DEADLINE_FORMAT,
                Messages.EVENT_FORMAT);
    }
}
